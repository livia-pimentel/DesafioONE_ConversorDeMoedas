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
    }

    // Metodo para fazer a conversão da opção desejada
    private void convertCurrency(String baseCurrency, String targetCurrency) {
        System.out.println("Realização conversão de " + baseCurrency + " para " + targetCurrency);

        try {
            ResponseExchange response = consultant.searchCurrency(baseCurrency);
            Double taxa = response.conversion_rates().get(targetCurrency);

            System.out.println("A taxa atual é: " + taxa);
        } catch (Exception e) {
            System.out.println("Erro na conversão: " + e.getMessage());
        }
    }
}
