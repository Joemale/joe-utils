package pay;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class httpClientUtil {


    public static String doPost(String apiUrl, String xmlInfo) {
        BufferedReader reader = null;
        try {
            // 创建http客户端
            HttpClient httpClient = new HttpClient();
            // 设置http参数
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
            PostMethod httpPost = new PostMethod(apiUrl);
            // 设置post请求参数
            RequestEntity requestEntity = new ByteArrayRequestEntity(xmlInfo.getBytes("UTF-8"));
            httpPost.setRequestEntity(requestEntity);
            // 执行
            httpClient.executeMethod(httpPost);
            // 读取结果
             reader = new BufferedReader(
                    new InputStreamReader(httpPost.getResponseBodyAsStream(),"UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            String str = null;
            while((str = reader.readLine()) != null){
                stringBuffer.append(str);
            }
             String prePayXml = stringBuffer.toString();
            return prePayXml;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
