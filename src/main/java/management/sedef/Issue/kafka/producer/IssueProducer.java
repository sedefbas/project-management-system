package management.sedef.issue.kafka.producer;

import management.sedef.issue.kafka.event.IssueUserEvent;
import management.sedef.project.kafka.ProjectProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class IssueProducer {

    private static final Logger log = LoggerFactory.getLogger(ProjectProducer.class);

    private final KafkaTemplate<String, IssueUserEvent> kafkaTemplate;

    @Value("${spring.kafka.template.issue-user-event-topic}")
    private String topic;

    public IssueProducer(KafkaTemplate<String, IssueUserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(IssueUserEvent message) {
        log.info("issue User Event Created Log: {}", message);
        kafkaTemplate.send(topic, message);
    }

}
