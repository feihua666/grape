package grape.auth.rest.client.authorization;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name = "grape-auth")
public interface AuthorizationClient {
}
