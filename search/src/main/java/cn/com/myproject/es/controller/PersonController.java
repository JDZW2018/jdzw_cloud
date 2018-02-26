package cn.com.myproject.es.controller;

import cn.com.myproject.es.bean.Person;
import cn.com.myproject.es.service.IPersonService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.range.geodistance.InternalGeoDistance;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Description: 附近人服务控制器类
 * User: pdt
 * Date: 2018-01-18 15:23
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private IPersonService iPersonService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    Client client;

    /**
     * 批量插入地理位置信息
     *
     * @param lat 纬度测试数据39.929986
     * @param lon 经度测试数据116.395645
     * @return
     */
    @RequestMapping("/addPerson")
    public String addPerson(double lat,double lon){
        List<Person> personList = new ArrayList<Person>(900000);
        for(int i = 100000;i < 1000000;i++){
            double max = 0.00001;
            double min = 0.000001;
            Random random = new Random();
            double s = random.nextDouble() % (max - min + 1) + max;
            DecimalFormat df = new DecimalFormat("######0.000000");
            String lons = df.format(s+lon);
            String lats = df.format(s+lat);
            Double dlon = Double.parseDouble(lons);
            Double dlat = Double.parseDouble(lats);
            Person person = new Person();
            person.setId(UUID.randomUUID().toString());
            person.setName("名字"+i);
            person.setPhone("电话"+i);
            person.setAddress(dlat + "," + dlon);
            personList.add(person);
        }
        iPersonService.bulkIndex(personList);
        return "添加数据";
    }

    /**
     * 查询附近的人
     * geo_distance: 查找距离某个中心点距离在一定范围内的位置
     * geo_bounding_box: 查找某个长方形区域内的位置
     * geo_distance_range: 查找距离某个中心的距离在min和max之间的位置
     * geo_polygon: 查找位于多边形内的地点。
     * sort可以用来排序
     *
     * @param lat 纬度测试数据39.929986
     * @param lon 经度测试数据116.395645
     * @return
     */
    @RequestMapping("/findPersonList")
    public List<Person> findPersonList(double lat,double lon) throws Exception{

        long nowTime = System.currentTimeMillis();

        List<Person> personList = new ArrayList<Person>();

        // 查询经纬度100米范围内的人
        /*GeoDistanceQueryBuilder builder = QueryBuilders.geoDistanceQuery("address")
                .point(lat,lon).distance(100, DistanceUnit.METERS);
        GeoDistanceSortBuilder sortBuilder = SortBuilders.geoDistanceSort("address",lat,lon)
                .point(lat,lon).unit(DistanceUnit.METERS).order(SortOrder.ASC);
        Pageable pageable = new PageRequest(0,50);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                .withFilter(builder).withSort(sortBuilder).withPageable(pageable);
        SearchQuery searchQuery = nativeSearchQueryBuilder.build();
        personList.addAll(elasticsearchTemplate.queryForList(searchQuery,Person.class));*/

        // 查询经纬度10千米到50千米范围内的人
        AggregationBuilder aggregation = AggregationBuilders.geoDistance("agg",new GeoPoint(lat,lon))
                .field("address")
                .unit(DistanceUnit.KILOMETERS)
                .addRange(10,50);

        GeoDistanceSortBuilder sortBuilder = SortBuilders.geoDistanceSort("address",lat,lon)
                .point(lat,lon).unit(DistanceUnit.KILOMETERS).order(SortOrder.DESC);

        GeoDistanceQueryBuilder builder = QueryBuilders.geoDistanceQuery("address")
                .point(lat,lon).distance(50, DistanceUnit.KILOMETERS);

        SearchRequestBuilder srb = client.prepareSearch("person")
                .setTypes("person")
                .setQuery(builder)
                .addAggregation(aggregation)
                .addSort(sortBuilder);

        Range range = srb.setSize(0).get().getAggregations().get("agg");
        long l = range.getBuckets().get(0).getDocCount();

        srb.setSize(20);
        srb.setFrom(0);

        SearchResponse sr = srb.get();

        // 获取锁定的地理位置信息
        SearchHits hits = sr.getHits();
        SearchHit[] searchHists = hits.getHits();
        Person person = null;
        String addressStr = null;
        GeoPoint point = null;
        for (SearchHit hit : searchHists) {
            if(hit.getSource().get("id") != null){
                person = new Person();
                person.setId((String)hit.getSource().get("id"));
                person.setName((String)hit.getSource().get("name"));
                person.setPhone((String)hit.getSource().get("phone"));
                addressStr = (String)hit.getSource().get("address");
                if(StringUtils.isNotBlank(addressStr)){
                    person.setAddress(addressStr);
                }else{
                    person.setAddress("39.929986,116.395645");
                }
                personList.add(person);
            }
        }

        System.out.println("耗时："+(System.currentTimeMillis()-nowTime)+"毫秒");

        return personList;

    }

}