package dominio;

import java.time.LocalDate;

public interface Dispositivo {
    public Double consumoEntre(LocalDate inicio, LocalDate fin);
}
