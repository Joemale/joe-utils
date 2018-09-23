package utils.httpclient.post;

import com.alibaba.fastjson.JSONObject;
import constant.MatchConsts;
import exception.incorrectPathException;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class postHttpClient {

    // 用于获取连接池
    private static PoolingHttpClientConnectionManager connMgr;
    // 获取配置一些环境属性
    private static RequestConfig requestConfig;
    // 超时时间
    private static final int MAX_TIMEOUT = 60000;

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        // 最大并发数
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        // 获取RequestConfig配置器
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        // 在提交请求之前 测试连接是否可用
        configBuilder.setStaleConnectionCheckEnabled(true);
        // 构建
        requestConfig = configBuilder.build();
    }

    /**
     * post访问 -> 无参数
     * 发送 POST 请求（HTTP）
     * @param apiUrl
     * @return
     */
    public static String doPost(String apiUrl) {
       boolean flag =  Pattern.matches(MatchConsts.REGEX_URL,apiUrl);
       if (flag) {
           CloseableHttpClient httpClient = HttpClients.createDefault();
           return execute(httpClient,apiUrl,null,null);
       }else {
           // 路径错误
           throw new incorrectPathException("apiUrl is not allowed with parameters");
       }
    }

    /**
     * post访问 -> 带参数
     * 发送 POST 请求（HTTP），K-V形式
     * @param apiUrl
     * @param param
     * @return
     */
    public static String doPost(String apiUrl, Map<String,Object> param){
        boolean flag =  Pattern.matches(MatchConsts.REGEX_URL,apiUrl);
        if (flag) {
            if (param != null && !param.isEmpty()) {
                CloseableHttpClient httpClient = HttpClients.createDefault();
                return execute(httpClient, apiUrl, param, null);
            } else {
                throw new NullPointerException("param is empty");
            }
        }else {
            // 路径错误
            throw new incorrectPathException("apiUrl is not allowed with parameters");
        }
    }

    /**
     * post访问 -> 带头信息带参数
     * 发送 POST 请求（HTTP），K-V形式
     * @param apiUrl
     * @param param
     * @param header
     * @return
     */
    public static String doPost(String apiUrl, Map<String,Object> param, Map<String,Object> header) {
        boolean flag =  Pattern.matches(MatchConsts.REGEX_URL,apiUrl);
        if (flag) {
            if(param != null && !param.isEmpty()) {
                if(header != null && !header.isEmpty()) {
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    return execute(httpClient, apiUrl, param, header);
                }else {
                    throw new NullPointerException("header is empty");
                }
            }else {
                throw new NullPointerException("param is empty");
            }
        }else {
            // 路径错误
            throw new incorrectPathException("apiUrl is not allowed with parameters");
        }
    }

    /**
     * 发送 SSL POST 请求（HTTPS） 无参数
     * @return
     */
    public static String doPostSsl(String apiUrl) {
        boolean flag =  Pattern.matches(MatchConsts.REGEX_URL_HTTPS,apiUrl);
        if (flag) {
            // 创建ssl的https客户端
            CloseableHttpClient httpClient = HttpClients.custom().
                    setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).
                    setDefaultRequestConfig(requestConfig).build();
            return execute(httpClient,apiUrl,null,null);
        }else {
            throw new incorrectPathException("apiUrl is not allowed with parameters");
        }
    }

    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     * @param apiUrl
     * @param param
     * @return
     */
    public static String doPostSsl(String apiUrl, Map<String,Object> param) {
        boolean flag =  Pattern.matches(MatchConsts.REGEX_URL_HTTPS,apiUrl);
        if (flag) {
            if(param != null && !param.isEmpty()) {
                // 创建ssl的https客户端
                CloseableHttpClient httpClient = HttpClients.custom().
                        setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).
                        setDefaultRequestConfig(requestConfig).build();
                return execute(httpClient,apiUrl,param,null);
            }else {
                throw new NullPointerException("param is empty");
            }
        }else {
            throw new incorrectPathException("apiUrl is not allowed with parameters");
        }
    }

    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     * @param apiUrl
     * @param param
     * @param header
     * @return
     */
    public static String doPostSsl(String apiUrl, Map<String,Object> param, Map<String,Object> header) {
        boolean flag =  Pattern.matches(MatchConsts.REGEX_URL_HTTPS,apiUrl);
        if (flag) {
            if(param != null && !param.isEmpty()) {
                if(header != null && !header.isEmpty()) {
                    // 创建ssl的https客户端
                    CloseableHttpClient httpClient = HttpClients.custom().
                            setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).
                            setDefaultRequestConfig(requestConfig).build();
                    return execute(httpClient,apiUrl,param,header);
                }else {
                    throw new NullPointerException("header is empty");
                }
            }else {
                throw new NullPointerException("param is empty");
            }
        }else {
            throw new incorrectPathException("apiUrl is not allowed with parameters");
        }
    }


    /**
     * 通过json进行访问
     * 发送 POST 请求（HTTP），JSON形式
     * @param apiUrl
     * @param param
     * @return
     */
    public static String doPost(String apiUrl, JSONObject param) {
        return  jsonExecute(apiUrl, param);
    }


    /**
     * http(s)协议执行
     * @param httpClient
     * @param apiUrl
     * @param params
     * @param header
     * @return
     */
    private static String execute(CloseableHttpClient httpClient,String apiUrl, Map<String, Object> params,Map<String,Object> header) {
//         创建http协议客户端。
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 获取返回值
        String httpStr = null;
        // 创建post请求。
        HttpPost httpPost = new HttpPost(apiUrl);
        // 获取http响应
        CloseableHttpResponse response = null;

        try {
            // 设置配置环境
            httpPost.setConfig(requestConfig);
            if(header != null && !header.isEmpty()) {
                // 设置头信息
                for(String key :  header.keySet()) {
                    httpPost.setHeader(key, (String) header.get(key));
                }
            }
            if(params != null && !params.isEmpty()) {
                // 设置访问参数
                List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                    pairList.add(pair);
                }
                // 访问参数
                httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
            }
//            logger.debug("远程访问接口参数：" + apiUrl);
//            long startTime = System.currentTimeMillis();
            // 调用http协议
            response = httpClient.execute(httpPost);
//            long endTime = System.currentTimeMillis();
//            logger.debug("远程访问接口需要时间：" + (endTime - startTime) + "毫秒");

//            System.out.println(response.toString());
            // 返回值
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (HttpHostConnectException hhce){
            hhce.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    private static String jsonExecute(String apiUrl,JSONObject json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine().getStatusCode());
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;

    }

    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    // TODO Auto-generated method stub
                    return false;
                }

                @Override
                public void verify(String host, SSLSocket ssl)
                        throws IOException {
                    // TODO Auto-generated method stub

                }

                @Override
                public void verify(String host, X509Certificate cert)
                        throws SSLException {
                    // TODO Auto-generated method stub

                }

                @Override
                public void verify(String host, String[] cns,
                                   String[] subjectAlts) throws SSLException {
                    // TODO Auto-generated method stub

                }


            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }
//    public static void main(String[] args) {
//        Map<String, Object> paramsMess = new HashMap<String, Object>();
////        //组装返回出去数据
////        JSONObject json = new JSONObject();
////        json.put("content", "<div>【项目创建人】<a href=\"http://huangzhaoman.w3.luyouxia.net//home.html?uid=3123032346002432\" class=\"margin-right text-blue\">Pchuang</a><span> 更换 </span><a href='http://huangzhaoman.w3.luyouxia.net//home.html?uid=3123033738052608' class='margin-right text-blue'>lcp</a><span>为 项目观察员</span></div>");
////        json.put("type", 6);
////
//        paramsMess.put("pageNum", "40");
////        paramsMess.put("content",json);
//       String str = doPostSsl("https://test.alashoo.com/alashoo//wap/userInfo/getInformationList?",null);
//       System.err.println("1"+str);
//    }



}
