import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        ConvertData convertToFormatterJson = new ConvertData();

        System.out.println("========= Testando consulta da API =========");
        // Instancia a classe
        ConsultExchange search = new ConsultExchange();

        try {
            // Chama o metodo searchCurrency e passa a moeda como argumento
            ResponseExchange response = search.searchCurrency("1");
            System.out.println(convertToFormatterJson.convertToJson(response));
        } catch (ErrorConsultApiException e) {
            System.out.println("Atenção: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }


        System.out.println("========= Fim do Programa=========");
    }
}
