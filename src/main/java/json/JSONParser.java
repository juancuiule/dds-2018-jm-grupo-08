package json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;


public class JSONParser{
    public <T> List<T> objetosDesdeArchivo(String url, Class<T[]> claseDeObjeto){
        
        FileReader archivo = readerDesdeArchivo(url);
        T[] jsonToObject = new Gson().fromJson(archivo, claseDeObjeto);

        return Arrays.asList(jsonToObject);
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
