package management.sedef.minio.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.annotation.PostConstruct;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.minio")
public class MinioConfig {

    private String endpoint;
    private Integer port;
    private String accessKey;
    private String secretKey;
    private boolean secure;
    private String bucketName;
    private long imageSize;
    private long fileSize;

    @PostConstruct
    public void init() {
        System.out.println("AccessKey: " + accessKey);
        System.out.println("SecretKey: " + secretKey);
        System.out.println("Endpoint: " + endpoint);
        System.out.println("Port: " + port);
        // Diğer parametreleri de ekleyebilirsiniz
    }

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder()
                .credentials(accessKey, secretKey)
                .endpoint(endpoint, port, secure)
                .build();
        return minioClient;
    }
}
