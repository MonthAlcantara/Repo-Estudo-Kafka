package github.io.monthalcantara.producer.configuration;

import github.io.monthalcantara.producer.dto.CarDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${topic.name}")
    private String topic;

    //NOrmalmente isso não existe. Estou criando um tópico com a própria aplicação
    // Geralmente só vamos escutar e emitir evento
    @Bean
    public NewTopic createTopic() {
        return new NewTopic(topic, 3, (short) 1);
    }


    @Bean
    public ProducerFactory<String, CarDto> CarProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);

        // Configurando pra serializar a chave vinda de um formato String
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Configurando pra serializar o valor vinda de um formato Json
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, CarDto> kafkaTemplateBean() {
        return new KafkaTemplate<>(CarProducerFactory());
    }
}

