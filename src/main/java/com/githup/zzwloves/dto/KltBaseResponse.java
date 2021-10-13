package com.githup.zzwloves.dto;

import java.io.Serializable;

/**
 * 开联通请求基础响应
 *
 * @author zhuzw
 * @version <b>1.0.0</b>
 */
public class KltBaseResponse implements Serializable {
    private static final long serialVersionUID = -5162354542983564861L;

    private String responseCode;
    private String responseMsg;
    private String requestId;
    private String mchtId;
    private String signMsg;
    private String signType;
    private Long time;

    // 接口失败时返回的信息
    private String errorCode;
    private Object data;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMchtId() {
        return mchtId;
    }

    public void setMchtId(String mchtId) {
        this.mchtId = mchtId;
    }

    public String getSignMsg() {
        return signMsg;
    }

    public void setSignMsg(String signMsg) {
        this.signMsg = signMsg;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KltBaseResponse that = (KltBaseResponse) o;

        if (responseCode != null ? !responseCode.equals(that.responseCode) : that.responseCode != null) return false;
        if (responseMsg != null ? !responseMsg.equals(that.responseMsg) : that.responseMsg != null) return false;
        if (requestId != null ? !requestId.equals(that.requestId) : that.requestId != null) return false;
        if (mchtId != null ? !mchtId.equals(that.mchtId) : that.mchtId != null) return false;
        if (signMsg != null ? !signMsg.equals(that.signMsg) : that.signMsg != null) return false;
        if (signType != null ? !signType.equals(that.signType) : that.signType != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (errorCode != null ? !errorCode.equals(that.errorCode) : that.errorCode != null) return false;
        return data != null ? data.equals(that.data) : that.data == null;
    }

    @Override
    public int hashCode() {
        int result = responseCode != null ? responseCode.hashCode() : 0;
        result = 31 * result + (responseMsg != null ? responseMsg.hashCode() : 0);
        result = 31 * result + (requestId != null ? requestId.hashCode() : 0);
        result = 31 * result + (mchtId != null ? mchtId.hashCode() : 0);
        result = 31 * result + (signMsg != null ? signMsg.hashCode() : 0);
        result = 31 * result + (signType != null ? signType.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (errorCode != null ? errorCode.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "KltBaseResponse{" +
                "responseCode='" + responseCode + '\'' +
                ", responseMsg='" + responseMsg + '\'' +
                ", requestId='" + requestId + '\'' +
                ", mchtId='" + mchtId + '\'' +
                ", signMsg='" + signMsg + '\'' +
                ", signType='" + signType + '\'' +
                ", time=" + time +
                ", errorCode='" + errorCode + '\'' +
                ", data=" + data +
                '}';
    }
}
