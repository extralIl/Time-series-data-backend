package com.acmclub.neuq.backend.common.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author extralIl@1141517977
 * @date 2021/8/19 11:21
 */
public class HttpUtil {

    /**
     * 带参数的get请求
     *
     * @param addr
     * @param pairs
     * @throws URISyntaxException
     */
    public static String get(String addr, List<NameValuePair> pairs) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URI uri = new URIBuilder(addr)
                .setParameters(pairs)
                .build();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = null;
        try {
            // 执行http get请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //内容写入文件
//                FileUtils.writeStringToFile(new File("E:\\devtest\\baidu-param.html"), content, "UTF-8");
//                System.out.println("内容长度：" + content.length());
                return content;
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
        return null;
    }

}
