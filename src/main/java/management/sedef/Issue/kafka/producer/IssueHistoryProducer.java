package management.sedef.issue.kafka.producer;

import management.sedef.issue.kafka.event.IssueHistoryEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class IssueHistoryProducer {

    private static final Logger log = LoggerFactory.getLogger(IssueHistoryProducer.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.template.issue-history-event-topic}")
    private String issueHistoryTopic;

    @Value("${spring.kafka.template.issue-assignment-event-topic}")
    private String issueAssignmentTopic;

    @Value("${spring.kafka.template.issue-comment-event-topic}")
    private String issueCommentTopic;

    public IssueHistoryProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendIssueHistoryEvent(IssueHistoryEvent message) {
        log.info("Sending IssueHistoryEvent to topic: {}", message);
        kafkaTemplate.send(issueHistoryTopic, message);
    }


}