import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultExchange {
    // Atributo
    private ConvertData converter = new ConvertData();

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

            // Verifica erros de status da API
            if (response.statusCode() == 404) {
                throw new ErrorConsultApiException("Moeda não encontrada: " + currency_code);
            }

            if (response.statusCode() != 200) {
                throw new ErrorConsultApiException("Erro na API. Status: " + response.statusCode());
            }

            return converter.obtainData(response.body(), ResponseExchange.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Não foi possível conectar com a API.", e);
        }
    }
}
