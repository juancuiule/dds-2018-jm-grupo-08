package jsonTest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import dominio.Cliente;
//import dominio.Dispositivo;
import json.JSONParser;

public class JSONParserTest {
    
    /*private Boolean dispositivosIguales(Dispositivo dispositivoA, Dispositivo dispositivoB) {
        return dispositivoA.getEncendido() == dispositivoB.getEncendido() &&
               dispositivoA.getkWh()       == dispositivoB.getkWh()       &&
               dispositivoA.getNombre()    == dispositivoB.getNombre();
    }*/
    
    @Test
    public void ObjetosDesdeArchivo_DadoUnJsonDeDispositivos_LoParseaCorrectamente(){
        JSONParser parser = new JSONParser();
        List<Cliente> objetosExtraidos = parser.objetosDesdeArchivo("src/test/resources/clienteDePrueba.json",Cliente[].class);
        assertTrue(objetosExtraidos.get(0).cantidadDeDispositivos()==3);
    }
    

    
}
