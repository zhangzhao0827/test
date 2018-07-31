package com.yhsl.ipproxypools.utils;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpUtils {

    protected static Logger LOG = LoggerFactory.getLogger(HttpUtils.class);

    public static String readHtml(String url) {
        String entity = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;

        //设置超时处理
        RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).
                setSocketTimeout(30000).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);

        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
                "q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("Cache-Control", "no-cache");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Pragma", "no-cache");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        try {
            //客户端执行httpGet方法，返回响应
            httpResponse = httpClient.execute(httpGet);

            //得到服务响应状态码
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                entity = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
        } catch (ClientProtocolException e) {
            LOG.error("readHtml ClientProtocolException ", e);
        } catch (IOException e) {
            LOG.error("readHtml IOException ", e);
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }

            } catch (IOException e) {
                LOG.error("readHtml httpResponse.close() IOException", e);
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                LOG.error("readHtml httpClient.close() IOException", e);
            }
        }

        return entity;
    }

    public static boolean proxyTest(String ip, String port, String url) {
        boolean flag = false;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpHost proxy = new HttpHost(ip, Integer.parseInt(port));
        RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(5000).
                setSocketTimeout(5000).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
                "q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit" +
                "/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        try {
            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                flag = true;
            }
        } catch (Exception e) {
            LOG.error("proxyTest Exception---->{}", e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    LOG.error("proxyTest response.close() IOException", e);
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    LOG.error("proxyTest httpClient.close() IOException", e);
                }
            }
        }
        LOG.info("ip={},port={} flag={}", ip, port, flag);
        return flag;
    }
}
