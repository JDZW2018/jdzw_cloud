package cn.com.myproject.es.controller;

import cn.com.myproject.es.bean.GoodsCat;
import cn.com.myproject.es.service.IGoodsCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Description:商品分类服务控制器类
 * User: pdt
 * Date: 2018-01-11 13:46
 */
@RestController
@RequestMapping("/goodsCat")
public class GoodsCatController {

    @Autowired
    private IGoodsCatService iGoodsCatService;

    @RequestMapping("/addGc")
    public GoodsCat addGc(String gcName,String gcParentId){
        GoodsCat goodsCat = new GoodsCat();
        goodsCat.setGcId(UUID.randomUUID().toString());
        goodsCat.setGcName(gcName);
        goodsCat.setGcParentId(gcParentId);
        GoodsCat returnGc = iGoodsCatService.addGc(goodsCat);
        return returnGc;
    }

    @RequestMapping("/findGcByParentId")
    public List<GoodsCat> findGcByParentId(String parentId){
        Iterable<GoodsCat> goodsCatIterable = iGoodsCatService.findGcByParentId(parentId);
        List<GoodsCat> goodsCatList = new ArrayList<GoodsCat>();
        if(goodsCatIterable != null){
            Iterator<GoodsCat> iterator = goodsCatIterable.iterator();
            while (iterator.hasNext()){
                goodsCatList.add(iterator.next());
            }
        }
        return goodsCatList;
    }

    @RequestMapping("/findGcById")
    public GoodsCat findGcById(String gcId){
        return iGoodsCatService.findGcById(gcId);
    }

}