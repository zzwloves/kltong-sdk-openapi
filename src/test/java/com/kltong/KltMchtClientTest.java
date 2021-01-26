package com.kltong;

import com.alibaba.fastjson.JSON;
import com.githup.zzwloves.KltMchtClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2020/11/30 15:29
 */
public class KltMchtClientTest {

    private static CloseableHttpClient httpClient = initHttpClient();

    public static void main(String[] args) throws Exception {

        // KltMchtClient可在系统初始化时创建，无须每次请求时都进行创建
//        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCdLD7wjTqNadXVZnwI5JouGVZlwQJcIhD7+j/xfe7sfFwNJUd0NTdJ1NYYyGERnC7YhTZuF4OHpEDBa2ZCYrCDdtSDpNFupdQRqyrBkl1I4IebN56rFBE2FLqTwxz7CNi1+gPrzNQfVvYRFBhZs4BHYSwIbl+/6vFraW4tcb79c/Q0BY+pEiF6paQb+Qg9Fv3cIEYSFBsbvxe9TZ9QmJugJGaVzlfNfLsd9qm4Bn3jUdDlYB9OUJraUsQFAD0ugosMD+VLu9Y9SGj0AjR6qMi0nFYG4CmIJeBzzrOnsk2yYUOuDAO3juRP/QNZyJ/o9vIAZ53VWuYUP1gtPcukw8GRAgMBAAECggEAKgwGsHrPwbohCwx2PTjO6Gs92k3msDgq3AYoKhY7H0frplalRXjhGB7YwduFYF0siR8mHxCqTtTJwBTX0AWv3BOjqa4jmxrA35MvhoZ97zul+4fHKlRdgF7ORS5q59uHgjNm7iOnUdUFUi9Sjli/hhkAWycP9XtIZ6hWYdDuu8XoiblBVgkPAOJ+86qyf/LvMFYC5mQ+v+rKtWqiUPZLO+Ju/g2eHzuqyWvqFQGj/yZA2N+8lSevdzMt7/hVsDDH5sxdwANlAu4I30Flnb+oiQt7TgkUKDsOTl5AGNsXwEezQ0H4LUGR/3B161F0J8S1GS+9BevgPCtqVZa7zfxpAwKBgQDSXqvXUJzukrtYu3yWEoWmlt2ki+E5KdbCnh5XuD4SiyY+5fSX4GU/3ugoYkDuOYJRH5+g7kDVg5tEASp2Nke43eNUlaTJvKeWsBiNcZrnVEq1EkrFgN/0S11UC+JvnSuJzGsu0d2itNXB3+1KJPH5tlqndL1dG0mx3o7girzNLwKBgQC/Q6y+O6S0QPRzv18aFpJvGn1cos7lpr72+dRD8YBQWk2vA2198cCuy6Lzath97tP1vFrLj2Nn/knMHZaLFrX0KCPII+Iw80N4R5Lgvth6jMIVguI8Qb+0lCKTxVI2j+2yTccf+YDC2ceTEnkYyWqp5UYWaSEmeG2V/1pCvIQtPwKBgQDDB43cghnbfCE/9CFeqDFL7lXxdNUKv+UgbKLIxECXxHVinqISI7D6c9dbiSuVL6/HMcA13GwzBl3tv/7ztQLtOpjFFC6/8KyWIQ/CIe5wRE82a4zyabY2r6AIiTQWMenpSKCduWb2rxv1L3q34GSNEygcUzgzl1bNFf3z4euDdQKBgHf55aQ3GawQiscIpolg215iSX0Qi6Q3ItceA+7S0OCWpOBB0C8OtQVwq7jxrvfgFomxK2tBeOPE+VnWvZZmGqEbhMaI69w03H/YtePRE05ceWqw/WU7ARwvSDlVr6qWQk50x5HhGsoffjddgSTxQ71rGrn4HFlbdbJ/yYjPZEJVAoGAIaVv6orkQyVM4K7LB224jn6MFk3ugranp9qKlxVRtw8U4sJWENXlnpHJDceTq01VK1e3BGIo4I28v6UkzCDueNFV60EuvnfLd6CKSS3pwaUMOfLGjL6nK3ivGrAW/zRKm8Z4GC94gRpxOJpgNsYYLA3GWrxuFqSsq63hMU+Vv1M=";
//        KltMchtClient client = new KltMchtClient("999410755110001", privateKey);
        File file = new File("C:\\Users\\zhuzw\\Desktop\\999622953110001-1-kltong.pfx");
        KltMchtClient client = new KltMchtClient("999410755110001", file, "Y9gLE9Fo0Z");

        // 创建请求中的content
        DownloadCheckFileForJsonParam param = new DownloadCheckFileForJsonParam();
        param.setDate("20201120");
        // 通过content构建加签后的请求对象，用户可以直接使用此对象进行HTTP请求
        String request = client.buildHttpBody(param);

        //http方法为普通的HTTP请求方法，由用户自己实现
        DownloadCheckFileForJsonRes res = http("https://ipay.chinasmartpay.cn/openapi/opc/downloadCheckFileJsonData", request,
                DownloadCheckFileForJsonRes.class);

        // 调用KltMchtClient的checkKltResponse(Object response, String kltPublicKey)对返回结果进行验签
        // 对返回结果是否进行验签，由用户自己决定
        String kltPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1DJN71D7GhENjJC6aCAwkL+I5dstmyXYhG3WsZU7sWBvLu854ZCyrJ3/kF3A+03H7Aikyy9NJbrmh5/GeIHwQsOrGJ3yC7nvLQb6Tge/lpbHKXvL5XljQksNX/8O+ZmHw+lRJt4Hl9y8rDfHSvnOLoNlfPOLBsDdMxjje83gWBLCB8HywQGeH6WLj94MMHEYdzUHyUDThuvY8vqE2DOvydLsaiqq1hmfQ+mIHxhPzMT4nvFiI5nNbPxW4FnNmcwBnX8np4XqdsxQuC/CG11WZZZLl7RBZ67T4DZDIHKLjHikvivGmJhu3rA4jVxSjOpiGREW/ki5+TidjOBxsMBfHQIDAQAB";
        boolean checkResult = client.checkKltResponse(res, kltPublicKey);
        System.out.println(checkResult);

    }

