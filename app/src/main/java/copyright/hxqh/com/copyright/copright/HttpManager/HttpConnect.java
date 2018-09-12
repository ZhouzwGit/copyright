package copyright.hxqh.com.copyright.copright.HttpManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by zzw on 2018/9/11.
 */

public class HttpConnect {
    public static String login(String username,String password){
        String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/applogin";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject json = new JSONObject();
        StringEntity jsonEntity = null;
        String result = null;
        try{
            json.put("username",username);
            json.put("password",password);
            jsonEntity = new StringEntity(json.toString(),"UTF-8");
            jsonEntity.setContentEncoding("UTF-8");
            jsonEntity.setContentType("application/json");
            post.setEntity(jsonEntity);
            HttpResponse response = null;
            response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200){
                HttpEntity httpEntity = response.getEntity();
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
                result = reader.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
