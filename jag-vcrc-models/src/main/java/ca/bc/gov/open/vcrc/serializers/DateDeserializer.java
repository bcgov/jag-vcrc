package ca.bc.gov.open.vcrc.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        // Do not think we need to have this function
        return null;
    }
}
