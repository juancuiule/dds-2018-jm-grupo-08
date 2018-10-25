package dominio;

import utils.Authenticator;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario extends PersistentObject{

    @OneToOne
    private Cliente rolCliente;

    @OneToOne
    private Administrador rolAdmin;
    private String username;
    private String password;

    public Usuario(Cliente rolCliente, Administrador rolAdmin, String username, String password) {
        this.rolCliente = rolCliente;
        this.rolAdmin = rolAdmin;
        this.username = username;
        this.password = Authenticator.hashPassword(password);
    }

    public Usuario() {
    }

    public Cliente getRolCliente() {
        return rolCliente;
    }

    public void setRolCliente(Cliente rolCliente) {
        this.rolCliente = rolCliente;
    }

    public Administrador getRolAdmin() {
        return rolAdmin;
    }

    public void setRolAdmin(Administrador rolAdmin) {
        this.rolAdmin = rolAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean esAdmin() {
        return rolAdmin != null;
    }

    public boolean esCliente() {
        return rolCliente != null;
    }

    public void actualizarPassword(String nuevaPass){
        this.password = Authenticator.hashPassword(password);
    }
}
