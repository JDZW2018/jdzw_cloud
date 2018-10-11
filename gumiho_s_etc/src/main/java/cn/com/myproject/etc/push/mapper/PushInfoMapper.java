package cn.com.myproject.etc.push.mapper;

import cn.com.myproject.etc.push.entity.PushInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PushInfoMapper {
    int deleteById(Integer id);

    int insert(PushInfo record);

    int insertSelective(PushInfo record);

    PushInfo selectById(Integer id);

    int updateByOutIdSelective(PushInfo record);

    int updateByPrimaryKeyWithBLOBs(PushInfo record);

    int updateByPrimaryKey(PushInfo record);

    Integer countByOutId(Long outId);

   int updateMessageStatus(String targetValue, Long outId);

    List<PushInfo> selectMessage(String targetValue);
}