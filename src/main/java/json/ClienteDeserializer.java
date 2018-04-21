package json;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import dominio.Cliente;
import dominio.Dispositivo;
import dominio.TipoDeDocumento;

public class ClienteDeserializer implements JsonDeserializer<Cliente> {
        @Override
        public Cliente deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
          
            JsonObject clienteJson = json.getAsJsonObject();
            Gson parser = new GsonBuilder()
                            .registerTypeAdapter(Dispositivo.class, new DispositivoDeserializer())
                            .create();
            
          return new Cliente(
                      clienteJson.get("nombre").getAsString(),
                      clienteJson.get("apellido").getAsString(),
                      TipoDeDocumento.valueOf(clienteJson.get("tipoDeDocumento").getAsString()),
                      clienteJson.get("numeroDeDocumento").getAsInt(),
                      clienteJson.get("telefono").getAsInt(),
                      LocalDate.parse(clienteJson.get("fechaDeAlta").getAsString()),
                      clienteJson.get("domicilio").getAsString(),
                      clienteJson.get("nombreDeUsuario").getAsString(),
                      clienteJson.get("contrasena").getAsString(),
                      Arrays.asList(parser.fromJson(clienteJson.get("dispositivos").getAsString(), Dispositivo[].class))
                  );
        }

    }