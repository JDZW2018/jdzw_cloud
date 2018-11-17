package cn.com.myproject.etc.message.service;

import cn.com.myproject.etc.message.entity.PO.MessageRecord;

import java.util.Map;

/**
 * @author zyh
 * @description:消息接口
 * @createtime 2018/5/3 0003
 */
@Deprecated
public interface IMessageRecordService {

    int insert4SendAll(int pageNum, int pageSize, Map<String, Object> map);

    int insertSelective(MessageRecord record);

    int batchInsertSelective(Map<String, Object> map);

}