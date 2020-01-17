package grape.common.rest.security;

import grape.common.service.common.dataconstraint.RawDataConstraint;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by yangwei
 * Created at 2020/1/16 10:13
 */
@Component
@FeignClient(name = "grape-auth")
public interface UserDetailsClient {

    @GetMapping(value = "/user/inner/dataScopes")
    public List<RawDataConstraint> getDataScopesByUserId(@RequestParam String userId);

    @GetMapping(value = "/user/inner/funcs")
    public List<String> getFuncsCodeByUserId(@RequestParam String userId);

    @GetMapping(value = "/user/inner/roles")
    public List<String> getRolesCodeByUserId(@RequestParam String userId);

    @GetMapping(value = "/user/inner/pwd/userid")
    public String getPwdByUserId(@RequestParam String userId);

    @GetMapping(value = "/user/inner/pwd/identifier")
    public String getPwdByIdentifier(@RequestParam String identifier);

    @GetMapping(value = "/user/inner/pwd/identifierid")
    public String getPwdByIdentifierId(@RequestParam String identifierId);

    @GetMapping(value = "/user/inner/applicationids")
    public List<String> getApplicationIdsByUserId(@RequestParam String userId);

}
