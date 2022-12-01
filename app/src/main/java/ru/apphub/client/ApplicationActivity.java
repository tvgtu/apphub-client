package ru.apphub.client;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.apphub.client.adapters.CommentsAdapter;
import ru.apphub.client.http.RetrofitClient;
import ru.apphub.client.model.Comment;

public class ApplicationActivity extends AppCompatActivity {

    private CommentsAdapter adapter = new CommentsAdapter(this);
    private RecyclerView recyclerView;
    private List<Comment> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        UUID applicationId = (UUID) getIntent().getExtras().get("applicationId");

        recyclerView = findViewById(R.id.list);

        LinearLayoutManager linearLayout
                = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayout);

        adapter.setComments(data);

        recyclerView.setAdapter(adapter);

        getComments(applicationId);
    }

    private void getComments(UUID applicationId) {
        Call<List<Comment>> call = RetrofitClient.getInstance().getApiClient().getComments(applicationId);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                List<Comment> comments = response.body();
                data.clear();
                data.addAll(comments);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }
}