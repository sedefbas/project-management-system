package management.sedef.issue.kafka.config;


import management.sedef.issue.kafka.event.IssueUserEvent;
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

@Configuration
public class IssueUserConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String kafkaAddress;

    @Value("${spring.kafka.consumer.issue.user-group-id}")
    private String issueUserGroupId;

    @Bean(name = "issueUserConsumerFactory")
    public ConsumerFactory<String, IssueUserEvent> consumerFactory() {
        JsonDeserializer<IssueUserEvent> jsonDeserializer = new JsonDeserializer<>(IssueUserEvent.class);
        jsonDeserializer.addTrustedPackages("*");
        jsonDeserializer.setUseTypeHeaders(false);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, issueUserGroupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, jsonDeserializer);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }

    @Bean(name = "issueUserKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, IssueUserEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, IssueUserEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
