import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;

public class GeneradorDeArchivos {
    public void guardarJson(DivisaOmdb moneda, double montoConvertido) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String nombreArchivo = moneda.base_code() + "a" + moneda.target_code() + ".json";

        JsonObject jsonMoneda = gson.toJsonTree(moneda).getAsJsonObject();
        jsonMoneda.addProperty("monto_convertido", montoConvertido);

        try (FileWriter escritura = new FileWriter(nombreArchivo)) {
            escritura.write(gson.toJson(jsonMoneda));
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
            throw e;
        }
    }
}
