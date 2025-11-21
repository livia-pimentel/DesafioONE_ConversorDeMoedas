import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// Faz a Desserialização para que o Java possa usar as informações da API que vem serealizada
public class ConvertData implements ToConvertData{
    // Atributos
    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    public <T> T obtainData(String json, Class<T> targetClass) {
        return gson.fromJson(json, targetClass);
    }

    public String convertToJson(Object object) {
        return gson.toJson(object);
    }
}