    private static <T> T http(String url, String body, Class<T> responseType) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        HttpEntity httpEntity = new StringEntity(body, ContentType.APPLICATION_JSON);
        httpPost.setEntity(httpEntity);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
            throw new Exception("发送请求失败！==》" + body, e);
        }
        // 请求失败
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new Exception("请求失败==>" + response);
        }

        HttpEntity responseEntity = response.getEntity();
        InputStream content = null;
        try {
            content = responseEntity.getContent();
        } catch (IOException e) {
            throw new Exception("获取响应信息失败！", e);
        }

        try {
            return JSON.parseObject(content, responseType);
        } catch (IOException e) {
            throw new Exception("解析响应信息失败！", e);
        }
    }

    private static CloseableHttpClient initHttpClient() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContextBuilder.create().loadTrustMaterial(null, new TrustAllStrategy()).build();
        } catch (Exception e) {
            throw new RuntimeException("初始化SSLContext出现异常！", e);
        }
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext, new String[]{"TLSv1"}, null,
                NoopHostnameVerifier.INSTANCE);

        // 配置同时支持 HTTP 和 HTPPS
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslsf)
                .build();
        // 初始化连接管理器
        PoolingHttpClientConnectionManager poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        // 同时最多连接数
        poolConnManager.setMaxTotal(32);
        // 设置最大路由
        poolConnManager.setDefaultMaxPerRoute(16);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .setConnectionRequestTimeout(60000)
                .build();
        // 初始化httpClient
        return HttpClients.custom()
                .setConnectionManager(poolConnManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

}
