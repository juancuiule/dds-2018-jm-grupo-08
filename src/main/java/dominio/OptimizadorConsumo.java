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

public class OptimizadorConsumo {
    
    public PointValuePair optimizar(List<Dispositivo> dispositivos) {
        
        // Funcion economica-objetivo
        double[] coeficientes = arrayDeCoeficientes(dispositivos);
        LinearObjectiveFunction funcionZ = new LinearObjectiveFunction(coeficientes, 0);
        
        // Restricciones
        List<LinearConstraint> restricciones = new ArrayList<LinearConstraint>();
        dispositivos.forEach(dispositivo -> {
            LinearConstraint restriccionSuperior = generarRestriccion(dispositivos,dispositivo,Relationship.LEQ);
            LinearConstraint restriccionInferior = generarRestriccion(dispositivos,dispositivo,Relationship.GEQ);
            restricciones.add(restriccionSuperior);
            restricciones.add(restriccionInferior);
        });
        
        
        SimplexSolver solver = new SimplexSolver();
        PointValuePair solucion = solver.optimize(new MaxIter(100)                        // Numero maximo de iteraciones
                                                , funcionZ                                // Funcion objetivo
                                                , new LinearConstraintSet(restricciones)  // Restricciones
                                                , GoalType.MAXIMIZE                       // Objetivo (Maximizar)
                                                , new NonNegativeConstraint(true));       // Restriccion adicional ( solo positivos )
        return solucion;
    }
    
    private LinearConstraint generarRestriccion(List<Dispositivo> dispositivos, Dispositivo dispositivo, Relationship relacion) {
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

    private double[] arrayDeCoeficientes(List<Dispositivo> dispositivos) {
        Double[] coeficientes = new Double[dispositivos.size()];
        coeficientes = dispositivos.stream()
                                   .map(dispositivo -> dispositivo.consumoPorHora())
                                   .collect(Collectors.toList())
                                   .toArray(coeficientes);
        double[] coeficientesPrimitivos = ArrayUtils.toPrimitive(coeficientes);
        return coeficientesPrimitivos;
    }
}
