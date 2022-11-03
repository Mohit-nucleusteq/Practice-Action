package us.fyndr.api.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * . FyndrAdminApiApplication class contains the main function, this is used to
 * run a whole application
 */
@SpringBootApplication
@EnableFeignClients
public class FyndrAdminApiApplication {

    /**
     * @return WebMvcConfigurer object
     */
    @Bean
    public static WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * . cors is a security concept that allows restricting the resources
             * implemented in web browsers
             */
            @Override
            public void addCorsMappings(final CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }

    /**
     * @param args is the name of string array and it stores Java command-line
     *             arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(FyndrAdminApiApplication.class, args);
    }

    /**
     * A doNothing method to resolve checkstyle.
     */
    public void doNothing() {

    }
}
