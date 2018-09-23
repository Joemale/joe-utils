package pay.wechat.entity;


import java.util.Date;

public class WeChatEntity {

    /**
     * appid
     */
    private String appId;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 设备号
     */
    private String deviceInfo;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    /**
     * 签名类型
     */
    private String signType;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 标价币种
     */
    private String feeType;
    /**
     * 标价金额
     */
    private String totalFee;
    /**
     * 终端ip
     */
    private String spbillCreateIp;
    /**
     * 交易起始时间
     */
    private Date timeState;
    /**
     * 交易结束时间
     */
    private Date timeExpire;
    /**
     * 订单优惠标记
     */
    private String goodsTag;
    /**
     * 通知地址
     */
    private String notifyUrl;
    /**
     * 交易类型
     */
    private String tradeType;
    /**
     * 商品id
     */
    private String productId;
    /**
     * 指定支付方式
     */
    private String limitPay;
    /**
     *   用户标记
     */
    private String openId;
    /**
     * 场景信息
     */
    private String sceneInfo;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public Date getTimeState() {
        return timeState;
    }

    public void setTimeState(Date timeState) {
        this.timeState = timeState;
    }

    public Date getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(Date timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }


    @Override
    public String toString() {
         StringBuffer buffer = new StringBuffer();
         if(appId != null) {
             buffer.append("appid="+appId);
         }
         if(mchId != null) {
             buffer.append("&mch_id="+mchId);
         }
         if(deviceInfo != null) {
             buffer.append("&device_info="+deviceInfo);
         }
         if(nonceStr != null) {
             buffer.append("&nonce_str="+nonceStr);
         }
         if(sign != null) {
             buffer.append("&sign="+sign);
         }
         if(signType != null) {
             buffer.append("&sign_type="+signType);
         }
         if(body != null) {
             buffer.append("&body="+body);
         }
         if(detail != null) {
             buffer.append("&detail="+detail);
         }
         if(attach != null) {
             buffer.append("&attach="+attach);
         }
         if(outTradeNo != null) {
             buffer.append("&out_trade_no="+outTradeNo);
         }
         if(feeType != null) {
             buffer.append("&fee_type="+feeType);
         }
         if(totalFee != null) {
             buffer.append("&total_fee="+totalFee);
         }
         if(spbillCreateIp != null) {
             buffer.append("&spbill_create_ip="+spbillCreateIp);
         }
         if(timeState != null) {
             buffer.append("&time_start="+timeState);
         }
         if(timeExpire != null) {
             buffer.append("&time_expire="+timeExpire);
         }
         if(goodsTag != null) {
             buffer.append("&goods_tag="+goodsTag);
         }
         if(notifyUrl != null) {
             buffer.append("&notify_url="+notifyUrl);
         }
         if(tradeType != null) {
             buffer.append("&trade_type="+tradeType);
         }
         if(productId != null) {
             buffer.append("&product_id="+productId);
         }
         if(limitPay != null) {
             buffer.append("&limit_pay="+limitPay);
         }
         if(openId != null) {
             buffer.append("&openid="+openId);
         }
         if(sceneInfo != null) {
             buffer.append("&scene_info="+sceneInfo);
         }
         return buffer.toString();
    }
}
