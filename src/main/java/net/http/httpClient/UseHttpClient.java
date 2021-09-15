package net.http.httpClient;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class UseHttpClient {

	 @Test
	// 无参数get请求
	public void httpclientGet() throws IOException {
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建http GET请求
		HttpGet httpGet = new HttpGet("http://hq.sinajs.cn/list=sh601006");

		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpclient.execute(httpGet);
			System.out.println(response.getCode() + " " + response.getReasonPhrase());
			HttpEntity entity = response.getEntity();
			// 获取服务端返回的数据
			String content = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
			// 服务端返回数据的长度
			System.out.println("内容长度：" + content.length());
			System.out.println(content);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				response.close();
			}
			// 相当于关闭浏览器
			httpclient.close();
		}
	}

	// @Test
	// 有参数get请求
	public void httpclientGetWithParam() throws IOException {
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 定义请求的参数
		URI uri = null;
		try {
			uri = new URIBuilder("http://www.baidu.com/s").setParameter("wd", "java").build();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(uri);

		// 创建http GET请求
		HttpGet httpGet = new HttpGet(uri);
		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpclient.execute(httpGet);
			System.out.println(response.getCode() + " " + response.getReasonPhrase());
			HttpEntity entity = response.getEntity();
			// 获取服务端返回的数据
			String content = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
			// 服务端返回数据的长度
			System.out.println("内容长度：" + content.length());
			System.out.println(content);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				response.close();
			}
			httpclient.close();
		}

	}

	@Test
	// 无参数post请求
	public void httpclientPost() throws IOException {
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建http POST请求
		HttpPost httpPost = new HttpPost("http://www.oschina.net/");
		// 伪装浏览器请求
		httpPost.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");

		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpclient.execute(httpPost);
			System.out.println(response.getCode() + " " + response.getReasonPhrase());
			HttpEntity entity = response.getEntity();
			// 获取服务端返回的数据
			String content = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
			// 服务端返回数据的长度
			System.out.println("内容长度：" + content.length());
			System.out.println(content);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				response.close();
			}
			httpclient.close();
		}

	}

	@Test
	// 有参数post请求
	public void httpclientPostWithParam() throws IOException {
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建http POST请求
		HttpPost httpPost = new HttpPost("http://www.oschina.net/search");

		// 设置2个post参数，一个是scope、一个是q
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("scope", "project"));
		parameters.add(new BasicNameValuePair("q", "java"));
		// 构造一个form表单式的实体
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
		// 将请求实体设置到httpPost对象中
		httpPost.setEntity(formEntity);
		// 伪装浏览器请求
		httpPost.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");

		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpclient.execute(httpPost);
			System.out.println(response.getCode() + " " + response.getReasonPhrase());
			HttpEntity entity = response.getEntity();
			// 获取服务端返回的数据
			String content = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
			// 服务端返回数据的长度
			System.out.println("内容长度：" + content.length());
			System.out.println(content);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				response.close();
			}
			httpclient.close();
		}

	}

}
