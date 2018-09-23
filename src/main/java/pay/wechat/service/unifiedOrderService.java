package pay.wechat.service;

import constant.MatchConsts;
import exception.*;
import pay.wechat.WechatConfig;
import pay.wechat.entity.WeChatEntity;
import utils.StringUtil;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class unifiedOrderService {


    /**
     * 判断随机字符串长度
     * @param str
     * @return
     */
    private static void checkLength(String str) {
        if(str.length() <= 0) {
           throw new incorrectLengthException("length:0");
        }else  {
            if(str.length() > 32) {
                throw new incorrectLengthException("Length greater than 32 bits");
            }
        }
    }

    /**
     * 验证商户订单号
     * @param str
     * @return
     */
    private static void iudgeOutTradeNo(String str) {
        String pattern =  "[0-9A-Za-z+-|*]{0,32}";
        boolean flag = Pattern.matches(pattern,str);
        if(!flag) {
            throw new incorrectLengthException("outTradeNo param error");
        }

    }

    /**
     * 转换交易时间
     * @param date
     * @return
     */
    private static String getTimeStr(Date date) {
        SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = simple.format(date);
        return str;
    }

    /**
     * 校验通知URL
     * @param url
     * @return
     */
    private static void checkNotifyUrl(String url) {
//        String pattern = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./]*)?";
        boolean flag  = Pattern.matches(MatchConsts.REGEX_URL,url);
        if(!flag) {
            throw new incorrectPathException("notifyUrl is not allowed with parameters");
        }
    }

    /**
     * 验证signType
     * @param signType
     * @return
     */
    private static void checkSignType(String signType) {
        // 当不等于MD5/HMAC-SHA256时则抛出异常
        if(!signType.equalsIgnoreCase(WechatConfig.SIGN_TYPE_MD5) && !signType.equalsIgnoreCase(WechatConfig.SIGN_TYPE_HAMC_SHA256)) {
            throw new wrongTypeException("signType is incorrect");
        }
    }

    /**
     * 获取不到ip
     * @return
     */
    private static String getHostIp() {
        String ip = null;
        try {
          ip =  Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        if(!StringUtil.isBlank(ip)) {
            return ip;
        }else {
            throw new NullPointerException("cant't get ip");
        }
    }

    /**
     * 将金额转换为分。
     * @param totalFee
     * @return
     */
    private static String changeToFen(String totalFee) {
        if(!totalFee.contains(".")) {
            return totalFee;
        }
        String priceStr = null;
        if(!StringUtil.isBlank(totalFee)) {
            double totalFeeDouble= Double.valueOf(totalFee).doubleValue();
            int fen = (int)(totalFeeDouble * 100); // 转换为分
            priceStr = Integer.toString(fen);
            return priceStr;
        }else {
            throw new NullPointerException("totalFee is empty");
        }
    }

    /**
     * 检测feeType
     * @param feeType
     */
    private static void checkFeeType(String feeType) {
        // 暂时只支持CNY 人民币
        if(!feeType.equalsIgnoreCase("CNY")) {
            throw new wrongTypeException("feeType is incorrect");
        }
    }

    /**
     * 检查两个时间的间隔是否大于1分钟
     * @param startDate
     * @param endDate
     */
    private static void checkIntervals(Date startDate, Date endDate) {
        long fen = Math.abs(startDate.getTime() - endDate.getTime()) / (1000 * 1000);
        if(fen < 1) {
            throw new incorrectTimeException("Minimum failure time interval is greater than 1 minute");
        }
    }

    /**
     * 检查tradeType
     * @param tradeType
     */
    private static void checkTradeType(String tradeType) {
        if(!tradeType.equalsIgnoreCase(WechatConfig.TRAGE_TYPE_JSAPI) && !tradeType.equalsIgnoreCase(WechatConfig.TRAGE_TYPE_NATIVE) &&
            !tradeType.equalsIgnoreCase(WechatConfig.TRAGE_TYPE_APP)) {
            throw new wrongTypeException("tradeType is incorrect");
        }
    }

    /**
     * 解析支付参数
     * @param paramUrl
     * @return
     */
    public static Map<String,String>  parsingParameters(String paramUrl) {
        Map<String,String>  paramMap = null;

        String[] paramsArray = paramUrl.split("&");


        if(paramsArray.length > 0) {
            paramMap = new HashMap<>(16);
            for (String params : paramsArray) {
                String[] paramArray = params.split("=");
                if (paramArray.length == 2) {
                    // 获取属性
                    String param = paramArray[0];
                    // 获取值
                    String value = paramArray[1];
                    paramMap.put(param,value);
                }
            }
              return paramMap;
        }
        return null;
    }

    /**
     * 验证支付参数
     * @param weChatEntity
     */
    public static boolean verifyEntity(WeChatEntity weChatEntity) {
        if(weChatEntity == null) {
            throw new NullPointerException("WeChatEntity is null ");
        }
        if(StringUtil.isBlank(weChatEntity.getAppId())) {
            throw new NullPointerException("appid is empty");
        }
        if(StringUtil.isBlank(weChatEntity.getMchId())) {
            throw new NullPointerException("mchid is empty");
        }
        if(StringUtil.isBlank(weChatEntity.getNonceStr())) {
            throw new NullPointerException("nonceStr is empty");
        }else {
            unifiedOrderService.checkLength(weChatEntity.getNonceStr());
        }
        if(StringUtil.isBlank(weChatEntity.getSign())) {
            throw new NullPointerException("sign is empty");
        }else {
            unifiedOrderService.checkLength(weChatEntity.getSign());
        }
        if (!StringUtil.isBlank(weChatEntity.getSignType())) {
            unifiedOrderService.checkSignType(weChatEntity.getSignType());
        }
        if(StringUtil.isBlank(weChatEntity.getBody())) {
            throw new NullPointerException("body is  empty");
        }
        if(StringUtil.isBlank(weChatEntity.getOutTradeNo())) {
            throw new NullPointerException("outTradeNo is empty");
        }else {
            unifiedOrderService.iudgeOutTradeNo(weChatEntity.getOutTradeNo());
        }
        if(!StringUtil.isBlank(weChatEntity.getFeeType())) {
            unifiedOrderService.checkFeeType(weChatEntity.getFeeType());
        }
        if(StringUtil.isBlank(weChatEntity.getTotalFee())) {
            throw new NullPointerException("totalFee is empty");
        }else {
            weChatEntity.setTotalFee(changeToFen(weChatEntity.getTotalFee()));
        }
        if(StringUtil.isBlank(weChatEntity.getSpbillCreateIp())) {
            throw new NullPointerException("spbillCreateIp is empty, need to be automatically obtained, please assign 127.0.0.1");
        }else {
            if(weChatEntity.getSpbillCreateIp().equals("127.0.0.1")) {
                weChatEntity.setSpbillCreateIp(unifiedOrderService.getHostIp());
            }
        }
        if(weChatEntity.getTimeState() != null || weChatEntity.getTimeExpire() != null) {
            if(weChatEntity.getTimeState() == null) {
                throw new NullPointerException("timeState is empty");
            }
            if(weChatEntity.getTimeExpire() == null) {
                throw new NullPointerException("timeExpire is empty");
            }
            unifiedOrderService.checkIntervals(weChatEntity.getTimeState(),weChatEntity.getTimeExpire());
        }
        if(StringUtil.isBlank(weChatEntity.getNotifyUrl())) {
            throw new NullPointerException("notifyUrl is empty");
        }else {
            unifiedOrderService.checkNotifyUrl(weChatEntity.getNotifyUrl());
        }
        if(StringUtil.isBlank(weChatEntity.getTradeType())) {
            throw new NullPointerException("trageType is empty");
        }else {
            unifiedOrderService.checkTradeType(weChatEntity.getTradeType());
        }
        if(StringUtil.isBlank(weChatEntity.getProductId())) {
            if(weChatEntity.getTradeType().equalsIgnoreCase(WechatConfig.TRAGE_TYPE_NATIVE)) {
                throw  new NullPointerException("productId is empty");
            }
        }
        if(!StringUtil.isBlank(weChatEntity.getLimitPay())) {
            if(!weChatEntity.getLimitPay().equalsIgnoreCase("no_credit")) {
                throw new incorrectParametersException("limitPay is not no_credit");
            }
        }
        if(StringUtil.isBlank(weChatEntity.getOpenId())) {
            if(weChatEntity.getTradeType().equalsIgnoreCase(WechatConfig.TRAGE_TYPE_JSAPI)) {
                throw new NullPointerException("openId is empty");
            }
        }
        return true;
    }

    public static String generateXml(Map<String,String> paramMap) {
        StringBuffer buffer = new StringBuffer("<xml>");
        Set<String> set = paramMap.keySet();
        for(Iterator<String> it = set.iterator();it.hasNext();) {
            String key = it.next();
            Object value = paramMap.get(key);
            buffer.append("<").append(key).append(">");
            buffer.append(value);
            buffer.append("</").append(key).append(">");
        }
        buffer.append("</xml>");
        return buffer.toString();
     }







}
