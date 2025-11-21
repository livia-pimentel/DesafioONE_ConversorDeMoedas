public interface ToConvertData {
    <T> T obtainData(String json, Class<T> targetClass);
}
