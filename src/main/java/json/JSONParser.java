package json;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import dominio.Cliente;

public class JSONParser {
    public List<Cliente> clienteDesdeArchivo (String url) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        Gson parser = new GsonBuilder()
                .registerTypeAdapter(Cliente.class, new ClienteDeserializer())
                .create();
         
        return Arrays.asList(parser.fromJson(new FileReader(url), Cliente[].class));
    }
}
