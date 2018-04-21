package json;
import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import dominio.Dispositivo;

public class DispositivoDeserializer implements JsonDeserializer<Dispositivo> {

    @Override
    public Dispositivo deserialize(JsonElement json, Type typeOf, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject dispositivoJson = json.getAsJsonObject();
        return new Dispositivo(
                    dispositivoJson.get("nombre").getAsString(),
                    dispositivoJson.get("kWh").getAsInt(),
                    dispositivoJson.get("encendido").getAsBoolean()
                );
    }

}
