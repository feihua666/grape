package grape.base.service.dataconstraint.dto;

import grape.base.service.dataconstraint.po.DataObject;
import grape.base.service.dataconstraint.po.DataScope;
import grape.common.service.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 数据对象和数据范围关系，数据传输对象
 * 主要用来作为当前登录用户属性使用
 * Created by yangwei
 * Created at 2019/12/9 10:04
 */
@Data
@AllArgsConstructor
public class DataConstraintDto extends BaseDto {

    // 当前登录用户的数据约束来自哪里，枚举
    public enum  DataConstraintFrom {
        user("user","用户直接分配的数据范围"),
        role("role","用户绑定角色分配的数据范围"),
        userPost("userPost", "用户分配的岗位关系直接分配的数据范围"),
        post("userRole","用户分配的岗位关系中岗位分配的数据范围");

        private String fromCode;
        private String fromName;
        DataConstraintFrom(String fromCode, String fromName) {
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

    /**
     * 数据对象
     */
    private DataObject dataObject;
    /**
     * 数据对象下的数据范围
     */
    private DataScope dataScope;

    // 用户正在使用的数据范围来自哪里
    private DataConstraintFrom dataConstraintFrom;
}
