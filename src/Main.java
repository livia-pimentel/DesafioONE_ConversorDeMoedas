public class Main {
    public static void main(String[] args) {
        System.out.println("========= Testando consulta da API =========");
        // Instancia a classe
        ConsultExchange search = new ConsultExchange();
        // Chama o metodo searchCurrency e passa a moeda como argumento
        ResponseExchange response = search.searchCurrency("BRL");

        System.out.println(response);
        System.out.println("========= Fim do =========");
    }
}
