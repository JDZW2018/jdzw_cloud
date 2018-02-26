package cn.com.myproject.es.service;

import cn.com.myproject.es.bean.GoodsCat;
import cn.com.myproject.es.repository.GoodsCatRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

/**
 * Description:商品分类Service接口实现类
 * User: pdt
 * Date: 2018-01-10 16:46
 */
@Service
public class GoodsCatService implements IGoodsCatService{

    @Autowired
    private GoodsCatRepository goodsCatRepository;

    /**
     * 添加商品分类
     *
     * @param goodsCat
     * @return
     */
    @Override
    public GoodsCat addGc(GoodsCat goodsCat){
        return goodsCatRepository.save(goodsCat);
    }

    /**
     * 根据父分类ID查询子分类
     *
     * @param parentId
     * @return
     */
    @Override
    public Iterable<GoodsCat> findGcByParentId(String parentId){
        NativeSearchQueryBuilder nsb = new NativeSearchQueryBuilder();
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        qb.must(QueryBuilders.matchQuery("gcParentId",parentId));
        nsb.withQuery(qb);
        SearchQuery searchQuery = nsb.build();
        return goodsCatRepository.search(searchQuery);
    }

    /**
     * 根据商品分类ID查询商品分类信息
     *
     * @param gcId
     * @return
     */
    @Override
    public GoodsCat findGcById(String gcId){
        return goodsCatRepository.findById(gcId).orElse(null);
    }

}