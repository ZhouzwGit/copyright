package copyright.hxqh.com.copyright.copright.HttpManager;

import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import copyright.hxqh.com.copyright.copright.ui.IRM.enity.Asset;
import copyright.hxqh.com.copyright.copright.util.JsonUtil;

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
    public static  List<Asset>  getAssetList(String pageNo, String pageSize, String username){
        String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/assetlist";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject json = new JSONObject();
        StringEntity jsonEntity = null;
        String result = null;
        List<Asset> assets = new ArrayList<>();
        try{
            json.put("pageNo",pageNo);
            json.put("pageSize",pageSize);
            json.put("username",username);
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
                assets = (List<Asset>) JsonUtil.getObject(result,new TypeToken<List<Asset>>(){});
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return assets;
    }

}
