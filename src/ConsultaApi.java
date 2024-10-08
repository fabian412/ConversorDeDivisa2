import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {
    public DivisaOmdb buscarMoneda(String monedaBase, String monedaConvertir, double monto) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/e16bb3bdeb2d7d197a08bfc6/pair/" + monedaBase + "/" + monedaConvertir + "/" + monto);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), DivisaOmdb.class);
        } catch (Exception e) {
            throw new RuntimeException("No se encontro la moneda!");

        }
    }
}
