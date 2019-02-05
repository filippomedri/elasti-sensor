package sensor.elastics.demo;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;


import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.geo.CustomGeoModule;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import tech.uom.lib.jackson.UnitJacksonModule;

import java.io.IOException;
import java.net.InetAddress;


@Configuration
@EnableElasticsearchRepositories(basePackages = "sensor.elastics.demo.repository")
public class ESConfig {

    @Value("${elasticsearch.host}")
    private String ESHost;

    @Value("${elasticsearch.port}")
    private int ESPort;

    @Value("${elasticsearch.clustername}")
    private String ESClusterName;


    @Bean
    public Client client() throws Exception{
        Settings esSettings = Settings.builder()
                .put("cluster.name",ESClusterName)
                .build();

        return new PreBuiltTransportClient(esSettings)
                .addTransportAddress(
                        new TransportAddress(InetAddress.getByName(ESHost),ESPort));

    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws Exception{
        return new ElasticsearchTemplate(client(), new CustomEntityMapper());
    }

    public static class CustomEntityMapper implements EntityMapper {

        private final ObjectMapper objectMapper;

        public CustomEntityMapper() {
            objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            // Register Jackson Modules for Serialization of java.time.instant and javax.measurement.unit

            objectMapper.registerModule(new ParameterNamesModule())
                    .registerModule(new Jdk8Module())
                    .registerModule(new UnitJacksonModule())        //Serialization of javax.measurement.unit
                    .registerModule(new JavaTimeModule())           //Serialization of java.time.instant
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)    //
                    .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                    ;

        }

        @Override
        public String mapToString(Object object) throws IOException {
            return objectMapper.writeValueAsString(object);
        }

        @Override
        public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
            return objectMapper.readValue(source, clazz);
        }
    }

}
