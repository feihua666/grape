package grape.auth.rest.authorization.mvc;

import grape.common.rest.mvc.SuperController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 授权控制器
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "授权相关接口")
public class AuthorizationController extends SuperController {
}
