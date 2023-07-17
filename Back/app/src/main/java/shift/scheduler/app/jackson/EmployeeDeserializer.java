package shift.scheduler.app.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import org.springframework.beans.factory.annotation.Autowired;
import shift.scheduler.app.models.Employee;
import shift.scheduler.app.repositories.EmployeeRepository;

import java.io.IOException;

/**
 * Custom deserializer for deserializing employees in a weekly schedule.
 *
 * Based on https://www.baeldung.com/jackson-deserialization
 */
public class EmployeeDeserializer extends StdDeserializer<Employee> {

    @Autowired
    private EmployeeRepository repository;

    public EmployeeDeserializer() {
        this(null);
    }

    public EmployeeDeserializer(Class<?> cl) {
        super(cl);
    }

    @Override
    public Employee deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Long id = node.asLong();
        return repository.findById(id).get();
    }
}
