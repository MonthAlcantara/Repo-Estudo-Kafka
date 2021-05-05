package github.io.monthalcantara.producer.producer;

import github.io.monthalcantara.producer.dto.CarDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CarProducer {

    private final String topic;
    private final KafkaTemplate<String, CarDto> kafkaTemplate;

    //pegando o nome do tópico por variáveis de ambientes setados no application properties
    public CarProducer(@Value(value = "${topic.name}") String topic, KafkaTemplate<String, CarDto> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(CarDto carDto){
        // vai enviar ao topico "topic" o valor "carDto"
        kafkaTemplate.send(topic, carDto)
                // e dando erro ou sucesso, faço algo, nessse caso logo o resultado
                .addCallback(
                        success -> log.info("Mensagem enviada: " + success.getProducerRecord().value()),
                        failure -> log.info("mensagem falhou " + failure.getMessage())

                );
    }
}
