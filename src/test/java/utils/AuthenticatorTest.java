package utils;

import dominio.repositorios.RepositorioUsuarios;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class AuthenticatorTest {
    @Test
    public void authenticateUser_whenCalledWithAnUserAndPassword_callsFindOneOptionalToItsRepository() {
        String user = "MaikelShacson";
        String password = "8d432773b4d11840ee63d153cfd9fb76";

        RepositorioUsuarios mockRepo = mock(RepositorioUsuarios.class);

        new Authenticator(mockRepo).authenticateUser(user,password);
        verify(mockRepo).findOneOptional("username = MaikelShacson AND password = 8d432773b4d11840ee63d153cfd9fb76");
    }

}
