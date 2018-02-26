package cn.com.myproject.adminuser.dao.impl;


import cn.com.myproject.adminuser.dao.BatchSysUserRoleDao;
import cn.com.myproject.adminuser.po.SysUserRole;
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
public class BatchSysUserRoleDaoImpl implements BatchSysUserRoleDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void saveBatch(List<SysUserRole> list) {

        SqlSession session =sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try{
            int i = 0;
            for(SysUserRole sysUserRole : list){
                session.insert("cn.com.myproject.sysuser.mapper.SysUserRoleMapper.addSysUserRole",sysUserRole);
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
