import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        ConvertData convertToFormatterJson = new ConvertData();

        System.out.println("========= Testando consulta da API =========");
        // Instancia a classe
        ConsultExchange search = new ConsultExchange();
        // Chama o metodo searchCurrency e passa a moeda como argumento
        ResponseExchange response = search.searchCurrency("BRL");

        System.out.println(convertToFormatterJson.convertToJson(response));
        System.out.println("========= Fim do Programa=========");
    }
}
