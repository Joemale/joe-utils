package pay.wechat.utils;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import constant.MatchConsts;
import encrypt.Md5Encode;
import exception.incorrectParametersException;
import pay.httpClientUtil;
import pay.wechat.WechatConfig;
import pay.wechat.entity.WeChatEntity;
import pay.wechat.service.unifiedOrderService;
import utils.StringUtil;
import utils.httpclient.post.postHttpClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeChatUtil {

    /**
     * 统一下单
     * @param weChatEntity
     * @return
     */
    public static Map<String,Object> unifiedOrder(WeChatEntity weChatEntity) {
           boolean flag =  unifiedOrderService.verifyEntity(weChatEntity);
           // 参数无误
           if(flag) {
               // 解析参数
               Map<String,String> paramMap = unifiedOrderService.parsingParameters(weChatEntity.toString());
               if(paramMap != null &&  !paramMap.isEmpty()) {
                 String xmlParam = unifiedOrderService.generateXml(paramMap);
                   if(!StringUtil.isBlank(xmlParam)) {
                       // 请求位置统一下单。
                       String result =   httpClientUtil.doPost(WechatConfig.UNIFIED_ORDER,xmlParam);
                       System.err.println(result);
                   }
               }



           }
      return null;
    }



    public static void main(String[] args) {
        WeChatEntity entity = new WeChatEntity();
        entity.setAppId("wx1a30b0de482fec0c");
        entity.setMchId("1501411171");
        entity.setNonceStr(getRandomNonceStr());
        entity.setBody("alashoo");
        entity.setOutTradeNo("12345685");
        entity.setTotalFee("0.01");
        entity.setSpbillCreateIp("127.0.0.1");
        entity.setNotifyUrl("https://test.alashoo.com/alashoo/WechatPay/getNotify");
        entity.setTradeType("APP");
        entity.setSign(generateSign(entity,"GERHRTHRTsdbeerhrthrtgngyjyt4546"));
        unifiedOrder(entity);

    }

    /**
     * 生成sign
     * @param weChatEntity
     * @Param key 秘钥
     * @return
     */
    public static String generateSign(WeChatEntity weChatEntity, String weChatKey) {
        if(weChatEntity != null) {
            if(StringUtil.isBlank(weChatEntity.getSign())) {
                weChatEntity.setSign("1");
                unifiedOrderService.verifyEntity(weChatEntity);
            }
            Map<String,String> payParamMap =  unifiedOrderService.parsingParameters(weChatEntity.toString());
            String[]  keys =  payParamMap.keySet().toArray(new String[0]);
            Arrays.sort(keys);
            StringBuffer buffer = new StringBuffer();
            for(String key : keys) {
                String val =    payParamMap.get(key);
                if(!StringUtil.isBlank(val)) {
                    buffer.append(key).append("=").append(val).append("&");
                }
            }
            buffer.append("key").append("=").append(weChatKey);

            return Md5Encode.encode(buffer.toString().replace("&sign=1","")).toUpperCase();
        }else {
            throw new NullPointerException("WeChatEntity is null");
        }
    }

    /**
     * 获取随机字符串
     * @return
     */
    public static String getRandomNonceStr(){
        // 32位
        int length = 32;
        String randomValue = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for(int i = 0;i < length; i++) {
            int index  = random.nextInt(62);
            buffer.append(randomValue.charAt(index));
        }
        return buffer.toString();

    }


}
