package cn.com.myproject.etc.message.mapper;

import cn.com.myproject.etc.message.entity.PO.MessageRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zyh
 * @description:
 * @createtime 2018/5/4 0004
 */
@Mapper
public interface MessageRecordMapper {
    int insertSelective(MessageRecord record);
}