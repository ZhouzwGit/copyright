package copyright.hxqh.com.copyright.copright.util;

import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by zzw on 2018/9/18.
 */

public class JsonUtil {
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();
    public static Object getObject(String json, TypeToken<?> typeToken){
        Object result = gson.fromJson(json,typeToken.getType());
        return result;
    }
}
