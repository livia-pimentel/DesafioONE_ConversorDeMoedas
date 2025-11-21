import java.util.Map;

/* Os componenetes (que servem tanto como atributos como metodos no record),
devem ter o mesmo nome que consta no retorno da API
 */
public record ResponseExchange(
        String base_code,
        String time_last_update_utc,
        Map<String, Double> conversion_rates
) {}
