package dominio.transformadores;

import dominio.Cliente;
import dominio.PersistentObject;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Transformador extends PersistentObject {
    @Embedded
    Punto punto;
    boolean activo;

    @OneToMany
    @JoinColumn(name = "transformador_id")
    List<Cliente> clientesSuministrados = new ArrayList<Cliente>();

    // Constructors
    public Transformador() {
    }

    public Transformador(Punto punto, boolean activo) {
        this.punto = punto;
        this.activo = activo;
    }

    // Accessors
    public Punto getPunto() {
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Cliente> getClientesSuministrados() {
        return clientesSuministrados;
    }

    public void setClientesSuministrados(List<Cliente> clientesSuministrados) {
        this.clientesSuministrados = clientesSuministrados;
    }

    // Public methods
    public Double energiaQueSuministra() {
        return 1.0;
    }

    public boolean estaActivo() {
        return activo;
    }

    public void conectarCliente(Cliente cliente) {
        this.clientesSuministrados.add(cliente);
    }

    public boolean tieneAlCliente(Cliente cliente) {
        return clientesSuministrados.contains(cliente);
    }

    public Double consumo(Double diasUltimoMes) {
        return clientesSuministrados.stream().mapToDouble(cliente -> cliente.consumo(diasUltimoMes)).sum();
    }
}