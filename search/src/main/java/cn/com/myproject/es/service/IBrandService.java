package cn.com.myproject.es.service;

import cn.com.myproject.es.bean.Brand;
import org.springframework.data.domain.Page;

/**
 * Description:品牌Service接口
 * User: pdt
 * Date: 2018-01-09 12:10
 */
public interface IBrandService {

    /**
     * 添加品牌
     *
     * @param brand
     * @return
     */
    Brand addBrand(Brand brand);

    /**
     * 根据品牌ID查询品牌
     *
     * @param brandId
     * @return
     */
    Brand findById(String brandId);

    /**
     * 查询所有品牌
     *
     * @return
     */
    Iterable<Brand> findAll();

    /**
     * 分页查询品牌
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Brand> findPageBrand(int pageNo,int pageSize);

    /**
     * 更新品牌
     *
     * @param brand
     * @return
     */
    void modifyBrand(Brand brand) throws Exception;

    /**
     * 根据品牌ID删除品牌
     *
     * @param brandId
     * @return
     */
    void removeBrandById(String brandId);

}