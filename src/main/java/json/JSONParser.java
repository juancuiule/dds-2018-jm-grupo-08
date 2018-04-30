package json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;


public class JSONParser{
   /* public <T> List<T> objetoDesdeArchivo(final String url, final Class<T[]> claseDeObjeto){
        FileReader archivo = readerDesdeArchivo(url);
        final T[] jsonToObject = new Gson().fromJson(archivo, claseDeObjeto);

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
    }*/
    
	public <TipoDeObjeto> List<TipoDeObjeto> objetoDesdeArchivo(String url,Class<TipoDeObjeto> claseDesObjeto){
	    Gson gson = new Gson();
	    
	    FileReader archivo = readerDesdeArchivo(url);
	    //Type tipo = new TypeToken<List<TipoDeObjeto>>(){}.getType();
	    
	    List<TipoDeObjeto> objetosExtraidos = gson.fromJson(archivo,new TipoListaDeObjetos<TipoDeObjeto>(claseDesObjeto));
	    //Revisar como manejar las excepciones: JsonSyntaxException y JsonIOException
        return objetosExtraidos; 
	    
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
    
    private class TipoListaDeObjetos<X> implements ParameterizedType {

        private Class<?> claseDeObjetos;

        public TipoListaDeObjetos(Class<X> claseDeObjetos) {
            this.claseDeObjetos = claseDeObjetos;
        }

        public Type[] getActualTypeArguments() {
            return new Type[] {claseDeObjetos};
        }

        public Type getRawType() {
            return List.class;
        }

        public Type getOwnerType() {
            return null;
        }

    }
    
}
