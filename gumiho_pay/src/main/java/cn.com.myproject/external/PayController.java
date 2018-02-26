package cn.com.myproject.external;

import cn.com.myproject.pay.entity.VO.PayOrder;
import cn.com.myproject.pay.factory.PayFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by liyang-macbook on 2017/8/17.
 */
@RestController
@Component
@RequestMapping("/pay")
public class PayController {

    /**
     * 统一下单
     * @param order
     * @return
     */
    @PostMapping("/excute/{type}")
    public Map<String, String>  aliPayOrder(@RequestBody PayOrder order,@PathVariable("type") String type){
        Map<String, String>  result = null;
        if(null != order){
            result =  PayFactory.getPay(type).payOrder(order);
        }
        return result;
    }

    /**
     * 统一校验
     * @param map
     * @return
     */
    @PostMapping("/check/{type}")
    public boolean wxJspCheck(@RequestBody  Map<String, String> map,@PathVariable("type") String type ){
        boolean  flag =PayFactory.getPay(type).check(map);
        return flag;
    }

}
