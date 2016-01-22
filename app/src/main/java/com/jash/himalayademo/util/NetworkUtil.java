package com.jash.himalayademo.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.jash.himalayademo.entities.Categories;
import com.jash.himalayademo.entities.HomeEntity;

import java.io.IOException;
import java.util.Date;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by jash
 * Date: 16-1-20
 * Time: 上午10:36
 */
public class NetworkUtil {
    public interface Service{
        @GET("/mobile/discovery/v1/recommends?channel=and-d11&device=android&includeActivity=false&includeSpecial=true&scale=2&version=4.3.44.2")
        Call<HomeEntity> getHome();
        @GET("/mobile/discovery/v1/categories?device=android&picVersion=11&scale=2")
        Call<Categories> getCategories();
    }

    private static Service service;

    static {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new TypeAdapter<Date>() {
            @Override
            public void write(JsonWriter out, Date value) throws IOException {
                out.value(value.getTime());
            }

            @Override
            public Date read(JsonReader in) throws IOException {
                long l = in.nextLong();
                return new Date(l);
            }
        }).create();
        service = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://mobile.ximalaya.com").build().create(Service.class);
    }

    public static Service getService() {
        return service;
    }
}
