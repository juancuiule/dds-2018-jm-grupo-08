package jsonTest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import dominio.Dispositivo;
import json.JSONParser;

public class JSONParserTest {
    
    /*
     * DEPRECATED
     * 
	// Exepciones
	@Test(expected = FileNotFoundException.class)
	public void clienteDesdeArchivo_parsearUnArchivoInexistente_UnaFileNotFoundException()
			throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		new JSONParser().clienteDesdeArchivo(     );
	}

	@Test(expected = JsonSyntaxException.class)
	public void clienteDesdeArchivo_parsearUnJSONErroneo_UnaJsonSyntaxException()
			throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		new JSONParser().clienteDesdeArchivo("src/test/resources/jsonErroneo.json");
	}
	*
	*  END-DEPRECATED
	*
	*/
    
    @Test
    public void ObjetosDesdeArchivo_DadoUnJsonDeDispositivoss_LoParseaCorrectamente(){
        JSONParser parser = new JSONParser();
        List<Dispositivo> objetosExtraidos = parser.objetoDesdeArchivo("src/test/resources/dispositivoDePrueba.json",Dispositivo.class);
        assertTrue(objetosExtraidos.get(0).getkWh()==15d);
    }
    

    
}
