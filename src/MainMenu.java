import java.util.Scanner;

public class MainMenu {
    // Atributos
    private ConsultExchange consultant = new ConsultExchange();
    private Scanner scanner = new Scanner(System.in);

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
                    
                    0) Sair   
                    ******************************************************************* 
                    Esolha uma opção: 
                    """;

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
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
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
            double amount = scanner.nextDouble();

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
}
