package com.worksn.cookie;

import androidx.annotation.NonNull;
import com.worksn.classes.PrefStorageCookie;
import com.worksn.objects.G_;
import java.io.IOException;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {
    public AddCookiesInterceptor() {}

    @NonNull
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        G_.cookie = PrefStorageCookie.getAllProperty();
        StringBuilder val= new StringBuilder();
        String tmp = "";
        for(Map.Entry<String, ?>entry : G_.cookie.entrySet()){
            tmp =  (String)entry.getValue();
            if (!tmp.contains("deleted")){
                val.append((String) entry.getValue()).append("; ");
            }
        }
        builder.addHeader("Cookie", val.toString());
        return chain.proceed(builder.build());
    }
}