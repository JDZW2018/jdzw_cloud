package cn.com.myproject.etc.sms.mapper;

import cn.com.myproject.etc.sms.entity.SmsSendOne;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsSendOneMapper {
    int insert(SmsSendOne one);
    SmsSendOne getByOutId(String outId);
    int countByOutId(String outId);
    int update(SmsSendOne one);
}
