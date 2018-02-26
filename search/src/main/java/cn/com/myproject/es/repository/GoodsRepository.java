package cn.com.myproject.es.repository;

import cn.com.myproject.es.bean.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Description: 商品DAO接口
 * User: pdt
 * Date: 2018-01-15 9:11
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,String>{



}