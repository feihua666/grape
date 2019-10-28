package grape.common.rest.mvc;

/**
 * Created by yangwei
 * Created at 2019/8/21 21:17
 */
public interface WebMapper<Vo,Po> {
    /**
     * 单表po转vo
     * @param po
     * @return
     */
    Vo poToVo(Po po);
}
