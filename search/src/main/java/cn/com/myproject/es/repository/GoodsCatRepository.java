package cn.com.myproject.es.repository;

import cn.com.myproject.es.bean.GoodsCat;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Description:商品分类DAO接口
 * User: pdt
 * Date: 2018-01-10 16:33
 */
public interface GoodsCatRepository extends ElasticsearchRepository<GoodsCat,String> {



}