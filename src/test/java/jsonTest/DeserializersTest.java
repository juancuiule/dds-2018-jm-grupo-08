package jsonTest;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dominio.Cliente;
import dominio.Dispositivo;
import dominio.TipoDeDocumento;
import json.ClienteDeserializer;
import json.DispositivoDeserializer;

public class DeserializersTest {
    String ClienteJSONDePrueba = "{\r\n" + 
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
            "                \"kWh\":\"9\",\r\n" + 
            "                \"encendido\":\"true\"\r\n" + 
            "            },\r\n" + 
            "            {\r\n" + 
            "                \"nombre\":\"Horno\",\r\n" + 
            "                \"kWh\":\"40\",\r\n" + 
            "                \"encendido\":\"true\"\r\n" + 
            "            },\r\n" + 
            "            {\r\n" + 
            "                \"nombre\":\"Microondas\",\r\n" + 
            "                \"kWh\":\"15\",\r\n" + 
            "                \"encendido\":\"false\"\r\n" + 
            "            }\r\n" + 
            "        ]\r\n" + 
            "    }";
    
    String ClienteJSONSinDispositivos = "{\r\n" + 
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
            "        ]\r\n" + 
            "    }";
    
    String DispositivoJSONDePrueba = "{\r\n" + 
            "                \"nombre\":\"Microondas\",\r\n" + 
            "                \"kWh\":\"15\",\r\n" + 
            "                \"encendido\":\"false\"\r\n" + 
            "    }";
    Gson parser;
    
    @Before
    public void generarParser(){
        parser = new GsonBuilder()
                .registerTypeAdapter(Cliente.class, new ClienteDeserializer())
                .registerTypeAdapter(Dispositivo.class, new DispositivoDeserializer())
                .create();
    }
    
    @Test
    public void ClienteDeserializer_DadoJSONDeCliente_SeDeserializaCorrectamenteLaFechaDeAlta(){
        
        Cliente clienteDePrueba = parser.fromJson(ClienteJSONDePrueba, Cliente.class);
        LocalDate fechaObjetivo = LocalDate.of(2007, 12, 03);
        
        assertEquals(fechaObjetivo, clienteDePrueba.getFechaDeAlta()); 
    }
    
    @Test
    public void ClienteDeserializer_DadoJSONDeCliente_SeDeserializaCorrectamenteElTipoDeDocumento(){
        
        Cliente clienteDePrueba = parser.fromJson(ClienteJSONDePrueba, Cliente.class);
        TipoDeDocumento tipoDeDocumentoObjetivo = TipoDeDocumento.DNI;
        
        assertEquals(tipoDeDocumentoObjetivo, clienteDePrueba.getTipoDeDocumento()); 
    }
    
    @Test
    public void DispositivoDeserializer_DadoJSONDeDispositivo_ElParserLoDeserializaCorrectamente(){
        
        Dispositivo dispositivoDePrueba = parser.fromJson(DispositivoJSONDePrueba, Dispositivo.class);
        Dispositivo dispositivoObjetivo = new Dispositivo("Microondas",15,false);

        assertTrue(dispositivoObjetivo.esIgualA(dispositivoDePrueba));
    }
    
    @Test
    public void ClienteDeserializer_DadoJSONDeClienteSinDispositivos_SuListaDeDispositivosNoTieneElementos(){
        
        Cliente clienteDePrueba = parser.fromJson(ClienteJSONSinDispositivos, Cliente.class);

        assertSame(0,clienteDePrueba.cantidadDeDispositivos());
    }

}
