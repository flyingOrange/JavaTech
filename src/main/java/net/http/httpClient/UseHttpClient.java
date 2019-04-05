package net.http.httpClient;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.message.BasicNameValuePair;

public class UseHttpClient {

	 @Test
	// 无参数get请求
	public void httpclientGet() throws IOException {
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建http GET请求
		//HttpGet httpGet = new HttpGet("http://hq.sinajs.cn/list=sh601006");
		HttpGet httpGet = new HttpGet("https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/amd_modules/@baidu/search-sug/sug/index_e0150f3.js");
		
		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获取服务端返回的数据
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				// 服务端返回数据的长度
				System.out.println("内容长度：" + content.length());
				System.out.println(content);
			}
		} catch (Exception e) {
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
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获取服务端，响应的数据
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println(content);
			}
		} catch (ClientProtocolException e) {
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
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获取服务端，响应的数据
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println(content);
			}
		} catch (ClientProtocolException e) {
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
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获取服务端响应的数据
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println(content);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				response.close();
			}
			httpclient.close();
		}

	}

}
