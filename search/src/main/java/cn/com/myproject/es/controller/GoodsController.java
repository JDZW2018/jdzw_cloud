package cn.com.myproject.es.controller;

import cn.com.myproject.es.bean.Goods;
import cn.com.myproject.es.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Description:商品服务控制器类
 * User: pdt
 * Date: 2018-01-15 9:52
 */
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private IGoodsService iGoodsService;

    @RequestMapping("/addGoods")
    public Goods addGoods(String goodsName,String goodsImg,double goodsPrice,String brandId,String gcId,int saleCount){
        Goods goods = new Goods();
        goods.setGoodsId(UUID.randomUUID().toString());
        goods.setGoodsName(goodsName);
        goods.setGoodsImg(goodsImg);
        goods.setGoodsPrice(goodsPrice);
        goods.setBrandId(brandId);
        goods.setGcId(gcId);
        goods.setSaleCount(saleCount);
        goods.setCreateTime(new Date().getTime());
        return iGoodsService.addGoods(goods);
    }

    @RequestMapping("/findPageGoods")
    public List<Goods> findPageGoods(String brandId,String gcId,int pageNo,int pageSize,int sortType){
        Sort sort = null;
        if(sortType == 1){// 新品，按创建时间倒叙排列
            sort = new Sort(Sort.Direction.DESC,"createTime");
        }
        if(sortType == 2){// 价格，按商品价格正序排列
            sort = new Sort(Sort.Direction.ASC,"goodsPrice");
        }
        if(sortType == 3){// 销量，按商品销量倒叙排列
            sort = new Sort(Sort.Direction.DESC,"saleCount");
        }
        Page<Goods> goodsPage = iGoodsService.findPageGoods(brandId,gcId,pageNo,pageSize,sort);
        List<Goods> goodsList = goodsPage.getContent();
        return goodsList;
    }

}