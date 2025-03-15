package management.sedef.issue.kafka.producer;

import lombok.RequiredArgsConstructor;
import management.sedef.issue.kafka.event.IssueUserEvent;
import management.sedef.project.kafka.producer.ProjectProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueUserProducer {

    private static final Logger log = LoggerFactory.getLogger(IssueUserProducer.class);

    private final KafkaTemplate<String, IssueUserEvent> kafkaTemplate;

    @Value("${spring.kafka.template.issue-assignment-event-topic}")
    private String topic;

    public void sendMessage(IssueUserEvent message) {
        log.info("issue User Event Created Log: {}", message);
        kafkaTemplate.send(topic, message);
    }

}
