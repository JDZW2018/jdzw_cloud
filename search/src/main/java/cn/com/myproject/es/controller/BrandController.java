package cn.com.myproject.es.controller;

import cn.com.myproject.es.bean.Brand;
import cn.com.myproject.es.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Description:品牌服务控制器类
 * User: pdt
 * Date: 2018-01-09 12:21
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private IBrandService iBrandService;

    @RequestMapping("/addBrand")
    public Brand addBrand(String brandName,String brandImg){
        Brand brand = new Brand();
        brand.setBrandId(UUID.randomUUID().toString());
        brand.setBrandName(brandName);
        brand.setBrandImg(brandImg);
        return iBrandService.addBrand(brand);
    }

    @RequestMapping("/findById")
    public Brand findById(String brandId){
        return iBrandService.findById(brandId);
    }

    @RequestMapping("/findAll")
    public List<Brand> findAll(){
        Iterable<Brand> brandIterable = iBrandService.findAll();
        List<Brand> brandList = new ArrayList<Brand>();
        if(brandIterable != null){
            Iterator<Brand> iterator = brandIterable.iterator();
            while (iterator.hasNext()){
                brandList.add(iterator.next());
            }
        }
        return brandList;
    }

    @RequestMapping("/findPageBrand")
    public List<Brand> findPageBrand(int pageNo,int pageSize){
        Page<Brand> brandPage = iBrandService.findPageBrand(pageNo,pageSize);
        List<Brand> brandList = brandPage.getContent();
        return brandList;
    }

    @RequestMapping("/modifyBrand")
    public String modifyBrand(String brandId,String brandName,String brandImg)
            throws Exception{
        Brand brand = new Brand();
        brand.setBrandId(brandId);
        brand.setBrandName(brandName);
        brand.setBrandImg(brandImg);
        iBrandService.modifyBrand(brand);
        return "更新品牌成功";
    }

    @RequestMapping("/removeBrandById")
    public String removeBrandById(String brandId){
        iBrandService.removeBrandById(brandId);
        return "删除品牌成功";
    }

}