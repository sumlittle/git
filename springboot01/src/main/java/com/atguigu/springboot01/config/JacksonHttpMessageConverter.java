package com.atguigu.springboot01.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

/**
 * 使用 Jackson 的 MessageConverter，主要是定制各种类型的 null 值的输出，时间格式等
 */
public class JacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public JacksonHttpMessageConverter() {
        ObjectMapper objectMapper = getObjectMapper();

        // Long to String
        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
//        simpleModule.addSerializer(Integer.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Integer.TYPE, ToStringSerializer.instance);
//        simpleModule.addSerializer(Double.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Double.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(LocalDateTime.class, new JsonLocalDateSerializer());//处理date
        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        objectMapper.registerModule(simpleModule);

        // Data Format
        objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"));

        // Null value
        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory().withSerializerModifier(new NullValueSerializerModifier()));
    }

    /**
     * 处理数组类型的 null 值
     */
    public class NullArrayJsonSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            if (value == null) {
                gen.writeStartArray();
                gen.writeEndArray();
            }
        }
    }



    /**
     * 处理字符串类型的 null 值
     */
    public static class NullStringJsonSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object o, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
            gen.writeString("");
        }
    }

    /**
     * 处理数字类型的 null 值
     */
    public static class NullNumberJsonSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object o, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
            gen.writeNumber("");
        }
    }

    /**
     * 处理布尔类型的 null 值
     */
    public static class NullBooleanJsonSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object o, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
            gen.writeBoolean(false);
        }
    }

    public class JsonLocalDateSerializer extends JsonSerializer<LocalDateTime> {


        @Override
        public void serialize(final LocalDateTime date, final JsonGenerator gen, final SerializerProvider provider) throws IOException, JsonProcessingException {

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String dateStr = dateTimeFormatter.format(date);
            gen.writeString(dateStr);
        }
    }

    public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext deserializationContext)
                throws IOException {
            return LocalDateTime.parse(p.getValueAsString(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        }
    }

    /**
     * 注册空值的序列化器
     */
    public class NullValueSerializerModifier extends BeanSerializerModifier {
        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
            // 循环所有的 beanPropertyWriter
            for (Object beanProperty : beanProperties) {
                BeanPropertyWriter writer = (BeanPropertyWriter) beanProperty;

                // 判断字段的类型，如果是 array，list，set 则注册 nullSerializer
                if (isArrayType(writer)) {
                    //给 writer 注册一个自己的 nullSerializer
                    writer.assignNullSerializer(new NullArrayJsonSerializer());
                } else if (isNumberType(writer)) {
                    writer.assignNullSerializer(new NullNumberJsonSerializer());
                } else if (isBooleanType(writer)) {
                    writer.assignNullSerializer(new NullBooleanJsonSerializer());
                } else if (isStringType(writer)) {
                    writer.assignNullSerializer(new NullStringJsonSerializer());
                }
            }

            return beanProperties;
        }


        /**
         * 是否是数组
         */
        private boolean isArrayType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
        }

        /**
         * 是否是 String
         */
        private boolean isStringType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return CharSequence.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz);
        }

        /**
         * 是否是 int
         */
        private boolean isNumberType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return Number.class.isAssignableFrom(clazz);
        }

        /**
         * 是否是 Boolean
         */
        private boolean isBooleanType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return clazz.equals(Boolean.class);
        }
    }
}