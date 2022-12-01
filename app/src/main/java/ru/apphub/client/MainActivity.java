package ru.apphub.client;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import ru.apphub.client.adapters.AppAdapter;
import ru.apphub.client.http.RetrofitClient;
import ru.apphub.client.model.Application;

public class MainActivity extends AppCompatActivity {

    private AppAdapter adapter = new AppAdapter(this);
    private RecyclerView recyclerView;
    private List<Application> data = new ArrayList<>();
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list);
        updateButton = findViewById(R.id.updateButton);

        LinearLayoutManager linearLayout
                = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayout);

        adapter.setApplications(data);

        recyclerView.setAdapter(adapter);

        updateButton.setOnClickListener(v -> getApplications());
    }

    public void getApplications() {
        Call<List<Application>> call = RetrofitClient.getInstance().getApiClient().getApplications();
        call.enqueue(new Callback<List<Application>>() {
            @Override
            public void onResponse(Call<List<Application>> call, retrofit2.Response<List<Application>> response) {
                List<Application> applications = response.body();
                data.clear();
                data.addAll(applications);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Application>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }

//    public class HttpRequest extends AsyncTask<Void, Void, List<Application>> {
//
//        @Override
//        protected List<Application> doInBackground(Void... voids) {
//            return getApplications();
//        }
//
//        @Override
//        protected void onPostExecute(List<Application> applications) {
//            data.clear();
//            data.addAll(applications);
//            adapter.notifyDataSetChanged();
//        }
//
//        private List<Application> getApplications() {
//            OkHttpClient httpClient =  new OkHttpClient.Builder().build();
//            Request request = new Request.Builder()
//                    .get()
//                    .url("http://§.0.2.2:8080/api/applications")
//                    .build();
//
//            try (Response response = httpClient.newCall(request).execute()) {
//                if (response.isSuccessful()) {
//                    Type type = new TypeToken<ArrayList<Application>>(){}.getType();
//                    return new Gson().fromJson(response.body().string(), type);
//                }
//            } catch (Exception e) {
//                Log.e("Error", "Error while getting applications", e);
//            }
//            return Collections.emptyList();
//        }
//    }
}