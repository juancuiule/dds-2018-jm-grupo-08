package jsonTest;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dominio.Cliente;
import dominio.Dispositivo;
import dominio.TipoDeDocumento;
import json.ClienteDeserializer;

public class DeserializersTest {
    String JSONDePrueba = "{\r\n" + 
            "        \"nombre\" : \"Armando\",\r\n" + 
            "        \"apellido\" : \"Barreda\",\r\n" + 
            "        \"tipoDeDocumento\" : \"DNI\",\r\n" + 
            "        \"numeroDeDocumento\" : \"38265412\",\r\n" + 
            "        \"telefono\" : \"1234657896\",\r\n" + 
            "        \"fechaDeAlta\" : \"2007-12-03\",\r\n" + 
            "        \"domicilio\" : \"Fake St. 123\",\r\n" + 
            "        \"nombreDeUsuario\" : \"PrincipalSkinner\",\r\n" + 
            "        \"contrasena\" : \"Skiiiiiiiinneer!\",\r\n" + 
            "        \"dispositivos\" : \r\n" + 
            "        [\r\n" + 
            "            {\r\n" + 
            "                \"nombre\":\"Heladera\",\r\n" + 
            "                \"kWh\":\"8\",\r\n" + 
            "                \"encendido\":\"true\"\r\n" + 
            "            }\r\n" + 
            "        ]\r\n" + 
            "    }";
    
    @Test
    public void ElParserDeserializaCorrectamenteLaFechaDeAltaDeUnCliente(){
        Gson parser = new GsonBuilder()
                .registerTypeAdapter(Cliente.class, new ClienteDeserializer())
                .create();
        
        Cliente clienteDePrueba = parser.fromJson(JSONDePrueba, Cliente.class);
        LocalDate fechaObjetivo = LocalDate.of(2007, 12, 03);
        
        assertEquals(fechaObjetivo, clienteDePrueba.getFechaDeAlta()); 
    }
    
    @Test
    public void ElParserDeserializaCorrectamenteElTipoDeDocumentoDeUnCliente(){
        Gson parser = new GsonBuilder()
                .registerTypeAdapter(Cliente.class, new ClienteDeserializer())
                .create();
        
        Cliente clienteDePrueba = parser.fromJson(JSONDePrueba, Cliente.class);
        TipoDeDocumento tipoObjetivo = TipoDeDocumento.DNI;
        
        assertEquals(tipoObjetivo, clienteDePrueba.getTipoDeDocumento()); 
    }
    
    @Test
    public void ElParserDeserializaCorrectamenteUnDispositivoDeUnCliente(){
        Gson parser = new GsonBuilder()
                .registerTypeAdapter(Cliente.class, new ClienteDeserializer())
                .create();
        
        Cliente clienteDePrueba = parser.fromJson(JSONDePrueba, Cliente.class);
        Dispositivo dispositivoObjetivo = new Dispositivo("Heladera",8,true);
        Dispositivo dispositivoDePrueba = clienteDePrueba.getDispositivos().get(0);
        
        assertTrue(dispositivoObjetivo.esIgualA(dispositivoDePrueba));
    }

}
