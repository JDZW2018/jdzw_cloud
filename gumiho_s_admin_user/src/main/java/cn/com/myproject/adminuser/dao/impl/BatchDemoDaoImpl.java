package cn.com.myproject.adminuser.dao.impl;


import cn.com.myproject.adminuser.dao.BatchDemoDao;
import cn.com.myproject.adminuser.po.SysUser;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liyang-macbook on 2017/8/12.
 */
@Repository
public class BatchDemoDaoImpl implements BatchDemoDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void saveBatch(List<SysUser> list) {

        SqlSession session =sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try{
            int i = 0;
            for(SysUser sysuser : list){
                session.insert("cn.com.myproject.cn.com.myproject.adminuser.mapper.SysUserMapper.addUsers",sysuser);
                i++;
                if (i % 500 == 0 ) {
                    session.commit();
                    session.clearCache();
                }
            }
            session.commit();
            //清理缓存，防止溢出
            session.clearCache();
        }catch(Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }
}
