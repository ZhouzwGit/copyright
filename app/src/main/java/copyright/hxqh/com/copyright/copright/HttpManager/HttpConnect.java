package copyright.hxqh.com.copyright.copright.HttpManager;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import copyright.hxqh.com.copyright.copright.entity.UserInfo;
import copyright.hxqh.com.copyright.copright.ui.IRM.enity.Asset;
import copyright.hxqh.com.copyright.copright.ui.IRM.enity.Resourcekind;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Author;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Royalty;
import copyright.hxqh.com.copyright.copright.ui.contract.entity.Contract;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Channel;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Product;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;
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
    public static  List<Asset>  getAssetList(JSONObject json,int page){
        String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/assetlist";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        StringEntity jsonEntity = null;
        String result = null;
        List<Asset> assets = new ArrayList<>();
        try{
            json.put("pageNo",page+"");
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
            return null;
        }
        return assets;
    }
    public static  List<Resourcekind>  getResourcekind(){
        String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/getresourcekind";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject json = new JSONObject();
        StringEntity jsonEntity = null;
        String result = null;
        List<Resourcekind> resourcekind = new ArrayList<>();
        try{
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
                resourcekind = (List<Resourcekind>) JsonUtil.getObject(result,new TypeToken<List<Resourcekind>>(){});
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resourcekind;
    }
    public static  List<Product>  getProductList(JSONObject json, int page){
        String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/productlist";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        StringEntity jsonEntity = null;
        String result = null;
        List<Product> productList = new ArrayList<>();
        try{
            json.put("pageNo",page+"");
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
                productList = (List<Product>) JsonUtil.getObject(result,new TypeToken<List<Product>>(){});
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return productList;
    }
    public static  List<Royalty>  getRoyaltyList(JSONObject json, int page){
        String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/authorpaylist";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        StringEntity jsonEntity = null;
        String result = null;
        List<Royalty> royaltyList = new ArrayList<>();
        try{
            json.put("pageNo",page+"");
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
                royaltyList = (List<Royalty>) JsonUtil.getObject(result,new TypeToken<List<Royalty>>(){});
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return royaltyList;
    }
    public static  List<Author>  getAuthorList(JSONObject json, int page){
        String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/authorlist";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        StringEntity jsonEntity = null;
        String result = null;
        List<Author> authorList = new ArrayList<>();
        try{
            json.put("pageNo",page+"");
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
                authorList = (List<Author>) JsonUtil.getObject(result,new TypeToken<List<Author>>(){});
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return authorList;
    }
    public static  List<Contract>  getContractList(JSONObject json, int page){
        String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/contractlist";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        StringEntity jsonEntity = null;
        String result = null;
        List<Contract> contractList = new ArrayList<>();
        try{
            json.put("pageNo",page+"");
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
                contractList = (List<Contract>) JsonUtil.getObject(result,new TypeToken<List<Contract>>(){});
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return contractList;
    }
    public static  List<Channel>  getChannelList(JSONObject json, int page){
        String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/channelinfolist";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        StringEntity jsonEntity = null;
        String result = null;
        List<Channel> channelList = new ArrayList<>();
        try{
            json.put("pageNo",page+"");
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
                channelList = (List<Channel>) JsonUtil.getObject(result,new TypeToken<List<Channel>>(){});
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return channelList;
    }
    public static UserInfo getUserinfoList(String username){
        String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/userInfo";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject json = new JSONObject();
        StringEntity jsonEntity = null;
        String result = null;
        UserInfo userInfo = new UserInfo();
        try{
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
                userInfo = (UserInfo) JsonUtil.getObject(result,new TypeToken<UserInfo>(){});
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return userInfo;
    }
    public static JSONObject getBasicJson(Context ctx){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageSize","20");
            jsonObject.put("username",AcountUtil.getUsername(ctx));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
