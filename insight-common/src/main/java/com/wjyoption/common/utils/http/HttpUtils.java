package com.wjyoption.common.utils.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用http发送方法
 * 
 * @author hs
 */
public class HttpUtils
{
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param)
    {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try
        {
            String urlNameString = url + "?" + param;
            log.info("sendGet - {}", urlNameString);
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
                result.append(line);
            }
            log.info("recv - {}", result);
        }
        catch (ConnectException e)
        {
            log.error("调用HttpUtils.sendGet ConnectException, url=" + url + ",param=" + param, e);
        }
        catch (SocketTimeoutException e)
        {
            log.error("调用HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + param, e);
        }
        catch (IOException e)
        {
            log.error("调用HttpUtils.sendGet IOException, url=" + url + ",param=" + param, e);
        }
        catch (Exception e)
        {
            log.error("调用HttpsUtil.sendGet Exception, url=" + url + ",param=" + param, e);
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception ex)
            {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }
    
    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param,Map<String, String> header)
    {
    	StringBuilder result = new StringBuilder();
    	BufferedReader in = null;
    	try
    	{
    		String urlNameString = url + "?" + param;
    		log.info("sendGet - {}", urlNameString);
    		URL realUrl = new URL(urlNameString);
    		URLConnection connection = realUrl.openConnection();
    		connection.setRequestProperty("accept", "*/*");
    		connection.setRequestProperty("connection", "Keep-Alive");
    		connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
    		if(header != null){
    			Set<String> keySet = header.keySet();
    			for(String key : keySet){
    				connection.setRequestProperty(key, header.get(key));
    			}
    		}
    		connection.connect();
    		in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    		String line;
    		while ((line = in.readLine()) != null)
    		{
    			result.append(line);
    		}
    		if(result.length() > 500){
    			log.info("recv - {}", result.substring(0,500) + ".......");
    		}else{
    			log.info("recv - {}", result);
    		}
    	}
    	catch (ConnectException e)
    	{
    		log.error("调用HttpUtils.sendGet ConnectException, url=" + url + ",param=" + param, e);
    	}
    	catch (SocketTimeoutException e)
    	{
    		log.error("调用HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + param, e);
    	}
    	catch (IOException e)
    	{
    		log.error("调用HttpUtils.sendGet IOException, url=" + url + ",param=" + param, e);
    	}
    	catch (Exception e)
    	{
    		log.error("调用HttpsUtil.sendGet Exception, url=" + url + ",param=" + param, e);
    	}
    	finally
    	{
    		try
    		{
    			if (in != null)
    			{
    				in.close();
    			}
    		}
    		catch (Exception ex)
    		{
    			log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
    		}
    	}
    	return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param)
    {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try
        {
            String urlNameString = url + "?" + param;
            log.info("sendPost - {}", urlNameString);
            URL realUrl = new URL(urlNameString);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
//            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null)
            {
                result.append(line);
            }
            log.info("recv - {}", result);
        }
        catch (ConnectException e)
        {
            log.error("调用HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param, e);
        }
        catch (SocketTimeoutException e)
        {
            log.error("调用HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param=" + param, e);
        }
        catch (IOException e)
        {
            log.error("调用HttpUtils.sendPost IOException, url=" + url + ",param=" + param, e);
        }
        catch (Exception e)
        {
            log.error("调用HttpsUtil.sendPost Exception, url=" + url + ",param=" + param, e);
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }
    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost2(String url, String param)
    {
    	PrintWriter out = null;
    	BufferedReader in = null;
    	StringBuilder result = new StringBuilder();
    	try
    	{
    		String urlNameString = url;
    		log.info("sendPost - {}", urlNameString);
    		URL realUrl = new URL(urlNameString);
    		URLConnection conn = realUrl.openConnection();
    		conn.setRequestProperty("accept", "*/*");
    		conn.setRequestProperty("connection", "Keep-Alive");
    		conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
    		conn.setRequestProperty("Accept-Charset", "utf-8");
    		conn.setRequestProperty("contentType", "utf-8");
    		conn.setDoOutput(true);
    		conn.setDoInput(true);
    		
    		conn.getOutputStream().write(param.getBytes());
    		conn.getOutputStream().flush();
//    		out = new PrintWriter(conn.getOutputStream());
//            out.print(param);
//    		out.flush();
    		in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
    		String line;
    		while ((line = in.readLine()) != null)
    		{
    			result.append(line);
    		}
    		log.info("recv - {}", result);
    	}
    	catch (ConnectException e)
    	{
    		log.error("调用HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param, e);
    	}
    	catch (SocketTimeoutException e)
    	{
    		log.error("调用HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param=" + param, e);
    	}
    	catch (IOException e)
    	{
    		log.error("调用HttpUtils.sendPost IOException, url=" + url + ",param=" + param, e);
    	}
    	catch (Exception e)
    	{
    		log.error("调用HttpsUtil.sendPost Exception, url=" + url + ",param=" + param, e);
    	}
    	finally
    	{
    		try
    		{
    			if (out != null)
    			{
    				out.close();
    			}
    			if (in != null)
    			{
    				in.close();
    			}
    		}
    		catch (IOException ex)
    		{
    			log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
    		}
    	}
    	return result.toString();
    }
    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param headers 请求头
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost2(String url, String param,Map<String, String> headers)
    {
    	DataOutputStream out = null;
    	BufferedReader in = null;
    	StringBuilder result = new StringBuilder();
    	try
    	{
    		log.info("sendPost - {}", url);
    		URL realUrl = new URL(url);
    		HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
    		conn.setRequestProperty("accept", "*/*");
    		conn.setRequestProperty("connection", "Keep-Alive");
//    		conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//    		conn.setRequestProperty("Accept-Charset", "utf-8");
//    		conn.setRequestProperty("contentType", "utf-8");
    		if(headers != null && headers.size() > 0){
    			Set<String> keySet = headers.keySet();
    			keySet.stream().forEach(key -> {
    				conn.setRequestProperty(key, headers.get(key));
    			});
    		}
    		conn.setDoOutput(true);
    		conn.setDoInput(true);
    		conn.connect();
    		out = new DataOutputStream(conn.getOutputStream());
            out.writeUTF(URLEncoder.encode(param, "UTF-8"));
            out.flush();
            out.close();
//    		out = new PrintWriter(conn.getOutputStream());
//            out.print(param);
//    		out.flush();
            System.out.println(conn.getResponseCode());
    		in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
    		String line;
    		while ((line = in.readLine()) != null)
    		{
    			result.append(line);
    		}
    		log.info("recv - {}", result);
    	}
    	catch (ConnectException e)
    	{
    		log.error("调用HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param, e);
    	}
    	catch (SocketTimeoutException e)
    	{
    		log.error("调用HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param=" + param, e);
    	}
    	catch (IOException e)
    	{
    		log.error("调用HttpUtils.sendPost IOException, url=" + url + ",param=" + param, e);
    	}
    	catch (Exception e)
    	{
    		log.error("调用HttpsUtil.sendPost Exception, url=" + url + ",param=" + param, e);
    	}
    	finally
    	{
    		try
    		{
    			if (out != null)
    			{
    				out.close();
    			}
    			if (in != null)
    			{
    				in.close();
    			}
    		}
    		catch (IOException ex)
    		{
    			log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
    		}
    	}
    	return result.toString();
    }
    
    /**
     * post请求
     * 
     * @param url
     * @param params
     * @param timeout
     *            超时时间，秒
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, String> params) throws Exception
    {
    	
    	CloseableHttpClient httpclient = HttpClientBuilder.create().build();
    	String retVal = "";
    	try
    	{
    		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            if (params != null)
            {
                for (Map.Entry<String, String> param : params.entrySet())
                {
                    formparams.add(new BasicNameValuePair(param.getKey(), param
                            .getValue()));
                }
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
                    "UTF-8");
    		HttpPost httppost = new HttpPost(url);
    		httppost.setEntity(entity);
    		HttpResponse resp = httpclient.execute(httppost);
    		HttpEntity retEntity = resp.getEntity();
    		Header[] h = resp.getHeaders("Content-Encoding");
    		if (null != h && h.length >= 1) {
    			
    			if ("gzip".equals(h[0].getValue())) {
    				retEntity = new GzipDecompressingEntity(retEntity);
    				
    			} else if ("deflate".equals(h[0].getValue())) {
    				retEntity = new DeflateDecompressingEntity(retEntity);
    			}
    		}
    		
    		retVal = EntityUtils.toString(retEntity, "UTF-8");
    		httppost.clone();
    	}
    	catch (IOException e)
    	{
    		throw e;
    	}
    	finally
    	{
    		httpclient.close();
    	}
    	return retVal;
    }
    /**
     * post请求
     * 
     * @param url
     * @param params
     * @param timeout
     *            超时时间，秒
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, String> params,Map<String, String> headers) throws Exception
    {
    	
    	CloseableHttpClient httpclient = HttpClientBuilder.create().build();
    	String retVal = "";
    	try
    	{
    		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
    		if (params != null)
    		{
    			for (Map.Entry<String, String> param : params.entrySet())
    			{
    				formparams.add(new BasicNameValuePair(param.getKey(), param
    						.getValue()));
    			}
    		}
    		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
    				"UTF-8");
    		HttpPost httppost = new HttpPost(url);
//    		HttpHost proxy = new HttpHost("127.0.0.1", 11001);
//        	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).setProxy(proxy).build();
//        	httppost.setConfig(requestConfig);
    		Set<String> keySet = headers.keySet();
            for(String key : keySet){
            	httppost.setHeader(key, headers.get(key));
            }
    		httppost.setEntity(entity);
    		HttpResponse resp = httpclient.execute(httppost);
    		HttpEntity retEntity = resp.getEntity();
    		Header[] h = resp.getHeaders("Content-Encoding");
    		if (null != h && h.length >= 1) {
    			
    			if ("gzip".equals(h[0].getValue())) {
    				retEntity = new GzipDecompressingEntity(retEntity);
    				
    			} else if ("deflate".equals(h[0].getValue())) {
    				retEntity = new DeflateDecompressingEntity(retEntity);
    			}
    		}
    		
    		retVal = EntityUtils.toString(retEntity, "UTF-8");
    		httppost.clone();
    	}
    	catch (IOException e)
    	{
    		throw e;
    	}
    	finally
    	{
    		httpclient.close();
    	}
    	return retVal;
    }
    /**
     * post请求
     * 
     * @param url
     * @param params
     * @param timeout
     *            超时时间，秒
     * @return
     * @throws IOException
     */
    public static String post(String url, String jsonParams, Map<String, String> headers) throws Exception
    {
    	
    	CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        String retVal = "";
        try
        {
            HttpPost httppost = new HttpPost(url);
            //设置代理
//        	HttpHost proxy = new HttpHost("127.0.0.1", 11001);
//        	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).setProxy(proxy).build();
//        	httppost.setConfig(requestConfig);
            Set<String> keySet = headers.keySet();
            for(String key : keySet){
            	httppost.setHeader(key, headers.get(key));
            }
            StringEntity entity = new StringEntity(jsonParams, "UTF-8");
            httppost.setEntity(entity);
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity retEntity = resp.getEntity();
            Header[] h = resp.getHeaders("Content-Encoding");
			if (null != h && h.length >= 1) {

				if ("gzip".equals(h[0].getValue())) {
					retEntity = new GzipDecompressingEntity(retEntity);

				} else if ("deflate".equals(h[0].getValue())) {
					retEntity = new DeflateDecompressingEntity(retEntity);
				}
			}
            
            retVal = EntityUtils.toString(retEntity, "UTF-8");
            httppost.clone();
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            httpclient.close();
        }
        return retVal;
    }

    public static String sendSSLPost(String url, String param)
    {
        StringBuilder result = new StringBuilder();
        String urlNameString = url + "?" + param;
        try
        {
            log.info("sendSSLPost - {}", urlNameString);
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
            URL console = new URL(urlNameString);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ret = "";
            while ((ret = br.readLine()) != null)
            {
                if (ret != null && !ret.trim().equals(""))
                {
                    result.append(new String(ret.getBytes("ISO-8859-1"), "utf-8"));
                }
            }
            log.info("recv - {}", result);
            conn.disconnect();
            br.close();
        }
        catch (ConnectException e)
        {
            log.error("调用HttpUtils.sendSSLPost ConnectException, url=" + url + ",param=" + param, e);
        }
        catch (SocketTimeoutException e)
        {
            log.error("调用HttpUtils.sendSSLPost SocketTimeoutException, url=" + url + ",param=" + param, e);
        }
        catch (IOException e)
        {
            log.error("调用HttpUtils.sendSSLPost IOException, url=" + url + ",param=" + param, e);
        }
        catch (Exception e)
        {
            log.error("调用HttpsUtil.sendSSLPost Exception, url=" + url + ",param=" + param, e);
        }
        return result.toString();
    }
    
    

    private static class TrustAnyTrustManager implements X509TrustManager
    {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
        {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
        {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers()
        {
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier
    {
        @Override
        public boolean verify(String hostname, SSLSession session)
        {
            return true;
        }
    }
}