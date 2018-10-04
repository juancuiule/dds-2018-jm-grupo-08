package dominio;

import dominio.dispositivo.Dispositivo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.Relationship;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestriccionesBuilder {

    private static double[] arrayDeCoeficientes(List<Dispositivo> dispositivos) {
        return (double[]) ArrayUtils.toPrimitive(
                dispositivos.stream().map(Dispositivo::consumoPorHora).collect(Collectors.toList()));
    }

    public static LinearConstraint generarResticcionMensual(List<Dispositivo> dispositivosConRestricciones) {
        Double limiteMensual = ConfiguracionApp.limiteMensualDeConsumo;
        double[] coeficientesRestriccion = arrayDeCoeficientes(dispositivosConRestricciones);
        return new LinearConstraint(coeficientesRestriccion, Relationship.LEQ,
                limiteMensual);
    }

    public static LinearConstraint generarRestriccion(List<Dispositivo> dispositivos, Dispositivo dispositivo,
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

    public static List<LinearConstraint> generarRestricciones(List<Dispositivo> dispositivos){
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
