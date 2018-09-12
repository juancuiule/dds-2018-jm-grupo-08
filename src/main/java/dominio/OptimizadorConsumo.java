package dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.NoExistenRestriccionesException;

public class OptimizadorConsumo {
    
    public static List<Optimizacion> optimizar(List<Dispositivo> dispositivos) {
    	// Limite mensua de consumo desde configuracion
    	Double limiteMensual = ConfiguracionApp.limiteMensualDeConsumo;
    	
        // Filtrar dispositivos sin restricciones
        List<Dispositivo> dispositivosFiltrados = dispositivos
                                                 .stream()
                                                 .filter(dispositivo -> {
                                                     return dispositivo.tieneRestricciones();
                                                 }).collect(Collectors.toList());
        
        // Funcion economica-objetivo
        double[] arrayObjetivo = new double[dispositivosFiltrados.size()];
        Arrays.fill(arrayObjetivo, 1);
        LinearObjectiveFunction funcionZ = new LinearObjectiveFunction(arrayObjetivo, 0);
        
        
        // RESTRICCIONES
        List<LinearConstraint> restricciones = new ArrayList<LinearConstraint>();
        
        // Restriccion mensual
        double[] coeficientesRestriccion = arrayDeCoeficientes(dispositivosFiltrados);
        LinearConstraint restriccionMensual = new LinearConstraint(coeficientesRestriccion, Relationship.LEQ, limiteMensual);
        restricciones.add(restriccionMensual);
        
        // Restricciones por dispositivo
        dispositivosFiltrados.forEach(dispositivo -> {
            LinearConstraint restriccionSuperior = generarRestricciones(dispositivosFiltrados,dispositivo,Relationship.LEQ);
            LinearConstraint restriccionInferior = generarRestricciones(dispositivosFiltrados,dispositivo,Relationship.GEQ);
            restricciones.add(restriccionSuperior);
            restricciones.add(restriccionInferior);
        });
        
        // Solucionar problema
        SimplexSolver solver = new SimplexSolver();
        PointValuePair solucion = solver.optimize(new MaxIter(100)                        // Numero maximo de iteraciones
                                                , funcionZ                                // Funcion objetivo
                                                , new LinearConstraintSet(restricciones)  // Restricciones
                                                , GoalType.MAXIMIZE                       // Objetivo (Maximizar)
                                                , new NonNegativeConstraint(true));       // Restriccion adicional ( solo positivos )
        
        // Genear solucion
        List<Double> limitesDeRestriccion = listFromDoubleArray(solucion.getPoint());
        return generarOptimizaciones(dispositivosFiltrados,limitesDeRestriccion);
    }
    
    /////////////////
    /////////////////
    /////////////////
    
    private static List<Double> listFromDoubleArray(double[] array) {
        List<Double> resultado = new ArrayList<Double>();
        for(int i = 0; i < array.length; i++) {
            resultado.add(new Double(array[i]));
        }
        return resultado;
    }

    private static LinearConstraint generarRestricciones(List<Dispositivo> dispositivos, Dispositivo dispositivo, Relationship relacion) {
        Integer posicionEnLista = dispositivos.indexOf(dispositivo);
        double[] arrayRestriccion = new double[dispositivos.size()];
        Arrays.fill(arrayRestriccion, 0d);
        arrayRestriccion[posicionEnLista] = 1;
        double limite;
        if(relacion == Relationship.LEQ) {
            limite = dispositivos.get(posicionEnLista).restricciones().getCotaSuperior().doubleValue();
        }else if(relacion == Relationship.GEQ){
            limite = dispositivos.get(posicionEnLista).restricciones().getCotaInferior().doubleValue();
        }else {
            throw new RuntimeException ("Relacion no admitida");
        }

        LinearConstraint restriccion = new LinearConstraint(arrayRestriccion, relacion, limite);
        return restriccion;
    }

    private static double[] arrayDeCoeficientes(List<Dispositivo> dispositivos) {
        Double[] coeficientes = new Double[dispositivos.size()];
        coeficientes = dispositivos.stream()
                                   .map(dispositivo -> dispositivo.consumoPorHora())
                                   .collect(Collectors.toList())
                                   .toArray(coeficientes);
        double[] coeficientesPrimitivos = ArrayUtils.toPrimitive(coeficientes);
        return coeficientesPrimitivos;
    }
    
    private static List<Optimizacion> generarOptimizaciones(List<Dispositivo> dispositivos, List<Double> limites){
    	List<Optimizacion> restricciones= new ArrayList<Optimizacion>();
    	
		for(int i = 0; i< dispositivos.size(); i++){
			Dispositivo dispositivo = dispositivos.get(i);
			Double limite = limites.get(i);
			
			restricciones.add(new Optimizacion(dispositivo, limite));
		}
    	return restricciones;
    }
    
}
