package sensor.elastics.demo;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

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

    public ElasticsearchOperations elasticsearchTemplate() throws Exception{
        return new ElasticsearchTemplate(client());
    }

}
