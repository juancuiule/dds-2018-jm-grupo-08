package json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSONParser<TObjeto> {
	public List<TObjeto> objetoDesdeArchivo(String url){
	    FileReader archivo = null;
	    try {
            archivo = new FileReader(url);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	    Gson gson = new Gson();
	    Type tipoDeLista = new TypeToken<ArrayList<TObjeto>>(){}.getType();
	    
	    
        List<TObjeto> objetosExtraidos = gson .fromJson(archivo,tipoDeLista);
	    //Revisar como manejar las excepciones: JsonSyntaxException y JsonIOException
        return objetosExtraidos;
	    
	}
}
