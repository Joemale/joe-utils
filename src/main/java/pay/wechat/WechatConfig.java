package pay.wechat;

public class WechatConfig {

    /**
     * 统一下单访问路径
     */
    public static final String UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 加密类型
     */
    public static final String SIGN_TYPE_MD5 = "MD5";
    public static final String SIGN_TYPE_HAMC_SHA256 = "HMAC-SHA256";
    /**
     * 交易类型
     */
    public static final String TRAGE_TYPE_JSAPI = "JSAPI";
    public static final String TRAGE_TYPE_NATIVE = "NATIVE";
    public static final String TRAGE_TYPE_APP = "APP";
//    public static final String


}
