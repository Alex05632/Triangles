package test.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import programExceptions.FileIsNotValidated;
import java.io.FileReader;
import java.io.IOException;

public class HelperUtil {
    private static final Logger LOG = LoggerFactory.getLogger(HelperUtil.class);

    public static JSONObject getJSON(String fileName) throws FileIsNotValidated {
        JSONObject json;
        try (FileReader reader = new FileReader("src/test/resources/jsons/" + fileName)) {
            json = (JSONObject) (new JSONParser().parse(reader));
        } catch (ParseException | IOException e) {
            LOG.error("Ошибка чтения json-файла / Файл не найден.", e);
            throw new FileIsNotValidated("Ошибка чтения JSON файла / Файл не найден.");
        }
        return json;
    }
}
