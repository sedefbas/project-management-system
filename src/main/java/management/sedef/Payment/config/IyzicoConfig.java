package management.sedef.Payment.config;

import org.springframework.context.annotation.Configuration;
import com.iyzipay.Options;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Configuration
@ConfigurationProperties(prefix = "iyzico") //yml dosyamda arıyor
public class IyzicoConfig {

    private String apiKey;
    private String secretKey;
    private String baseUrl;

    @Bean
    public Options iyzicoOptions() {
        Options options = new Options();
        options.setApiKey(apiKey);
        options.setSecretKey(secretKey);
        options.setBaseUrl(baseUrl);
        return options;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @PostConstruct  //bean oluştukdan sonra otomatik çalışır.
    public void init() {
        System.out.println("apiKey: " + apiKey);
        System.out.println("secretKey: " + secretKey);
        System.out.println("baseUrls: " + baseUrl);
    }
}