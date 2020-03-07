package grape.auth.rest.authenticaion.mvc;

import grape.common.rest.mvc.SuperController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证控制器
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "认证相关接口")
public class AuthenticationController  extends SuperController {

}
