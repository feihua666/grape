package grape.base.service.log.po;

import grape.common.service.po.NormalBasePo;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统日志
 * </p>
 *
 * @author yangwei
 * @since 2019-11-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_log")
public class Log extends NormalBasePo<Log> {

    private static final long serialVersionUID = 1L;

    /**
     * 表主键
     */
    @TableId("ID")
    private String id;

    /**
     * 请求id，后台自动生成
     */
    private String requestId;

    /**
     * 上一次请求的id
     */
    private String parentRequestId;

    /**
     * 日志类型,字典
     */
    private String typeDictId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 操作用户昵称，姓名，冗余字段方便查询
     */
    private String userNickname;

    /**
     * 日志内容
     */
    private String content;

    /**
     * 请求IP
     */
    private String host;

    /**
     * 请求结果状态码
     */
    private String responseHttpStatus;

    /**
     * 请求方法
     */
    private String requestHttpMethod;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 请求头
     */
    private String requestHttpHeader;

    /**
     * 请求url
     */
    private String requestHttpUrl;

    /**
     * 操作名称
     */
    private String operationName;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 执行时长（毫秒）
     */
    private String duration;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
