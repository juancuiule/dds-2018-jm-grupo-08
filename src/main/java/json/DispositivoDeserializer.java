package json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dominio.Dispositivo;

public class DispositivoDeserializer implements JsonDeserializer<Dispositivo> {

	@Override
	public Dispositivo deserialize(JsonElement json, Type typeOf, JsonDeserializationContext context) {
		JsonObject dispositivoJson = json.getAsJsonObject();
		return new Dispositivo(dispositivoJson.get("nombre").getAsString(), dispositivoJson.get("kWh").getAsDouble(),
				dispositivoJson.get("encendido").getAsBoolean());
	}

}
