package json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JSONParser{
    public <T> List<T> objetosDesdeArchivo(String url, Class<T[]> claseDeObjeto){
        
        FileReader archivo = readerDesdeArchivo(url);
        Gson gson = generarGson();
        T[] jsonToObject = gson.fromJson(archivo, claseDeObjeto);

        return Arrays.asList(jsonToObject);
    }
    
    /* PRIVATE METHODS*/
    
    private Gson generarGson() {
        Gson gson = new GsonBuilder()
                       .registerTypeAdapter(LocalDate.class, new LocalTimeDeserializer())
                       .create();
        return gson;
    }
    
    private FileReader readerDesdeArchivo(String url) {
        FileReader archivo = null;
        try {
            archivo = new FileReader(url);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return archivo;
    
    }
}
