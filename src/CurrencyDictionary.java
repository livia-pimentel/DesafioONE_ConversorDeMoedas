import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CurrencyDictionary {
    // Guardará chave e valor
    private Map<String, CurrencyData> currencyMap = new HashMap<>();

    public CurrencyDictionary() {
        loadData();
    }

    // Metodo
    private void loadData() {
        String path = "currencies.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            // Desconsidera a primeira linha do arquivo
            br.readLine();

            // Lê a primeira linha de dados
            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(";"); // Separa por ;

                // Verifica as colunas
                if (fields.length >= 3) {
                    String code = fields[0].trim();
                    String name = fields[1].trim();
                    String country = fields[2].trim();

                    // Cria o record e grava o mapa
                    CurrencyData currency = new CurrencyData(code, name, country);
                    currencyMap.put(code, currency);
                }

                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());;
        }
    }

    public Map<String, CurrencyData> getAllCurrencies() {
        return currencyMap;
    }

}
