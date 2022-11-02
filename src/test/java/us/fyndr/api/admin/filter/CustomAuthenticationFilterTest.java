package us.fyndr.api.admin.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import us.fyndr.api.admin.exception.TokenServiceException;
import us.fyndr.api.admin.model.TokenUser;
import us.fyndr.api.admin.token.TokenServiceClient;

class CustomAuthenticationFilterTest {

    @InjectMocks
    private CustomAuthenticationFilter customAuthenticationFilter;
    
    @Mock
    private TokenServiceClient tokenServiceClient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOptionsMethodDoFilter() throws IOException, ServletException, TokenServiceException {

        String authToken = "authToken";

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", authToken);
        request.setRequestURI("/testURI");
        request.setMethod("OPTIONS");

        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();

        customAuthenticationFilter.doFilter(request, response, filterChain);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    public void testActuatorMethodDoFilter() throws IOException, ServletException, TokenServiceException {

        String authToken = "authToken";

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", authToken);
        request.setRequestURI("/actuator/health");

        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();

        customAuthenticationFilter.doFilter(request, response, filterChain);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
    
    @Test
    public void testAdminTypeDoFilter() throws IOException, ServletException, TokenServiceException {

        String authToken = "authToken";

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", authToken);
        request.setRequestURI("/testURI");

        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();

        TokenUser tokenUser = new TokenUser();

        tokenUser.setEmail("email@gmail.com");
        tokenUser.setObjId(5L);
        tokenUser.setBizName("bizName");
        tokenUser.setUserEntity("FYNDR");
        tokenUser.setUserRole("userRole");
        tokenUser.setGeneratedBy(0L);

        when(tokenServiceClient.fetchTokenData(authToken)).thenReturn(tokenUser);

        customAuthenticationFilter.doFilter(request, response, filterChain);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    public void testNotAdminEntityDoFilter() throws IOException, ServletException, TokenServiceException {

        String authToken = "authToken";

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", authToken);
        request.setRequestURI("/testURI");

        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();

        TokenUser tokenUser = new TokenUser();

        tokenUser.setEmail("email@gmail.com");
        tokenUser.setObjId(5L);
        tokenUser.setBizName("bizName");
        tokenUser.setUserEntity("INDIVIDUAL");
        tokenUser.setUserRole("userRole");
        tokenUser.setGeneratedBy(0L);

        when(tokenServiceClient.fetchTokenData(authToken)).thenReturn(tokenUser);

        customAuthenticationFilter.doFilter(request, response, filterChain);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());

    }

    @Test
    public void testNullAuthTokenEntityDoFilter() throws IOException, ServletException, TokenServiceException {

        String authToken = "";

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", authToken);
        request.setRequestURI("/testURI");

        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();

        TokenUser tokenUser = new TokenUser();

        tokenUser.setEmail("email@gmail.com");
        tokenUser.setObjId(5L);
        tokenUser.setBizName("bizName");
        tokenUser.setUserEntity("INDIVIDUAL");
        tokenUser.setUserRole("userRole");
        tokenUser.setGeneratedBy(0L);

        when(tokenServiceClient.fetchTokenData(authToken)).thenReturn(tokenUser);

        customAuthenticationFilter.doFilter(request, response, filterChain);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());

    }

    
    @Test
    public void testTokenServiceClientExceptionDoFilter() throws IOException, ServletException, TokenServiceException {

        String authToken = "authToken";

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", authToken);
        request.setRequestURI("/testURI");

        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();

        when(tokenServiceClient.fetchTokenData(null)).thenThrow(TokenServiceException.class);

        customAuthenticationFilter.doFilter(request, response, filterChain);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());

    }
}
