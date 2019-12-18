package grape.base.service.func.dto;

import grape.base.service.func.po.Func;
import grape.common.service.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 功能分配数据对象
 * Created by yangwei
 * Created at 2019/12/18 9:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FuncAssignDto extends BaseDto {
    // 当前登录用户的数据约束来自哪里，枚举
    public enum  FuncFrom {
        user("user","用户直接分配的功能"),
        role("role","用户绑定角色分配的功能"),
        userPost("userPost", "用户分配的岗位关系直接分配的功能"),
        userPostRole("userPostRole","用户分配的岗位关系中关联角色分配的功能"),
        userPostPost("userPostPost","用户分配的岗位关系中关联岗位分配的功能");

        private String fromCode;
        private String fromName;
        FuncFrom(String fromCode, String fromName) {
            this.fromCode = fromCode;
            this.fromName = fromName;
        }
        public String getFromCode(){
            return this.fromCode;
        }
        public String getFormName(){
            return this.fromName;
        }
    }
    private FuncFrom funcFrom;
    private List<Func> funcs;
}
