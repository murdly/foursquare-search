package com.akarbowy.foursquaresearch.network;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        HttpUrl authUrl = original.url().newBuilder()
                .addQueryParameter("oauth_token", "4Q2OI3GCARGO2OD3VZF4EPUAL2RUX0IGB0SISOHIGMJVG2UN")
                .addQueryParameter("v","20170707")
                .build();

        Request authorized = original.newBuilder()
                .url(authUrl)
                .build();

        return chain.proceed(authorized);
    }
}
