package com.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class JacksonConfig {

    private static final String PATTERN_FORMAT = "uuuu-MM-dd HH:mm:ss";

    @Bean
    public SimpleModule simpleModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeJsonSerializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeJsonDeserializer());
        return module;
    }

    public static class LocalDateTimeJsonSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime var1, JsonGenerator var2, SerializerProvider var3)
                throws IOException {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                                                        .withResolverStyle(ResolverStyle.STRICT);
            var2.writeString(var1.format(format));
        }
    }

    public static class LocalDateTimeJsonDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                                                        .withResolverStyle(ResolverStyle.STRICT);
            return LocalDateTime.parse(jsonParser.getValueAsString(), format);
        }
    }
}
