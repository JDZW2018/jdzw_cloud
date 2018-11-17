package cn.com.myproject.etc.external;

import cn.com.myproject.Message.entuty.VO.MessageRecordVO;
import cn.com.myproject.etc.message.entity.PO.MessageRecord;
import cn.com.myproject.etc.message.service.IMessageRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * @author zyh
 * @description:
 * @createtime 2018/5/3 0003
 */
@RestController
@RequestMapping(value ="/messagerecord")
@Deprecated
public class MessagesController {

    @Autowired
    private IMessageRecordService iMessageRecordService;

    @PostMapping("/insertSendAll")
    public int insert4SendAll(@RequestBody Map<String, Object> map) {
        return iMessageRecordService.insert4SendAll(Integer.valueOf(String.valueOf(map.get("pageNum"))).intValue(), Integer.valueOf(String.valueOf(map.get("pageSize"))).intValue(), map);
    }
    @PostMapping("/batchInsertSelective")
    public int batchInsertSelective(@RequestBody Map<String, Object> map) {
        return iMessageRecordService.batchInsertSelective(map);
    }
    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody MessageRecordVO recordVO) {
        recordVO.setMessageId(UUID.randomUUID().toString().replace("-", ""));
        recordVO.setCreateTime(System.currentTimeMillis());
        MessageRecord record = new MessageRecord();
        BeanUtils.copyProperties(recordVO, record);
        return iMessageRecordService.insertSelective(record);
    }
}





