package grape.common.tools;

import grape.common.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 拼音结果
 * 请使用grape.common.tools.ToolService#getPinyin(java.lang.String)获取
 * Created by yangwei
 * Created at 2019/11/1 9:24
 */
@Data
public class PinyinDto extends BasePojo {


    /**
     * 原始转换的汉字
     */
    private String originHanzi;
    /**
     * 如果一个汉字有多音字，在这里
     * list index=第index个汉字的全拼音
     */
    List<String[]> pinyins;

    /**
     * 第一个字的首字母，大写
     */
    private String first;
    /**
     * 每个字的首字母，大写
     */
    private String simple;
    /**
     * 全拼，大写
     */
    private String full;
}
