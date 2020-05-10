package com.cqut.wangyu.crm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;


public class ClientUtil {

    // 每个HOST的最大连接数量
    private final static int MAX_CONN_PRE_HOST = 20;
    // 连接池的最大连接数
    private final static int MAX_CONN = 60;
    // 连接池
    private final static PoolingHttpClientConnectionManager cm;

    public final static String REQUEST_HEADER = "x-forwarded-for";

    static {
        SSLContextBuilder builder = new SSLContextBuilder();
        SSLConnectionSocketFactory sslConnectionSocketFactory = null;
        try {
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                    builder.build(), NoopHostnameVerifier.INSTANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("https", sslConnectionSocketFactory)
                .register("http", new PlainConnectionSocketFactory()).build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(MAX_CONN);
        cm.setDefaultMaxPerRoute(MAX_CONN_PRE_HOST);
    }

    public static CloseableHttpClient getHttpClient() {
//        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();//如果不采用连接池就是这种方式获取连接
        return httpClient;
    }

    /**
     * @param url
     * @param values
     * @return
     * @throws Exception
     */
    public static String httpsdoPost(String url, Map<String, String> values) throws Exception {
        HttpClient httpClient = new SSLClient();
        url = URLDecoder.decode(url, "UTF-8");
        HttpPost post = new HttpPost(url);
        post.setHeader("Accept-Encoding", "gzip, deflate");
        post.setHeader("Accept-Language", "zh-CN");
        post.setHeader("Accept", "application/json, application/xml, text/html, text/*, image/*, */*");
        post.setHeader("Authorization", "Basic QURNSU46S1lMSU4=");
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        String resultString = "";
        if (values != null && values.size() > 0) {
            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            for (String key : values.keySet()) {
                list.add(new BasicNameValuePair(key, values.get(key)));
            }
            // 解决中文乱码问题
            StringEntity entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            post.setEntity(entity);
        }
        try {
            HttpResponse response = httpClient.execute(post);
            resultString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw e;
        } finally {
            post.releaseConnection();
        }
        return resultString;
    }

    public static String doPostWithJson(String url, String json, Map<String, String> headMap) throws Exception {
        HttpResponse response;
        url = URLDecoder.decode(url, "UTF-8");
        HttpPost post = new HttpPost(url);
        post.setHeader("Accept-Encoding", "gzip, deflate");
        post.setHeader("Accept-Language", "zh-CN");
        post.setHeader("Accept", "application/json, application/xml, text/html, text/*, image/*, */*");
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        post.setHeader("Cookie", headMap.get("Cookie"));

        String resultString = "";

        StringEntity entity = new StringEntity(json, HTTP.UTF_8);
        entity.setContentType("text/json");
        post.setEntity(entity);

        try {
            HttpClient httpClient = getHttpClient();
            response = httpClient.execute(post);
            resultString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw e;
        } finally {
            post.releaseConnection();
        }
        return resultString;
    }

    /**
     * @param url
     * @param values
     * @param headvalues
     * @return
     * @throws Exception
     */
    public static String doPostwithhead(String url, Map<String, String> values, Map<String, String> headvalues) throws Exception {
        HttpResponse response;
        url = URLDecoder.decode(url, "UTF-8");
        HttpPost post = new HttpPost(url);
        post.setHeader("Accept-Encoding", "gzip, deflate");
        post.setHeader("Accept-Language", "zh-CN");
        post.setHeader("Accept", "application/json, application/xml, text/html, text/*, image/*, */*");
        post.setHeader("content-type", "application/json");

        //头部信息
        if (headvalues != null && headvalues.size() > 0) {
            for (String key : headvalues.keySet()) {
                post.setHeader(key, headvalues.get(key));
            }
        }

        String resultString = "";
        if (values != null && values.size() > 0) {
            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            for (String key : values.keySet()) {
                list.add(new BasicNameValuePair(key, values.get(key)));
            }
            // 解决中文乱码问题
            StringEntity entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            post.setEntity(entity);
        }
        try {
            HttpClient httpClient = getHttpClient();
            response = httpClient.execute(post);
            resultString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw e;
        } finally {
            post.releaseConnection();
        }
        return resultString;
    }

    public static String doPostwithUrlhead(String url, Map<String, String> values, Map<String, String> headvalues) throws Exception {
        HttpResponse response;
        url = URLDecoder.decode(url, "UTF-8");
        HttpPost post = new HttpPost(url);
        post.setHeader("Accept-Encoding", "gzip, deflate");
        post.setHeader("Accept-Language", "zh-CN");
        post.setHeader("Accept", "application/json, application/xml, text/html, text/*, image/*, */*");

        //头部信息
        if (headvalues != null && headvalues.size() > 0) {
            for (String key : headvalues.keySet()) {
                post.setHeader(key, headvalues.get(key));
            }
        }

        String resultString = "";
        if (values != null && values.size() > 0) {
            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            for (String key : values.keySet()) {
                list.add(new BasicNameValuePair(key, values.get(key)));
            }
            // 解决中文乱码问题
            StringEntity entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            post.setEntity(entity);
        }
        try {
            HttpClient httpClient = getHttpClient();
            response = httpClient.execute(post);
            resultString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw e;
        } finally {
            post.releaseConnection();
        }
        return resultString;
    }

    public static String doGetwithhead(String url, Map<String, String> params, Map<String, String> headvalues) throws Exception {

        HttpResponse response;
        // 拼接参数
        String paramStr = "";
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                paramStr = paramStr + "&" + key + "=" + params.get(key);
            }
            paramStr = paramStr.substring(1);
            url += "?" + paramStr;
        }
        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Accept-Language", "zh-CN");
        httpGet.setHeader("Accept", "application/json, application/xml, text/html, text/*, image/*, */*");
        httpGet.setHeader("Authorization", "Basic QURNSU46S1lMSU4=");
        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");

