package com.githup.zzwloves;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.githup.zzwloves.util.JsonUtils;
import com.githup.zzwloves.util.RSAUtils;
import com.githup.zzwloves.util.StringUtils;
import com.githup.zzwloves.dto.Head;
import com.githup.zzwloves.dto.KltBaseResponse;
import com.githup.zzwloves.dto.KltRequest;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

/**
 * 开联通商户客户端
 *
 * @author zhuzw
 * @version <b>1.0.0</b>
 */
public final class KltMchtClient {

    private String mchtId;
    private String privateKey;

    /**
     * 根据商户号、私钥创建客户端
     *
     * @param mchtId     商户号
     * @param privateKey 商户私钥
     */
    public KltMchtClient(String mchtId, String privateKey) {
        if (StringUtils.isEmpty(mchtId)) {
            throw new IllegalArgumentException("商户号不能为null或者空字符串");
        }
        if (StringUtils.isEmpty(privateKey)) {
            throw new IllegalArgumentException("商户私钥不能为null或者空字符串");
        }
        this.mchtId = mchtId;
        this.privateKey = privateKey;
    }

    /**
     * 根据商户号、证书和证书密码创建客户端
     *
     * @param mchtId   商户号
     * @param pfx      证书
     * @param password 证书密码
     */
    public KltMchtClient(String mchtId, File pfx, String password) {
        if (StringUtils.isEmpty(mchtId)) {
            throw new IllegalArgumentException("商户号不能为null或者空字符串");
        }
        this.mchtId = mchtId;

        if (pfx == null) {
            throw new IllegalArgumentException("证书不能为null");
        }
        if (StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("证书密码不能为null或者空字符串");
        }
        try {
            Map<String, String> map = RSAUtils.loadKeyByFile(pfx, password);
            this.privateKey = map.get(RSAUtils.PRIVATE_KEY);
        } catch (Exception e) {
            throw new RuntimeException("创建客户端出现异常！", e);
        }
    }

    /**
     * 构建开联通请求对象
     *
     * @param content 开联通请求的content
     * @param <T>     content的类型
     * @return 开联通请求对象
     */
    public <T> KltRequest<T> buildKltRequest(T content) {
        KltRequest<T> request = new KltRequest<>();
        request.setContent(content);
        Head head = new Head(mchtId);
        request.setHead(head);
        String sign = getSign(request);
        request.getHead().setSign(sign);
        return request;
    }

    /**
     * 对开联通返回结果进行验签
     *
     * @param response     开联通返回结果
     * @param kltPublicKey 开联通公钥
     * @return true：验签通过；false：验签失败
     * @throws Exception 异常
     */
    public boolean checkKltResponse(KltBaseResponse response, String kltPublicKey) throws Exception {
        String signMsg = response.getSignMsg();
        String plaintext = getPlaintext(response);
        return RSAUtils.rsa256VerifySign(plaintext, signMsg, kltPublicKey, StandardCharsets.UTF_8.name());
    }

    private String getSign(KltRequest kltRequest) {
        checkKltRequest(kltRequest);
        // 获取前置的加密原串
        String plaintext = getPlaintext(kltRequest);
        // 根据签名加密类型获取相应的加密处理方法
        try {
            return RSAUtils.rsa256Sign(plaintext, privateKey, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            throw new RuntimeException("加签失败！", e);
        }
    }

    private void checkKltRequest(KltRequest kltRequest) {
        String error = null;
        if (kltRequest == null) {
            error = "KltRequest不能为null";
        } else if (kltRequest.getHead() == null) {
            error = "KltRequest中的Head不能为null";
        } else if (StringUtils.isEmpty(kltRequest.getHead().getMerchantId())) {
            error = "KltRequest中Head的merchantId不能为null或者空字符串";
        } else if (kltRequest.getContent() == null) {
            error = "KltRequest中的content不能为null";
        }
        if (error != null) {
            throw new IllegalArgumentException(error);
        }
    }

    private String getPlaintext(KltRequest kltRequest) {
        JSONObject json = (JSONObject) JSON.toJSON(kltRequest);
        JSONObject head = (JSONObject) json.get("head");
        head.remove("sign");
        TreeMap<String, Object> treeMap = new TreeMap<>(head.getInnerMap());

        Object content = json.get("content");
        if (content instanceof JSONArray) {
            String con = ((JSONArray) content).toJSONString();
            treeMap.put("content", con);
        } else if (content instanceof JSONObject) {
            JSONObject contentObject = (JSONObject) content;
            treeMap.putAll(contentObject.getInnerMap());
        }

        return getPlaintext(treeMap);
    }

    private String getPlaintext(KltBaseResponse response) {
        JSONObject json = (JSONObject) JSONObject.toJSON(response);
        json = JsonUtils.sort(json);
        json.remove("signMsg");
        TreeMap<String, Object> treeMap = new TreeMap<>();
        for (Map.Entry<String, Object> entry : json.getInnerMap().entrySet()) {
            Object value = entry.getValue();
            if (value == null || (value instanceof String && ((String) value).trim().isEmpty())) {
                continue;
            }

            if (!(value instanceof String)) {
                value = JSON.toJSONString(value);
            }
            treeMap.put(entry.getKey(), value);
        }

        return getPlaintext(treeMap);
    }

    private String getPlaintext(TreeMap<String, Object> treeMap) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && !value.toString().trim().isEmpty()) {
                sb.append(key).append("=").append(value).append("&");
            }
        }
        if (sb.length() == 0) {
            return sb.toString();
        }
        return sb.substring(0, sb.length() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KltMchtClient that = (KltMchtClient) o;

        if (mchtId != null ? !mchtId.equals(that.mchtId) : that.mchtId != null) return false;
        return privateKey != null ? privateKey.equals(that.privateKey) : that.privateKey == null;
    }

    @Override
    public int hashCode() {
        int result = mchtId != null ? mchtId.hashCode() : 0;
        result = 31 * result + (privateKey != null ? privateKey.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "KltMchtClient{" +
                "mchtId='" + mchtId + '\'' +
                ", privateKey='" + privateKey + '\'' +
                '}';
    }
}
