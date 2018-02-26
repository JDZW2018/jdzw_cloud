package cn.com.myproject.es.service;

import cn.com.myproject.es.bean.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * Description:商品Service接口
 * User: pdt
 * Date: 2018-01-15 9:22
 */
public interface IGoodsService {

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    Goods addGoods(Goods goods);

    /**
     * 分页查询商品列表
     *
     * @param brandId
     * @param gcId
     * @param pageNo
     * @param pageSize
     * @param sort
     * @return
     */
    Page<Goods> findPageGoods(String brandId,String gcId,int pageNo,int pageSize, Sort sort);

}