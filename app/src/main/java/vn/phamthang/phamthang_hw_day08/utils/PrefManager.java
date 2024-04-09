package vn.phamthang.phamthang_hw_day08.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import vn.phamthang.phamthang_hw_day08.MyApplication;
import vn.phamthang.phamthang_hw_day08.objects.Product;

public class PrefManager {
    private static SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(Constant.DATA, Context.MODE_PRIVATE);
    public static Gson gson = new Gson();
    public static void saveData(String key, ArrayList<Product> listData) {
        String dataJson = gson.toJson(listData);
        sharedPreferences.edit().putString(key, dataJson).commit();
    }

    public static ArrayList<Product> getData(String key) {
        String dataJson = sharedPreferences.getString(key, null);
        if (dataJson != null) {
            Type type = new TypeToken<ArrayList<Product>>() {}.getType();
            return gson.fromJson(dataJson,type);
        }
        return new ArrayList<>();
    }
}
