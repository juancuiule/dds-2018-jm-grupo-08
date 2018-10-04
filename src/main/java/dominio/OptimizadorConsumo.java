package dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

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
import utils.Utils;

public class OptimizadorConsumo {

    public static List<Optimizacion> optimizar(List<Dispositivo> dispositivos) {

        List<Dispositivo> dispositivosConRestricciones = filtrarDispositivosConRestricciones(dispositivos);

        LinearObjectiveFunction funcionZ = generarFuncionEconomica(dispositivosConRestricciones);

        List<LinearConstraint> restricciones = generarRestricciones(dispositivosConRestricciones);

        PointValuePair solucion = solucionarSimplex(funcionZ, restricciones);

        return generarSolucion(solucion, dispositivosConRestricciones);
    }

    private static List<Optimizacion> generarSolucion(PointValuePair solucion, List<Dispositivo> dispositivos){
        List<Double> limitesDeRestriccion = listFromDoubleArray(solucion.getPoint());
        return generarOptimizaciones(dispositivos, limitesDeRestriccion);
    }

    private static PointValuePair solucionarSimplex(LinearObjectiveFunction funcionZ, List<LinearConstraint> restricciones) {
        SimplexSolver solver = new SimplexSolver();
        return solver.optimize(new MaxIter(100) // Numero maximo de iteraciones
                    , funcionZ // Funcion objetivo
                    , new LinearConstraintSet(restricciones) // Restricciones
                    , GoalType.MAXIMIZE // Objetivo (Maximizar)
                    , new NonNegativeConstraint(true));
    }

    private static LinearConstraint generarResticcionMensual(List<Dispositivo> dispositivosConRestricciones) {
        Double limiteMensual = ConfiguracionApp.limiteMensualDeConsumo;
        double[] coeficientesRestriccion = arrayDeCoeficientes(dispositivosConRestricciones);
        return new LinearConstraint(coeficientesRestriccion, Relationship.LEQ,
                limiteMensual);
    }

    private static List<Dispositivo> filtrarDispositivosConRestricciones(List<Dispositivo> dispositivos) {
        return dispositivos.stream().filter(Dispositivo::tieneRestricciones).collect(Collectors.toList());
    }

    private static LinearObjectiveFunction generarFuncionEconomica(List<Dispositivo> dispositivosConRestricciones) {
        double[] arrayObjetivo = (double[]) ArrayUtils.toPrimitive(dispositivosConRestricciones.stream().map(disp -> 1).collect(Collectors.toList()));
        return new LinearObjectiveFunction(arrayObjetivo, 0);
    }

    private static List<Double> listFromDoubleArray(double[] array) {
        return DoubleStream.of(array).boxed().collect(Collectors.toList());
    }

    private static LinearConstraint generarRestriccion(List<Dispositivo> dispositivos, Dispositivo dispositivo,
                                                       Relationship relacion) {
        double[] arrayRestriccion = (double[]) ArrayUtils.toPrimitive(dispositivos.stream().map(disp -> {
            if (disp == dispositivo) {
                return 1d;
            } else {
                return 0d;
            }
        }).collect(Collectors.toList()));

        double limite;
        if (relacion == Relationship.LEQ) {
            limite = dispositivo.restricciones().getCotaSuperior();
        } else if (relacion == Relationship.GEQ) {
            limite = dispositivo.restricciones().getCotaInferior();
        } else {
            throw new RuntimeException("Relacion no admitida");
        }

        return new LinearConstraint(arrayRestriccion, relacion, limite);
    }

    private static double[] arrayDeCoeficientes(List<Dispositivo> dispositivos) {
        return (double[]) ArrayUtils.toPrimitive(
                dispositivos.stream().map(Dispositivo::consumoPorHora).collect(Collectors.toList()));
    }

    private static List<Optimizacion> generarOptimizaciones(List<Dispositivo> dispositivos, List<Double> limites) {
        return Utils.zip(dispositivos.stream(), limites.stream(), (dispositivo, limite) -> {
            return new Optimizacion(dispositivo, limite);
        }).collect(Collectors.toList());
    }

    private static List<LinearConstraint> generarRestricciones(List<Dispositivo> dispositivos){
        // RESTRICCIONES
        List<LinearConstraint> restricciones = new ArrayList<>();

        // Restriccion mensual
        LinearConstraint restriccionMensual = generarResticcionMensual(dispositivos);
        restricciones.add(restriccionMensual);

        // Restricciones por dispositivo
        dispositivos.forEach(dispositivo -> {
            LinearConstraint restriccionSuperior = generarRestriccion(dispositivos, dispositivo,
                    Relationship.LEQ);
            LinearConstraint restriccionInferior = generarRestriccion(dispositivos, dispositivo,
                    Relationship.GEQ);
            restricciones.add(restriccionSuperior);
            restricciones.add(restriccionInferior);
        });
        return restricciones;
    }

}
