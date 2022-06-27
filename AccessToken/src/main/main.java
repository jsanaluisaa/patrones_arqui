/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 *
 * @author PXNDX
 */
public class main {
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException {

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        StringBuilder data = new StringBuilder();
        data.append("grant_type=client_credentials");
        byte[] byteArray = data.toString().getBytes("UTF-8");
        URL url = new URL("https://192.168.15.82:8243/token");
        HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setConnectTimeout(5000);
        con .setDoOutput(true);
        con.setRequestProperty("Authorization",
                "Basic WFFWWFh5dElKeHBvcGxBd3JieGFNTEZzUDQ4YTppWWZpakJTbEJJUkpGQ2Z2NndpR2VzNWdpYU1h");
        OutputStream postStream = con.getOutputStream();
        postStream.write(byteArray, 0, byteArray.length);
        postStream.close();
//      curl -k -d "grant_type=client_credentials" -H "Authorization: Basic WFFWWFh5dElKeHBvcGxBd3JieGFNTEZzUDQ4YTppWWZpakJTbEJJUkpGQ2Z2NndpR2VzNWdpYU1h" https://192.168.15.82:8243/token
        InputStreamReader reader = new InputStreamReader(con.getInputStream());
        BufferedReader in = new BufferedReader(reader);
        String json = in.readLine();
        System.out.println("Json String = " + json);

        // Parse the Json response and retrieve the Access Token
        Gson gson = new Gson();
        Type mapType  = new TypeToken<Map<String,String>>(){}.getType();
        Map<String,String> ser = gson.fromJson(json, mapType);
        String accessToken = ser.get("access_token");
        System.out.println("Access Token = " + accessToken);
        in.close();
        con.disconnect();
    }




}
