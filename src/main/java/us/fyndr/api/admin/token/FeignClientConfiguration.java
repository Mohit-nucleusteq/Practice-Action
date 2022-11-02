/**
*@author Prerna Goyal
 *
 */
package us.fyndr.api.admin.token;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Contract;
import feign.Feign;
import feign.Retryer;
import feign.hystrix.HystrixFeign;
import feign.okhttp.OkHttpClient;
import us.fyndr.api.admin.util.Constants;

@Configuration
public class FeignClientConfiguration {

    /**
    * This module directs Feign's http requests to
    * <a href="http://square.github.io/okhttp/">OkHttp</a>, which enables SPDY and better network
    * control.
    * @return OkHttpClient()
    */
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    /**
     *@return Feign.Builder
     */
    @Bean
    @Scope("prototype")
    public Feign.Builder actFeignBuilder() {
        return HystrixFeign.builder();
    }

    /**
     *@return Retryer
     */
    @Bean
    @Scope("prototype")
    public Retryer actRetryer() {
        return new Retryer.Default(Constants.PERIOD, Constants.MAX_PERIOD, Constants.MAX_ATTEMPTS);
    }

    /**
     *@return Contract
     */
    @Bean
    public Contract useFeignAnnotations() {
        return new Contract.Default();
    }

    /**
     *@return TokenServiceErrorDecoder
     */
    @Bean
    public TokenServiceErrorDecoder errorDecoder() {
        return new TokenServiceErrorDecoder();
    }
}
