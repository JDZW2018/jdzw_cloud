package cn.com.myproject.adminuser.dao;



import cn.com.myproject.adminuser.po.SysUser;

import java.util.List;

/**
 * Created by liyang-macbook on 2017/8/12.
 */
public interface BatchDemoDao {
    void saveBatch(List<SysUser> list);
}
