import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultExchange {

    // Metodo
    public ResponseExchange searchCurrency(String currency_code) {
        // Cria a variavel e coloca o nome que foi salvo em vaiaveis de ambiente
        String apiKey = System.getenv("API_KEY");

        URI address = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey +"/latest/" + currency_code);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(address)
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
//            return new Gson().fromJson(response.body(), ResponseExchange.class);
            return
        } catch (Exception e) {
            throw new RuntimeException("Moeda não encontrada.");
        }
    }
}