        //头部信息
        if (headvalues != null && headvalues.size() > 0) {
            for (String key : headvalues.keySet()) {
                httpGet.setHeader(key, headvalues.get(key));
            }
        }

        String resultString = "";
        try {
            HttpClient httpClient = getHttpClient();
            response = httpClient.execute(httpGet);
            resultString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw e;
        } finally {
            httpGet.releaseConnection();
        }
        return resultString;

    }

    /**
     * @param @param  url
     * @param @param  params
     * @param @return
     * @return String
     * @throws
     * @Title: doGet
     * @Description: 所有get 请求底层调用方法
     * @CreateTime:2016年11月21日 下午1:52:35
     * @author zhuyuan
     * @since JDK 1.7
     */
    public static String doGet(String url, Map<String, String> params) throws Exception {

        HttpResponse response;
        // 拼接参数
        String paramStr = "";
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                paramStr = paramStr + "&" + key + "=" + params.get(key);
            }
            paramStr = paramStr.substring(1);
            url += "?" + paramStr;
        }
        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Accept-Language", "zh-CN");
        httpGet.setHeader("Accept", "application/json, application/xml, text/html, text/*, image/*, */*");
        httpGet.setHeader("Authorization", "Basic QURNSU46S1lMSU4=");
        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
        String token = params.get("token");
        if (Objects.nonNull(token)) {
            httpGet.setHeader("X-Token", token);
        }
        String resultString = "";
        try {
            HttpClient httpClient = getHttpClient();
            response = httpClient.execute(httpGet);
            resultString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw e;
        } finally {
            httpGet.releaseConnection();
        }
        return resultString;

    }

    /**
     * @param @param  url
     * @param @param  values
     * @param @return
     * @return String
     * @throws
     * @throws Exception
     * @Title: doPost
     * @Description: 所有Post 请求底层调用方法
     * @CreateTime:2016年11月21日 下午1:52:26
     * @author zhuyuan
     * @since JDK 1.7
     */
    public static String doPost(String url, Map<String, String> values) throws Exception {
        HttpResponse response;
        url = URLDecoder.decode(url, "UTF-8");
        HttpPost post = new HttpPost(url);
        post.setHeader("Accept-Encoding", "gzip, deflate");
        post.setHeader("Accept-Language", "zh-CN");
        post.setHeader("Accept", "application/json, application/xml, text/html, text/*, image/*, */*");
        post.setHeader("Authorization", "Basic QURNSU46S1lMSU4=");
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        String resultString = "";
        if (values != null && values.size() > 0) {
            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            for (String key : values.keySet()) {
                list.add(new BasicNameValuePair(key, values.get(key)));
            }
            // 解决中文乱码问题
            StringEntity entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            post.setEntity(entity);
        }
        try {
            HttpClient httpClient = getHttpClient();
            response = httpClient.execute(post);
            resultString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw e;
        } finally {
            post.releaseConnection();
        }
        return resultString;
    }

    public static String doPutParam(String url, String token, Map<String, Object> paramMap) throws Exception {
        // 获取连接客户端工具
        CloseableHttpClient httpClient = getHttpClient();
        String entityStr = null;
        CloseableHttpResponse response = null;
        try {
            // 创建POST请求对象
            HttpPut httpPut = new HttpPut(url);
            //添加请求参数
            List<NameValuePair> list = new LinkedList<>();
            for (String key : paramMap.keySet()) {
                Object value = paramMap.get(key);
                list.add(new BasicNameValuePair(key, value.toString()));
            }
            // 使用URL实体转换工具
            UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
            httpPut.setEntity(entityParam);
            /*
             * 添加请求头信息
             */
            httpPut.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
            httpPut.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPut.addHeader("X-Token", token);
            // 执行请求
            response = httpClient.execute(httpPut);
            // 获得响应的实体对象
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            entityStr = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            throw e;
        } finally {
            // 释放连接
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    System.err.println("释放连接出错");
                    e.printStackTrace();
                }
            }
        }
        return entityStr;
    }

    public static String doPutObject(String url, String token, Object obj) throws Exception {
        // 获取连接客户端工具
        CloseableHttpClient httpClient = getHttpClient();
        String entityStr = null;
        CloseableHttpResponse response = null;
        try {
            // 创建POST请求对象
            HttpPut httpPut = new HttpPut(url);
            // 添加请求头信息
            httpPut.addHeader("Content-Type", "application/json;charset=utf8");
            httpPut.addHeader("accept","application/json");
            httpPut.addHeader("X-Token", token);
            // 添加请求参数
            String jsonString = JSONObject.toJSONString(obj);
            StringEntity entityParam = new StringEntity(jsonString, "UTF-8");
            httpPut.setEntity(entityParam);
            // 执行请求
            response = httpClient.execute(httpPut);
            // 获得响应的实体对象
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            entityStr = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            throw e;
        } finally {
            // 释放连接
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    System.err.println("释放连接出错");
                    e.printStackTrace();
                }
            }
        }
        return entityStr;
    }

    /**
     * @Title: doPut
     * @Description: PUT基础请求
     * @param @param url
     * @param @param values
     * @param @return
     * @return String
     * @throws
     * @CreateTime:2016年11月21日 下午1:52:11
     * @author zhuyuan
     * @since JDK 1.7
     */
