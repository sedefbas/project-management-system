package management.sedef.issue.kafka.producer;


import management.sedef.issue.kafka.event.IssueMailEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class IssueMailProducer {

    private static final Logger log = LoggerFactory.getLogger(IssueMailProducer.class);

    private final KafkaTemplate<String, IssueMailEvent> kafkaTemplate;

    @Value("${spring.kafka.template.issue-mail-event-topic}")
    private String topic;

    public IssueMailProducer(KafkaTemplate<String, IssueMailEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(IssueMailEvent message) {
        log.info("issue User Event Created Log: {}", message);
        kafkaTemplate.send(topic, message);
    }
}
