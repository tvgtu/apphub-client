package ru.apphub.client.http;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.apphub.client.model.Application;
import ru.apphub.client.model.Comment;

public interface Api {

    String BASE_URL = "http://10.0.2.2:8080/api/";

    @GET("applications")
    Call<List<Application>> getApplications();

    @GET("applications/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") UUID id);
}
