import java.util.Map;

public record ResponseExchange(String base_code, Map<String, Double> conversion_rates) {
}
