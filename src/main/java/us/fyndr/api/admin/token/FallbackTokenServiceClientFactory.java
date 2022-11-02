/**
*
 */
package us.fyndr.api.admin.token;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

/**
 * @author Prerna Goyal
 *
 */
@Component
public class FallbackTokenServiceClientFactory implements FallbackFactory<TokenServiceClient> {

    /**
     * create method creates an object fallback class.
     * @param cause
     * @return FallbackTokenServiceClient
     * */
    @Override
    public TokenServiceClient create(final Throwable cause) {

        return new FallbackTokenServiceClient(cause);

    }
}
