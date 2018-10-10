package cn.com.myproject.filter;

import cn.com.myproject.redis.IRedisService;
import cn.com.myproject.util.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tianfusheng
 * @date 2018/4/23 14:06
 * @desc
 **/
public class AccessFilter extends ZuulFilter {

    private static final String[] str = new String[]{
            "/**/b2b/**",

            "/**/login/**","/**/error*","/**/swagger-resources/**","/**/swagger-ui.html","/**/swagger.json",
            "/**/search/**","/**/v2/api-docs",
            "/**/user/findPwd",
            "/**/userRegister/**","/**/static/**","/**/wap/**","/**/webjars/**","/**/helperRegister/**",
            "/**/cart/**",
            "/**/goodsB2C/**","/**/goodsB2BNat/**","/**/goodsB2B/**","/**/goodsO2O/**","/**/goods/**",
            "/**/pcindex/**", "/**/pcb2bindex/**",
            "/**/SeckillGoodsB2c/searchAppHomePage","/**/searchGoodsByCayId",
            "/**/register/sendCode",
            "/**/appLoginContriller/appVerify","/**/appLoginContriller/appBound",
            "/**/category/**","/**/brand/**","/**/goodsEvaluate/search**","/**/goodsEvaluate/countEvaluateByLevel",
            "/**/api/region/**","/**/priority/**","/**/thirdpartyLoginController/**",
            "/**/SeckillGoodsB2c/**","/**/welfareOrder/searchListByState","/**/welfareOrder/searchByNo",
            "/**/welfareOrder/queryAllWelfareList","/**/welfareOrder/queryHomePageWelfareList",
            "/**/helper/recommendHelper","/**/welfareOrder/selectDetailById",
            "/**/insert","/**/message/**","/**/helpCenter/**",
            "/**/task/**","/**/orderTask/**","/**/serviceOrderTimer/**","/**/opertionOrderTimer/**",
            "/**/welfareJoin/**",
            "/**/serve/searchByCatyName","/**/messageInfo/searchMessageInfoById",
            "/**/sts/token/**","/**/serveCaty/searchTwoServeCaty","/**/appcarousel/getAppCarousel",
            "/**/redPacket/findAllTaskCoord","/**/redPacket/findAllBlueCoord",
            "/**/o2oSupplier/**","/**/operatingApi/searchNearbyCountryId","/**/gadHelper/takeGadMapHelper",
            "/**/appsVM/appsUpdateCheck","/**/shopSettingController/queryShopSettingById","/**/discountRedPacket/findDiscountByShopId",
            "/**/user/selectPhoneByToken","/**/operatingApi/insertApi","/**/supplierEnter/getByPhone",
            "/**/wxJspLoginContriller/**","/**/Allinpay/bindType/supportType","/**/redPacket/getOper","/**/shop/shopIndex",
            "/**/merchant/getMerchantDetailedById","/**/o2o/order/offlinePay",
            "/**/user/getUserIfExistByPhone","/**/o2o/order/qrCodeProfitInfo","/**/o2o/order/dealQrCodeProfit","/**/o2o/order/offlinePayZFB"
    };
    @Autowired
    private IRedisService redisService;

    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }
    @Override
    public int filterOrder() {
        return 0;
    }
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        ctx.getResponse().setHeader("Access-Control-Allow-Origin", "*");//fixme 线上修改
        PathMatchingResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
        String uri = request.getRequestURI();
        for (String s :str){
            if(resourceLoader.getPathMatcher().match(s, uri)){
                return false;
            }
        }
        return true;
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        try {
            log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
            String accessToken = getToken(request);
            if(accessToken == null|| StringUtils.isEmpty(accessToken)) {
                log.warn("access token is empty");
                ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
                //ctx.setResponseStatusCode(401);// 设置响应状态码
                ctx.setResponseBody("{\"result\":\"1\",\"message\":\"tokenIsNull\",\"errorCode\":\"00001\",\"data\":[]}");
                return null;
            }
            Boolean b = redisService.containKey(accessToken);
            if(!b){
                log.warn("access token is timeOut");
                ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
                //ctx.setResponseStatusCode(401);// 设置响应状态码
                ctx.setResponseBody("{\"result\":\"1\",\"message\":\"tokenTimeOut\",\"errorCode\":\"00002\",\"data\":[]}");

                return null;
            }
            log.info("access token ok");
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            log.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "accessFilter", "run()", e.getMessage());
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            //ctx.setResponseStatusCode(401);// 设置响应状态码
            ctx.setResponseBody("{\"result\":1,\"message\":\"访问失败,gateway服务器错误\",\"errorCode\":\"00004\",\"data\":[]}");
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
            return null;
        }
    }
    private String getToken(HttpServletRequest request){
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            token = request.getParameter("token");
        }
        if(StringUtils.isEmpty(token)){
            token = CookieUtil.getCookie("session_id",request);
        }
        return token;
    }
}