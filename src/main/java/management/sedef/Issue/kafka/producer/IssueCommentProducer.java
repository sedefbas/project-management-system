package management.sedef.issue.kafka.producer;

import management.sedef.issue.kafka.event.IssueCommentEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class IssueCommentProducer {
    private static final Logger log = LoggerFactory.getLogger(IssueCommentProducer.class);

    private final KafkaTemplate<String, IssueCommentEvent> kafkaTemplate;

    @Value("${spring.kafka.template.issue-comment-event-topic}")
    private String topic;

    public IssueCommentProducer(KafkaTemplate<String, IssueCommentEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(IssueCommentEvent message) {
        log.info("issue User Event Created Log: {}", message);
        kafkaTemplate.send(topic, message);
    }
}
