package cn.com.myproject.es.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Description:品牌实体类
 * User: pdt
 * Date: 2018-01-09 11:33
 */
@Document(indexName = "brand",type = "brand")
public class Brand {

    @Id
    private String brandId;// 品牌ID

    private String brandName;// 品牌名称

    private String brandImg;// 品牌图片

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandImg() {
        return brandImg;
    }

    public void setBrandImg(String brandImg) {
        this.brandImg = brandImg;
    }

}