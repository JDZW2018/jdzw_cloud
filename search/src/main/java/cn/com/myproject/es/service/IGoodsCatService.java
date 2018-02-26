package cn.com.myproject.es.service;

import cn.com.myproject.es.bean.GoodsCat;

/**
 * Description:商品分类Service接口
 * User: pdt
 * Date: 2018-01-10 16:35
 */
public interface IGoodsCatService {

    /**
     * 添加商品分类
     *
     * @param goodsCat
     * @return
     */
    GoodsCat addGc(GoodsCat goodsCat);

    /**
     * 根据父分类ID查询子分类
     *
     * @param parentId
     * @return
     */
    Iterable<GoodsCat> findGcByParentId(String parentId);

    /**
     * 根据商品分类ID查询商品分类信息
     *
     * @param gcId
     * @return
     */
    GoodsCat findGcById(String gcId);

}