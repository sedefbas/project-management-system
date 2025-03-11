package management.sedef.project.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class ProjectProducer {

    private static final Logger log = LoggerFactory.getLogger(ProjectProducer.class);

    private final KafkaTemplate<String, ProjectUserEvent> kafkaTemplate;

    @Value("${spring.kafka.template.project-user-event-topic}")
    private String topic;

    public ProjectProducer(KafkaTemplate<String, ProjectUserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(ProjectUserEvent message) {
        log.info("Project User Event Created Log: {}", message);
        kafkaTemplate.send(topic, message);
    }
}
