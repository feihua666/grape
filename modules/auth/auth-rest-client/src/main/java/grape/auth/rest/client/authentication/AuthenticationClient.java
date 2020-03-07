package grape.auth.rest.client.authentication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Component
@FeignClient(name = "grape-auth")
public interface AuthenticationClient {


    /**
     * 参见：
     * @see org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint#checkToken(java.lang.String)
     * @param token
     * @return
     * {
     *     "user_name": "2",
     *     "scope": [
     *         "read"
     *     ],
     *     "active": true,
     *     "exp": 1583336689,
     *     "authorities": [
     *         "user",
     *         "ROLE_empty",
     *         "ROLE_superadmin",
     *         "empty"
     *     ],
     *     "jti": "30c0eaaf-0f2a-4f35-b5d9-b48183a3346c",
     *     "client_id": "test_client"
     * }
     * 错误信息：
     * {
     *     "error": "invalid_token",
     *     "error_description": "Cannot convert access token to JSON"
     * }
     */
    @PostMapping(value = "/oauth/check_token")
    public Map<String, ?> checkToken(String token);

}
