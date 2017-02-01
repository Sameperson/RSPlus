package org.sameperson.rest.filters;

import org.glassfish.jersey.internal.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
    private static final String SECURED_URL_PREFIX = "secured";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (containerRequestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
            Optional<String> any = containerRequestContext.getHeaders().get(AUTHORIZATION_HEADER).stream().findAny();
            if (any != null && any.isPresent()) {
                String authToken = any.get();
                authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
                String decodedString = Base64.decodeAsString(authToken);
                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();
                if ("admin".equals(username) && "admin".equals(password)) {
                    return;
                }
            }
            Response unauthorizedResponse = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("Forbidden.")
                    .build();
            containerRequestContext.abortWith(unauthorizedResponse);
        }
    }
}
