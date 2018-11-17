package cn.com.myproject.etc.message.service.impl;

import cn.com.myproject.aliyun.push.AliyunPushService;
import cn.com.myproject.etc.message.entity.PO.MessageRecord;
import cn.com.myproject.etc.message.mapper.MessageRecordMapper;
import cn.com.myproject.etc.message.service.IHelperService;
import cn.com.myproject.etc.message.service.IMessageRecordService;
import cn.com.myproject.helper.entity.VO.HelperVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * @author zyh
 * @description:
 * @createtime 2018/5/3 0003
 */
@Service
@Deprecated
public class MessageRecordService implements IMessageRecordService {

    public static final Logger logger = LoggerFactory.getLogger(MessageRecordService.class);


    @Autowired
    private MessageRecordMapper messageRecordMapper;


    @Autowired
    private IHelperService helperService;

    @Autowired
    private AliyunPushService pushService;

    @Override
    public int insert4SendAll(int pageNum, int pageSize, Map<String, Object> map) {
        return 0;
    }

    @Override
    public int insertSelective(MessageRecord record) {
        int result = 0;
        String helperId = record.getRelationId();
        HelperVO helper = helperService.findHelper(helperId);
        String helperPhone = helper.getHelperPhone();
        if(2==record.getRelationType()){//发送短信
            if(StringUtils.isNotBlank(helperPhone)){
               //TODO 短信接口
            }
            logger.error("[{系统发送短信}]("+helper.getHelperName()+")[{失败}]");
        }else if(3 == record.getRelationType()) {//发送站内信
            //TODO  推送接口
        }
        result= messageRecordMapper.insertSelective(record);
        if(result > 0){
            record.setMessageId(UUID.randomUUID().toString().replace("-",""));
            record.setRelationId(record.getSendUserId());
            record.setReceiveUserId(helper.getHelperId());
            record.setCreateTime(System.currentTimeMillis());
            record.setStatus((short)1);
            record.setVersion(1);
            result = messageRecordMapper.insertSelective(record);
        }else{
            logger.error("[{}]时，数据存储失败","存储消息数据");
        }
        return result;
    }

    @Override
    public int batchInsertSelective(Map<String, Object> map) {
        return 0;
    }
}