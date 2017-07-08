package com.akarbowy.foursquaresearch.data;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        HttpUrl authUrl = original.url().newBuilder()
                .addQueryParameter("oauth_token", "KBZC0M4NG1R2ASQKICPUZVOBNEL1PMABM3IEQKOH3J3LJEQS")
                .addQueryParameter("v","20170708")
                .build();

        Request authorized = original.newBuilder()
                .url(authUrl)
                .build();

        return chain.proceed(authorized);
    }
}
