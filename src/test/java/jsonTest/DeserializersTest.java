package jsonTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import dominio.Cliente;
import dominio.Dispositivo;
import dominio.TipoDeDocumento;
import json.ClienteDeserializer;
import json.DispositivoDeserializer;

public class DeserializersTest {
	JsonReader readerClienteDePrueba;
	JsonReader readerClienteSinDispositivos;
	JsonReader readerDispositivoDePrueba;
	Gson parser;

	// Antes de iniciar las pruebas se requieren dos operaciones:
	// Generar las cadenas JSON a partir de las cuales se generaran los objetos
	@Before
	public void generarReaders() {
		try {
			readerClienteDePrueba = new JsonReader(new FileReader("src/test/resources/clienteDePrueba.json"));
			readerClienteSinDispositivos = new JsonReader(
					new FileReader("src/test/resources/clienteSinDispositivos.json"));
			readerDispositivoDePrueba = new JsonReader(new FileReader("src/test/resources/dispositivoDePrueba.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Construir el parser de JSON, utilizando nuestras clases de deserializacion
	@Before
	public void generarParser() {
		parser = new GsonBuilder().registerTypeAdapter(Cliente.class, new ClienteDeserializer())
				.registerTypeAdapter(Dispositivo.class, new DispositivoDeserializer()).create();
	}

	// Testeamos que los campos de los objetos se deserializen correctamente
	// centrandonos en aquellos cuya deserializacion no esta estandarizada por la
	// libreria GSON
	// Estos son:
	// Las fechas de alta, de la clase LocalDate
	@Test
	public void ClienteDeserializer_DadoJSONDeCliente_SeDeserializaCorrectamenteLaFechaDeAlta() {

		Cliente clienteDePrueba = parser.fromJson(readerClienteDePrueba, Cliente.class);
		LocalDate fechaObjetivo = LocalDate.of(2007, 12, 03);

		assertEquals(fechaObjetivo, clienteDePrueba.getFechaDeAlta());
	}

	// Los tipos de documento, de la enumeracion TipoDeDocumento
	@Test
	public void ClienteDeserializer_DadoJSONDeCliente_SeDeserializaCorrectamenteElTipoDeDocumento() {

		Cliente clienteDePrueba = parser.fromJson(readerClienteDePrueba, Cliente.class);
		TipoDeDocumento tipoDeDocumentoObjetivo = TipoDeDocumento.DNI;

		assertEquals(tipoDeDocumentoObjetivo, clienteDePrueba.getTipoDeDocumento());
	}

	// Y finalemnte los dispositivos, de la clase Dispositivo
	@Test
	public void DispositivoDeserializer_DadoJSONDeDispositivo_ElParserLoDeserializaCorrectamente() {

		Dispositivo dispositivoDePrueba = parser.fromJson(readerDispositivoDePrueba, Dispositivo.class);
		Dispositivo dispositivoObjetivo = new Dispositivo("Microondas", 15.0, false);

		assertTrue(dispositivoObjetivo.esIgualA(dispositivoDePrueba));
	}

	// Testeamos tambien los casos borde, como:
	// Deserializar un cliente que no tiene dispositivos
	@Test
	public void ClienteDeserializer_DadoJSONDeClienteSinDispositivos_SuListaDeDispositivosNoTieneElementos() {

		Cliente clienteDePrueba = parser.fromJson(readerClienteSinDispositivos, Cliente.class);

		assertSame(0, clienteDePrueba.cantidadDeDispositivos());
	}

}
