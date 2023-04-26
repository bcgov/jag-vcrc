package ca.bc.gov.open.vcrc.configuration;

import ca.bc.gov.open.vcrc.serializers.DateDeserializer;
import ca.bc.gov.open.vcrc.serializers.DateSerializer;
import ca.bc.gov.open.vcrc.serializers.InstantDeserializer;
import ca.bc.gov.open.vcrc.serializers.InstantSerializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    @Value("${ords.username}")
    private String username;

    @Value("${ords.password}")
    private String password;

    @Value("${ords.ords-read-timeout}")
    private String ordsReadTimeout;

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        SimpleModule module = new SimpleModule();

        module.addDeserializer(Date.class, new DateDeserializer());
        module.addSerializer(Date.class, new DateSerializer());

        module.addDeserializer(Instant.class, new InstantDeserializer());
        module.addSerializer(Instant.class, new InstantSerializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }

    private MappingJackson2HttpMessageConverter createMappingJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        var restTemplate =
                restTemplateBuilder
                        .basicAuthentication(username, password)
                        .setReadTimeout(Duration.ofSeconds(Integer.parseInt(ordsReadTimeout)))
                        .build();
        return restTemplate;
    }
}
