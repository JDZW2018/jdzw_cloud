package cn.com.myproject.es.service;

import cn.com.myproject.es.bean.Brand;
import cn.com.myproject.es.repository.BrandRepository;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Description:品牌Service接口实现类
 * User: pdt
 * Date: 2018-01-09 12:14
 */
@Service
public class BrandService implements IBrandService{

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private Client client;

    /**
     * 添加品牌
     *
     * @param brand
     * @return
     */
    @Override
    public Brand addBrand(Brand brand){
        return brandRepository.save(brand);
    }

    /**
     * 根据品牌ID查询品牌
     *
     * @param brandId
     * @return
     */
    @Override
    public Brand findById(String brandId){
        return brandRepository.findById(brandId).orElse(null);
    }

    /**
     * 查询所有品牌
     *
     * @return
     */
    @Override
    public Iterable<Brand> findAll(){
        return brandRepository.findAll();
    }

    /**
     * 分页查询品牌
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Brand> findPageBrand(int pageNo, int pageSize){
        Pageable pageable = new PageRequest(pageNo-1,pageSize);
        Page<Brand> brandPage = brandRepository.findAll(pageable);
        return  brandPage;
    }

    /**
     * 更新品牌
     *
     * @param brand
     * @return
     */
    @Override
    public void modifyBrand(Brand brand) throws Exception{
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
                .field("brandName",brand.getBrandName())
                .field("brandImg",brand.getBrandImg()).endObject();
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("brand");
        updateRequest.type("brand");
        updateRequest.id(brand.getBrandId());
        updateRequest.doc(builder);
        client.update(updateRequest).get();
    }

    /**
     * 根据品牌ID删除品牌
     *
     * @param brandId
     * @return
     */
    @Override
    public void removeBrandById(String brandId){
        brandRepository.deleteById(brandId);
    }

}