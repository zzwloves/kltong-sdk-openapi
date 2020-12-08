## 使用说明

### 1、创建客户端（KltMchtClient）：
##### 1.1 KltMchtClient client = new KltMchtClient(商户号, 商户证书私钥);
##### 1.2 KltMchtClient client = new KltMchtClient(商户号, 商户证书, 证书密码)
   KltMchtClient可在系统初始化时创建，无须每次请求时都进行创建，生产环境建议使用「_1.2_」方式创建客户端
### 2、构建http请求体需要的实体
##### 2.1 用户实现content内容的类
##### 2.2 使用client.buildKltRequest(content对象)方法创建请求体需要的实体
### 3、HTTP请求
#### 用户可以把用上一步得到的KltRequest放到HTTP请求体内进行HTTP请求
### 4、对响应内容验签
#### 对于开联通的响应内容进行验签时，响应类必须继承com.githup.zzwloves.dto.KltBaseResponse，然后直接使用KltMchtClient的checkKltResponse(响应对象, 开联通公钥)方法进行验签

### 伪代码示例:
        // KltMchtClient可在系统初始化时创建，无须每次请求时都进行创建
        KltMchtClient client = new KltMchtClient(商户号, 证书, 证书密码);
    
        // 创建请求中的content
        DownloadParam param = new DownloadParam() {
            private String date;
                    
            public String getDate() {
             return date;
            }
            public void setDate(String date) {
             this.date = date;
        };
        param.setDate("20201120");
        // 通过content构建加签后的请求对象，用户可以直接使用此对象进行HTTP请求
        KltRequest<DownloadParam> request = client.buildKltRequest(param);

        //http方法为普通的HTTP请求方法，由用户自己实现，请求响应内容实体如果需要验签，请继承com.githup.zzwloves.dto.KltBaseResponse类
        DownloadRes res = http(*******);// DownloadRe为继承com.githup.zzwloves.dto.KltBaseResponse的普通类
        
        // 调用KltMchtClient的checkKltResponse(Object response, String kltPublicKey)对返回结果进行验签
        // 对返回结果是否进行验签，由用户自己决定
        boolean checkResult = client.checkKltResponse(res, 开联通公钥);
