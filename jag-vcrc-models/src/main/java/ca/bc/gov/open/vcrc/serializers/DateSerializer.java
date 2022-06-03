package ca.bc.gov.open.vcrc.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(
            Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        // format to "yyyy-MM-dd" while sending date to JSON in wire; otherwise, REST DB only
        // accepts this format, such as Birth_Dt
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        jsonGenerator.writeString(dateFormat.format(date));
    }
}
