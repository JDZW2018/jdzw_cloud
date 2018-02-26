package cn.com.myproject.es.repository;

import cn.com.myproject.es.bean.Brand;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Description:品牌DAO接口
 * User: pdt
 * Date: 2018-01-09 12:07
 */
public interface BrandRepository extends ElasticsearchRepository<Brand,String> {



}