package management.sedef.issue.kafka.config;

import management.sedef.issue.kafka.event.IssueHistoryEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class IssueHistoryConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String kafkaAddress;

    @Value("${spring.kafka.consumer.issue.history-group-id}")
    private String groupId;

    @Bean(name = "issueHistoryConsumerFactory")
    public ConsumerFactory<String, IssueHistoryEvent> consumerFactory() {
        JsonDeserializer<IssueHistoryEvent> jsonDeserializer = new JsonDeserializer<>(IssueHistoryEvent.class);
        jsonDeserializer.addTrustedPackages("*");
        jsonDeserializer.setUseTypeHeaders(false);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }

    @Bean(name = "issueHistoryKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, IssueHistoryEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, IssueHistoryEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory()); // Burada ConsumerFactory doğru şekilde kullanılıyor
        return factory;
    }
}