package com.chingaridesigin.network;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderAdder implements Interceptor {

    String token="eyJhbGciOiJIUzI1NiJ9.ZTIzMTExMmE3ZDJjNWI3YTQxYjMwYmE1MTliNDlmNmM6NzBhZTk0ZDRlNGM0OWViMDYzOWExNzE5YTdiZWRmNTQxODgzMmIzZDMyN2VlNDlhZDQxMDJiMGU5YTVmNTVkZWNkZmQ2ZjViMDQ3N2Q2OWZmMTI0M2Y5ZmEwZDVlNTAzMTVhZTAyMWVjYTU5MDgxNWU1YjY4YjlkYmFiYzczZGMxYzQzMzlkOTU5ZTJkMjExZmNjZGM4YjBkYjAwZGFhOGNlMjJlMDZjNzIzNGU2NjUzOTQ5YTk0Mjc5ZTRiMTZjMjdhOTc3ZmVkNzc4NDE4ZWMwOTY1ODdiYTkyYzE5YzY4YTFhNWNiMGFkZDMxM2U5ZjI5NjUwNTIwNzVjNzI3MjhiYjRkNGUwYjk3YmQ0MGJhMDY2YzZkYWM3ZWI0NWU3.895S-imoVbyX-k9z1KCgaaUXgBhr--LaXwNeWJLbj9A";

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer " +token).build();

        return chain.proceed(request);
    }


}
