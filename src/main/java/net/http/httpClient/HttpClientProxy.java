package net.http.httpClient;

import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpClientProxy {

    @Test
    public void httpProxy() {
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpHost target = new HttpHost("https", "youtube.com", 443);
            //final HttpHost target = new HttpHost("http", "ip.3322.net", 80);
            //http代理，访问http://ip.3322.net/应该显示的是代理服务器的IP
            final HttpHost proxy = new HttpHost("http", "127.0.0.1", 1081);

            final RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            final HttpGet request = new HttpGet("/get");
            request.setConfig(config);

            System.out.println("Executing request " + request.getMethod() + " " + request.getUri() +
                    " via " + proxy);

            try (final CloseableHttpResponse response = httpclient.execute(target, request)) {
                System.out.println("----------------------------------------");
                System.out.println(response.getCode() + " " + response.getReasonPhrase());
                System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void httpProxyAuthentication(){
        final BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope("localhost", 8888),
                new UsernamePasswordCredentials("squid", "squid".toCharArray()));
        credsProvider.setCredentials(
                new AuthScope("httpbin.org", 80),
                new UsernamePasswordCredentials("user", "passwd".toCharArray()));
        try (final CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider).build()) {
            final HttpHost target = new HttpHost("http", "httpbin.org", 80);
            final HttpHost proxy = new HttpHost("localhost", 8888);

            final RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            final HttpGet httpget = new HttpGet("/basic-auth/user/passwd");
            httpget.setConfig(config);

            System.out.println("Executing request " + httpget.getMethod() + " " + httpget.getUri() +
                    " via " + proxy);

            try (final CloseableHttpResponse response = httpclient.execute(target, httpget)) {
                System.out.println("----------------------------------------");
                System.out.println(response.getCode() + " " + response.getReasonPhrase());
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
