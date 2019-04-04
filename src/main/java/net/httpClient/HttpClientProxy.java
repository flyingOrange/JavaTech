package net.httpClient;

import org.apache.http.HttpHost;
import org.junit.Test;

public class HttpClientProxy {
    //java.net.Proxy.Type.HTTP;
    
    @Test
    public void socksProxy() {
        HttpHost proxy = new HttpHost("localhost", 1080, "SOCKS");
        
    } 


}