//	public static String doPut(String url, Map<String, String> values) {
//
//		HttpPut request = new HttpPut(url);
//
//		request.setHeader("Accept-Encoding", "gzip, deflate");
//		request.setHeader("Accept-Language", "zh-CN");
//		request.setHeader("Accept", "application/json, application/xml, text/html, text/*, image/*, */*");
//		request.setHeader("Authorization", "Basic QURNSU46S1lMSU4=");
//
//		HttpClient httpClient = getHttpClient();
//		String resultString = "";
//		if (values != null && values.size() > 0) {
//			try {
//
//				List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
//				for (String key : values.keySet()) {
//					list.add(new BasicNameValuePair(key, values.get(key)));
//				}
//				// 解决中文乱码问题
//				StringEntity entity = new UrlEncodedFormEntity(list,HTTP.UTF_8);
//				request.setEntity(entity);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		try {
//			HttpResponse response = httpClient.execute(request);
//			resultString = EntityUtils.toString(response.getEntity());
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			request.releaseConnection();
//		}
//		return resultString;
//	}

    /**
     * @param @param  url
     * @param @param  values
     * @param @return
     * @return String
     * @throws
     * @Title: doDelete
     * @Description: Delete基础请求
     * @CreateTime:2016年11月21日 下午1:51:58
     * @author zhuyuan
     * @since JDK 1.7
     */
