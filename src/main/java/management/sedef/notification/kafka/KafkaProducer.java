package management.sedef.notification.kafka;

import management.sedef.notification.config.NotificationEvent;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;


@Service
public class KafkaProducer {

    private static final Logger log = (Logger) LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, NotificationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${spring.kafka.template.notification-created-topic}")
    private String defaultTopic;


    public void sendMessage(NotificationEvent message) {
        System.out.println("Notification Event Created Sout: ");
        log.info("Notification Event Created Log: ");
        kafkaTemplate.send(defaultTopic, message);
    }
}
