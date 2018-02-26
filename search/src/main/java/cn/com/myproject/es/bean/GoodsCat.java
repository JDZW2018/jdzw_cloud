package cn.com.myproject.es.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Description:商品分类实体类
 * User: pdt
 * Date: 2018-01-10 16:23
 */
@Document(indexName = "goodscat",type = "goodscat")
public class GoodsCat {

    @Id
    private String gcId;// 商品分类ID

    private String gcName;// 商品分类名称

    private String gcParentId;// 商品分类父类ID

    public String getGcId() {
        return gcId;
    }

    public void setGcId(String gcId) {
        this.gcId = gcId;
    }

    public String getGcName() {
        return gcName;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName;
    }

    public String getGcParentId() {
        return gcParentId;
    }

    public void setGcParentId(String gcParentId) {
        this.gcParentId = gcParentId;
    }

}