package ru.apphub.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.apphub.client.adapters.AppAdapter;
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

        updateButton.setOnClickListener(
                v -> new HttpRequest().execute()
        );
    }

    public class HttpRequest extends AsyncTask<Void, Void, List<Application>> {

        @Override
        protected List<Application> doInBackground(Void... voids) {
            return getApplications();
        }

        @Override
        protected void onPostExecute(List<Application> applications) {
            data.clear();
            data.addAll(applications);
            adapter.notifyDataSetChanged();
        }

        private List<Application> getApplications() {
            OkHttpClient httpClient =  new OkHttpClient.Builder().build();
            Request request = new Request.Builder()
                    .get()
                    .url("http://10.0.2.2:8080/api/applications")
                    .build();

            try (Response response = httpClient.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    Type type = new TypeToken<ArrayList<Application>>(){}.getType();
                    return new Gson().fromJson(response.body().string(), type);
                }
            } catch (Exception e) {
                Log.e("Error", "Error while getting applications", e);
            }
            return Collections.emptyList();
        }
    }

    private List<Application> testApps() {
        final List<Application> result = new ArrayList<>();

        result.add(new Application("app1", "descr1"));
        result.add(new Application("app2", "descr2"));
        result.add(new Application("app3", "descr3"));
        result.add(new Application("app4", "descr4"));
        result.add(new Application("app5", "descr5"));
        result.add(new Application("app6", "descr6"));
        result.add(new Application("app7", "descr7"));

        return result;
    }
}