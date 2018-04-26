package app.retake.parser;

import app.retake.io.api.FileIO;
import app.retake.parser.interfaces.Parser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component(value = "JSONParser")
public class JSONParser implements Parser {
    private Gson gson;

    @Autowired
    private FileIO fileIO;

    public JSONParser() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .setDateFormat("dd-MM-yyyy")
                .create();
    }
    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException {
        T object = this.gson.fromJson(fileContent, objectClass);
        return object;
    }

    @Override
    public <T> String write(T object) throws IOException, JAXBException {
        String jsonContent = this.gson.toJson(object);
        return jsonContent;
    }
}
