package utils.httpclient.get;

import constant.MatchConsts;
import exception.incorrectPathException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class GetHttpClient {

    public static String doGet(String apiUrl) {
        boolean flag = Pattern.matches(MatchConsts.REGEX_URL_PARAM,apiUrl);
        if(flag) {
            return execute(apiUrl,null,null);
        }else {
            throw new incorrectPathException(apiUrl + ":apiUrl error");
        }
    }
    public static String doGet(String apiUrl, Map<String,Object> param) {
        boolean flag = Pattern.matches(MatchConsts.REGEX_URL,apiUrl);
        if(flag) {
            return execute(apiUrl,param,null);
        }else {
            throw new incorrectPathException(apiUrl + ":apiUrl error");
        }
    }

    public static String doGet(String apiUrl,Map<String,Object> param, Map<String,Object> header) {
        boolean flag = Pattern.matches(MatchConsts.REGEX_URL,apiUrl);
        if(flag) {
            return execute(apiUrl,param,header);
        }else {
            throw new incorrectPathException(apiUrl + ":apiUrl error");
        }
    }
    private static String execute(String apiUrl, Map<String, Object> params, Map<String,Object> header) {
        // 拼接get参数
        if(params != null &&  params.isEmpty()) {
                StringBuffer param = new StringBuffer();
                int i = 0;
                for (String key : params.keySet()) {
                    if (i == 0)
                        param.append("?");
                    else
                        param.append("&");
                    param.append(key).append("=").append(params.get(key));
                    i++;
                }
                apiUrl += param;
        }
        String result = null;
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            // 设置头信息
            if(header != null && !header.isEmpty()) {
                for(String key : header.keySet()) {
                    httpGet.setHeader(key, (String) header.get(key));
                }
            }
            HttpResponse response = httpclient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();

            System.out.println("执行状态码 : " + statusCode);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
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
//        String str = doGet("https://www.baidu.com");
//        System.err.println("1"+str);
//    }
}
