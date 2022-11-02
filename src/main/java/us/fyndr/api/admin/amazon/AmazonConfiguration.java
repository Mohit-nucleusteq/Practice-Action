package us.fyndr.api.admin.amazon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;

@Configuration
public class AmazonConfiguration {

    /**
     * AmazonSNS bean.
     * @return AmazonSNS
     * */
    @Bean
    public AmazonSNS snsClient() {
        return AmazonSNSClient.builder().withRegion(Regions.US_WEST_1).build();
    }

}
