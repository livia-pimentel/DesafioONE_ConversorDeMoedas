public class Main {
    public static void main(String[] args) {
        // Configuração da Minha API Key como variavel de ambiente
        String myApiKey = System.getenv("API_KEY");
        System.out.println("Minha chave é: " + myApiKey);
    }
}
