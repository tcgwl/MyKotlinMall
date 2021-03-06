package com.kotlin.base.data.net.interceptors;

import android.support.annotation.RawRes;

import com.kotlin.base.utils.FileUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Archer on 2018/2/8.
 */

public class DebugInterceptor extends BaseInterceptor {
    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = rawId;
    }

    /**
     * 返回自定义数据
     */
    private Response getResponse(Interceptor.Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Interceptor.Chain chain, @RawRes int rawId) {
        final String json = FileUtils.getRawFile(rawId);
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if (url.contains(DEBUG_URL)) {
            //拦截特定url，返回自定义数据
            return debugResponse(chain, DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
