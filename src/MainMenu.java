import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MainMenu {
    // Atributos
    private ConsultExchange consultant = new ConsultExchange();
    private Scanner scanner = new Scanner(System.in);
    // Add o dicionário para ler o CSV
    private CurrencyDictionary dictionary = new CurrencyDictionary();

    public void printMenu() {
        var option = -1;

        while (option !=0) {
            var menu = """
                    ********************** CONVERSOR DE MOEDAS **********************
                    
                    1) Dólar => Euro
                    2) Euro => Dólar
                    3) Dólar => Real brasileiro
                    4) Real brasileiro => Dólar
                    5) Real brasileiro => Euro
                    6) Euro => Libra Esterlina
                    
                    7) Ver lista (inglês) de moedas disponíveis
                    8) Nova conversão (Escolha qualquer moeda)
                    
                    0) Sair   
                    ******************************************************************* 
                    Esolha uma opção: 
                    """;

            try {
                System.out.println(menu);

                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        convertCurrency("USD", "EUR");
                        break;
                    case 2:
                        convertCurrency("EUR", "USD");
                        break;
                    case 3:
                        convertCurrency("USD", "BRL");
                        break;
                    case 4:
                        convertCurrency("BRL", "USD");
                        break;
                    case 5:
                        convertCurrency("BRL", "EUR");
                        break;
                    case 6:
                        convertCurrency("EUR", "GBP");
                    case 7:
                        showAvailableCurrencies();
                        break;
                    case 8:
                        newConversion();
                        break;
                    case 0:
                        System.out.println("======= PROGRAMA ENCERRADO =======");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro. Por favor, digite apenas números inteiros.");

                // Limpa o buffer, para evitar loop infinito
                scanner.nextLine();

            } catch (ErrorConsultApiException e) {
                System.out.println("Erro na API: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // Metodo para fazer a conversão da opção desejada
    private void convertCurrency(String baseCurrency, String targetCurrency) {
        try {
            // Buscar a cotação da API
            ResponseExchange response = consultant.searchCurrency(baseCurrency);

            // Pega a taxa da moeda desejada na API
            Double rate = response.conversion_rates().get(targetCurrency);

            // Pergunta o valor ao usuário
            System.out.println("Digite o valor em " + baseCurrency + " que deseja converter para " + targetCurrency);
            double amount = readDoubleSafe();

            // Multiplica o valor digitado pela taxa da moeda desejada
            double finalValue = amount * rate;

            // Mostrar resultado formatado
            System.out.println("-------------------------------------");
            System.out.printf("A taxa de conversão de %s para %s é %.3f%n", baseCurrency, targetCurrency, rate);
            System.out.printf("%.2f convertido para %s é: %.2f\n", amount, targetCurrency, finalValue);
            System.out.println("-------------------------------------");

        } catch (Exception e) {
            System.out.println("Erro na conversão: " + e.getMessage());
        }
    }

    // Fica no loop até um número ser digitado
    private double readDoubleSafe() {
        while (true) {
            try{
                return scanner.nextDouble();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Digite apenas números.");
                scanner.nextLine(); // Limpa o buffer
            }
        }
    }

    private void showAvailableCurrencies() {
        System.out.println("\n***************************************************************");
        System.out.println("                    LIST OF AVAILABLE CURRENCIES");
        System.out.println("****************************************************************");

        System.out.printf("%-6s | %-35s | %s%n", "CODE", "NAME", "COUNTRY");
        System.out.println("-------+-------------------------------------+-------------------------");

        Map<String, CurrencyData> allCurrencies = dictionary.getAllCurrencies();
        Map<String, CurrencyData> sortedCurrencies = new TreeMap<>(allCurrencies);

        for (Map.Entry<String, CurrencyData> entry : sortedCurrencies.entrySet()) {
            CurrencyData c = entry.getValue();
            System.out.printf("%-6s | %-35s | %s%n", c.code(), c.name(), c.country());
        }

        System.out.println("****************************************************************");
        System.out.println("Pressione Enter para voltar...");
        scanner.nextLine(); // Limpa o buffer
        scanner.nextLine(); // Espera o Enter
    }

    private void newConversion() {
        scanner.nextLine(); // Limpa o buffer do nextInt anterior

        System.out.println("Digite a sigla da moeda BASE (ex: USD): ");
        String base = scanner.nextLine().toUpperCase().trim();

        System.out.println("Digite a sigla da moeda que deseja CONVERTER (ex: JPY):");
        String target = scanner.nextLine().toUpperCase().trim();

        // Validação
        if (base.length() != 3 || target.length() != 3) {
            System.out.println("Erro: As siglas devem ter 3 letras.");
            return;
        }

        convertCurrency(base, target);
    }

}
