package jsonTest;
import java.io.FileNotFoundException;

import org.junit.Test;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import json.JSONParser;

public class JSONParserTest {
    
    // Exepciones
    @Test(expected = FileNotFoundException.class)
    public void clienteDesdeArchivo_parsearUnArchivoInexistente_UnaFileNotFoundException() throws JsonSyntaxException, JsonIOException, FileNotFoundException{
        new JSONParser().clienteDesdeArchivo("src/test/resources/archivoInexistente.json");
    }
    
    @Test(expected = JsonSyntaxException.class)
    public void clienteDesdeArchivo_parsearUnJSONErroneo_UnaJsonSyntaxException() throws JsonSyntaxException, JsonIOException, FileNotFoundException{
        new JSONParser().clienteDesdeArchivo("src/test/resources/jsonErroneo.json");
    }

}
