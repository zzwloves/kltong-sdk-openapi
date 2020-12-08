package com.githup.zzwloves.dto;

import com.githup.zzwloves.util.StringUtils;

import java.io.Serializable;

/**
 * 开联通请求head
 *
 * @author zhuzw
 * @version <b>1.0.0</b>
 */
public class Head implements Serializable {

    private static final long serialVersionUID = 8813602726012491318L;

    private String version;
    private String signType;
    private String merchantId;
    private String sign;

    /**
     * 构建head对象
     *
     * @param merchantId 开联通系统的商户号
     */
    public Head(String merchantId) {
        if (StringUtils.isEmpty(merchantId)) {
            throw new IllegalArgumentException("商户号不能为null或者空字符串");
        }
        this.merchantId = merchantId;
        this.version = "1.0";
        this.signType = "2";
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        if (StringUtils.isEmpty(merchantId)) {
            throw new IllegalArgumentException("signType不能为null或者空字符串");
        }
        this.signType = signType;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        if (StringUtils.isEmpty(merchantId)) {
            throw new IllegalArgumentException("merchantId不能为null或者空字符串");
        }
        this.merchantId = merchantId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Head head = (Head) o;

        if (!version.equals(head.version)) return false;
        if (!signType.equals(head.signType)) return false;
        if (!merchantId.equals(head.merchantId)) return false;
        return sign.equals(head.sign);
    }

    @Override
    public int hashCode() {
        int result = version.hashCode();
        result = 31 * result + signType.hashCode();
        result = 31 * result + merchantId.hashCode();
        result = 31 * result + sign.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Head{" +
                "version='" + version + '\'' +
                ", signType='" + signType + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