//	public static String doDelete(String url, Map<String, String> values) {
//		HttpDelete delete = new HttpDelete(url);
//		delete.setHeader("Authorization", "Basic QURNSU46S1lMSU4=");
//		delete.setHeader("Accept-Encoding", "gzip, deflate");
//		delete.setHeader("Accept-Language", "zh-CN");
//		delete.setHeader("Accept", "application/json, application/xml, text/html, text/*, image/*, */*");
//		delete.setHeader("Authorization", "Basic QURNSU46S1lMSU4=");
//
//		HttpClient httpClient = getHttpClient();
//
//		String resultString = "";
//		try {
//
//			HttpResponse response = httpClient.execute(delete);
//			resultString = EntityUtils.toString(response.getEntity());
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			delete.releaseConnection();
//		}
//
//		return resultString;
//	}
//	  type: POST GET
    public static String jsonSend(String strURL, String params, String type) {
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod(type); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            int length = (int) connection.getContentLength();// 获取长度
            InputStream is = connection.getInputStream();
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8"); // utf-8编码
                return result;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }

    public static HttpClient doPostReturnClient(String url, Map<String, String> values) throws Exception {
        HttpResponse response;
        url = URLDecoder.decode(url, "UTF-8");
        HttpPost post = new HttpPost(url);
        if (values != null && values.size() > 0) {
            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            for (String key : values.keySet()) {
                list.add(new BasicNameValuePair(key, values.get(key)));
            }
            // 解决中文乱码问题
            StringEntity entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            post.setEntity(entity);
        }
        HttpClient httpClient = null;
        try {
            httpClient = getHttpClient();
            response = httpClient.execute(post);
        } catch (Exception e) {
            throw e;
        } finally {
            post.releaseConnection();
        }
        return httpClient;
    }

    public static String doPostReturnJSessionId(String url, Map<String, String> values) throws Exception {
        String resultString = null;
        HttpResponse response;
        url = URLDecoder.decode(url, "UTF-8");
        HttpPost post = new HttpPost(url);
//		 post.setHeader("varzhy195eb087b3a24cad9bdf042387a83922", "DgyImRWehE28PkQYUtnPQ8lnEz5TMmTG74fWY7qNew");
//		 post.setHeader("Cookie", "JSESSIONID=4082FC6A1E00293C262A0F8C91CF229E; timeNum=1555489439547; lan=zh_CN; org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE=zh_CN; locale=zh_CN");
        if (values != null && values.size() > 0) {
            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            for (String key : values.keySet()) {
                list.add(new BasicNameValuePair(key, values.get(key)));
            }
            // 解决中文乱码问题
            StringEntity entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            post.setEntity(entity);
        }
        HttpClient httpClient = null;
        try {
            httpClient = getHttpClient();
            response = httpClient.execute(post);
            resultString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw e;
        } finally {
            post.releaseConnection();
        }
        return resultString;
    }

    public static String doPostWithClient(String url, Map<String, String> values, HttpClient httpClient) throws Exception {
        HttpResponse response;
        url = URLDecoder.decode(url, "UTF-8");
        HttpPost post = new HttpPost(url);

        String resultString = "";
        if (values != null && values.size() > 0) {
            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            for (String key : values.keySet()) {
                list.add(new BasicNameValuePair(key, values.get(key)));
            }
            // 解决中文乱码问题
            StringEntity entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            post.setEntity(entity);
        }
        try {
            response = httpClient.execute(post);
            resultString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw e;
        } finally {
            post.releaseConnection();
        }
        return resultString;
    }
}
