package grape.common.tools;


import grape.common.exception.runtime.InvalidParamsException;
import grape.common.exception.runtime.RDataNotExistException;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 一个常用工具集合
 * 为了一致的编码思路，service实现类和controller继承该以实现方便的工具方法
 * 其它类也可以继承来达到一致的目的
 * Created by yangwei
 * Created at 2019/7/25 9:34
 */
public interface ToolService {
    /**
     * 比较字符串相同
     * @param cs1
     * @param cs2
     * @return
     */
    default boolean isEqual(final CharSequence cs1, final CharSequence cs2){
        return StringUtils.equals(cs1,cs2);
    }
    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    default boolean isEqualAny(String str,String ...testStr){

        return StringUtils.containsAny(str,testStr);
    }

    /**
     * 判断是否为空
     * @param list
     * @return true 如果是null或size为0
     */
    default boolean isEmpty(Collection<?> list){
        return list == null || list.isEmpty();
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    default boolean isStrEmpty(String str){
        return StringUtils.isEmpty(str);
    }

    /**
     * 是否任何一个为空
     * @param str
     * @return
     */
    default boolean isStrAnyEmpty(String ...str){
        return StringUtils.isAnyEmpty(str);
    }

    /**
     * 是否以某个字符串开始
     * @param str
     * @param prefix
     * @return
     */
    default boolean isStrStart(String str,String prefix){
        return StringUtils.startsWith(str,prefix);
    }

    /**
     * 转拼音
     * @param hanzi
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    default PinyinDto getPinyin(String hanzi) throws BadHanyuPinyinOutputFormatCombination {
        return getPinyin(hanzi,false);
    }
    /**
     * 转拼音
     * @param hanzi
     * @param caps 返回大小写
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    default PinyinDto getPinyin(String hanzi,boolean caps) throws BadHanyuPinyinOutputFormatCombination {
        PinyinDto pinyinDto = new PinyinDto();
        pinyinDto.setPinyins(new ArrayList<>());
        pinyinDto.setOriginHanzi(hanzi);
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        char[] chars = hanzi.toCharArray();

        for (char aChar : chars) {

            String[] strings = PinyinHelper.toHanyuPinyinStringArray(aChar, format);
            if (strings == null || strings.length == 0) {
                continue;
            }
            String defaultPinyin = strings[0];
            String defaultPinyinFirst = defaultPinyin.substring(0,1);
            pinyinDto.getPinyins().add(strings);
            if (isStrEmpty(pinyinDto.getFirst())) {
                pinyinDto.setFirst(defaultPinyinFirst);
            }
            if (isStrEmpty(pinyinDto.getSimple())) {
                pinyinDto.setSimple(defaultPinyinFirst);
            }else {
                pinyinDto.setSimple(pinyinDto.getSimple() + defaultPinyinFirst);

            }
            if (isStrEmpty(pinyinDto.getFull())) {
                pinyinDto.setFull(defaultPinyin);
            }else {

                pinyinDto.setFull(pinyinDto.getFull() + defaultPinyin);
            }
        }

        if(caps){
            pinyinDto.setFull(pinyinDto.getFull().toUpperCase());
            pinyinDto.setFirst(pinyinDto.getFirst().toUpperCase());
            pinyinDto.setSimple(pinyinDto.getSimple().toUpperCase());
            for (String[] pinyin : pinyinDto.getPinyins()) {
                for (int i = 0; i < pinyin.length; i++) {
                    pinyin[i] = pinyin[i].toUpperCase();
                }
            }
        }

        return pinyinDto;
    }

    /**
     * 根据属性去重
     * 结合filter使用
     * @param keyExtractor
     * @param <T>
     * @return
     */
    default <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


    /**
     * 新建一个数组
     * @param items
     * @return
     */
    default <T> List<T> newArrayList(T ...items){
        if (items != null) {
            return Stream.of(items).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 断言
     * @param obj
     * @param exceptionMsg
     */
    default public void assertNotNull(Object obj,String exceptionMsg){
        if(obj == null){
            throw new RDataNotExistException(exceptionMsg);
        }
    }
    default public void assertParamNotNull(Object obj,String exceptionMsg){
        if(obj == null){
            throw new InvalidParamsException(exceptionMsg);
        }
    }
    default public void assertParamNotEmpty(String obj,String exceptionMsg){
        if(isStrEmpty(obj)){
            throw new InvalidParamsException(exceptionMsg);
        }
    }
    default public void assertNotEmpty(Collection<?> obj, String exceptionMsg){
        if(isEmpty(obj)){
            throw new InvalidParamsException(exceptionMsg);
        }
    }
}
