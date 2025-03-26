package management.sedef.notification.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.concurrent.TimeUnit;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableMongoRepositories(
        basePackages = "management.sedef.notification.repository", // Notification için repo paketi
        mongoTemplateRef = "notificationMongoTemplate" // MongoTemplate için referans adı
)
public class NotificationMongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String connectionString;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${mongodb.maxIdleTime:30000}") // Varsayılan değer 30 saniye
    private int maxIdleTime;

    @Value("${mongodb.appName:MySpringApp}") // Varsayılan değer
    private String appName;

    @Bean(name = "notificationMongoClient")
    public MongoClient notificationMongoClient() {
        ConnectionString connString = new ConnectionString(connectionString);

        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .retryWrites(true)
                .applyConnectionString(connString)
                .applyToConnectionPoolSettings(builder -> builder
                        .maxSize(100)
                        .minSize(5)
                        .maxConnectionLifeTime(30, TimeUnit.MINUTES)
                        .maxConnectionIdleTime(maxIdleTime, TimeUnit.MILLISECONDS)
                )
                .applyToSocketSettings(builder -> builder.connectTimeout(2000, TimeUnit.MILLISECONDS))
                .applicationName(appName)
                .build();

        return MongoClients.create(clientSettings);
    }

    @Bean(name = "notificationMongoTemplate")
    public MongoTemplate notificationMongoTemplate() {
        return new MongoTemplate(notificationMongoClient(), databaseName);
    }
}
