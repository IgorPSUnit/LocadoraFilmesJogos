package utilities;

import com.google.gson.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JsonUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>)
                    (src, typeOfSrc, context) -> new JsonPrimitive(src.format(FORMATTER)))
            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                    (json, typeOfT, context) -> LocalDateTime.parse(json.getAsString(), FORMATTER))
            .setPrettyPrinting()
            .create();

    public static <T> void salvar(String caminho, List<T> lista) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(lista, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> ler(String caminho, Type tipo) {
        try (FileReader reader = new FileReader(caminho)) {
            return gson.fromJson(reader, tipo);
        } catch (Exception e) {
            return null;
        }
    }
}
