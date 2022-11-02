package us.fyndr.api.admin.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import com.google.common.base.Strings;

import us.fyndr.api.admin.model.TokenUser;
import us.fyndr.api.admin.token.TokenServiceClient;
import us.fyndr.api.admin.util.EntityConstants;

@Configuration
public class CustomAuthenticationFilter extends GenericFilterBean {

    /** This object is used to call token service related methods. */
    @Autowired
    private TokenServiceClient tokenServiceClient;

    /**
     * This method of the Filter is called by the container
     * each time a request/response pair is passed through the chain due to a
     * client request for a resource at the end of the chain. The FilterChain
     * passed in to this method allows the Filter to pass on the request and
     * response to the next entity in the chain.
     * */
    @Override
    public void doFilter(final ServletRequest servletRequest,
            final ServletResponse servletResponse,
            final FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authTokenHeader = "Authorization";
        String authToken = request.getHeader(authTokenHeader);

        if (isActuatorEndPoint(request) || request.getMethod().equalsIgnoreCase("OPTIONS")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (!isAdminToken(authToken)) {
            setResponseAttributesForUnauthorizedAccess(response);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * This method checks for actutors end point.
     * @param request
     * @return boolean
     * */
    private boolean isActuatorEndPoint(final HttpServletRequest request) {

        String[] actuatorEndpoints = {"/actuator/health", "/actuator/log", "/admin/campaign"};
        for (String endpoint : actuatorEndpoints) {
            if (request.getRequestURI().indexOf(endpoint) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks whether it is an admin token or not.
     * @param authToken
     * @return boolean
     * */
    private boolean isAdminToken(final String authToken) {

        if (Strings.isNullOrEmpty(authToken)) {
            return false;
        }

        try {
            TokenUser tokenUser = tokenServiceClient.fetchTokenData(authToken);
            if (tokenUser.getUserEntity().equals(EntityConstants.FYNDR.name())) {
                return true;
            }

        } catch (Exception exception) {
            logger.info("Error from Token Service Client");
            return false;
        }
        return false;

    }

    /**
     * This method set response attributes in case of unathorized access.
     * @param response
     * */
    private void setResponseAttributesForUnauthorizedAccess(
            final HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Headers", "Accept");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods",
                "OPTIONS,POST,GET,PUT");
        response.setHeader("Content-type", "application/json; charset=utf-8");

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Authentication Failed");
    }

}
