package cn.com.myproject.es.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

/**
 * Description: 附近人实体类
 * User: pdt
 * Date: 2018-01-18 14:28
 */
@Document(indexName = "person",type = "person")
public class Person {

    @Id
    private String id;// ID

    private String name;// 名字

    private String phone;// 电话

    @GeoPointField
    private String address;// 地理位置经纬度：lat纬度，lon经度 "40.715,-74.011"，如果用数组则相反[-73.983, 40.719]

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}