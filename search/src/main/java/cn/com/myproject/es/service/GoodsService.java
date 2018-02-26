package cn.com.myproject.es.service;

import cn.com.myproject.es.bean.Goods;
import cn.com.myproject.es.repository.GoodsRepository;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

/**
 * Description:商品Service接口实现类
 * User: pdt
 * Date: 2018-01-15 9:30
 */
@Service
public class GoodsService implements IGoodsService{

    @Autowired
    private GoodsRepository goodsRepository;

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    @Override
    public Goods addGoods(Goods goods){
        return goodsRepository.save(goods);
    }

    /**
     * 分页查询商品列表
     *
     * @param pageNo
     * @param pageSize
     * @param sort
     * @return
     */
    @Override
    public Page<Goods> findPageGoods(String brandId, String gcId, int pageNo, int pageSize, Sort sort){
        Pageable pageable = new PageRequest(pageNo-1,pageSize,sort);
        NativeSearchQueryBuilder nsb = new NativeSearchQueryBuilder();
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        if(StringUtils.isNotBlank(brandId)){
            qb.must(QueryBuilders.matchQuery("brandId",brandId));
        }
        if(StringUtils.isNotBlank(gcId)){
            qb.must(QueryBuilders.matchQuery("gcId",gcId));
        }
        nsb.withQuery(qb);
        nsb.withPageable(pageable);
        SearchQuery searchQuery = nsb.build();
        Page<Goods> goodsPage = goodsRepository.search(searchQuery);
        return goodsPage;
    }

}