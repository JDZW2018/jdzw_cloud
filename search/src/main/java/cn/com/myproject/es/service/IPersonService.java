package cn.com.myproject.es.service;

import cn.com.myproject.es.bean.Person;
import java.util.List;

/**
 * Description: 附近人Service接口
 * User: pdt
 * Date: 2018-01-18 14:54
 */
public interface IPersonService {

    /**
     * 添加附近人
     *
     * @param person
     * @return
     */
    Person addPerson(Person person);

    /**
     * 批量操作附近人
     *
     * @param personList
     */
    void bulkIndex(List<Person> personList);

}