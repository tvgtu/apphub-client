package ru.apphub.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ru.apphub.client.adapters.AppAdapter;
import ru.apphub.client.model.Application;

public class MainActivity extends AppCompatActivity {

    private AppAdapter adapter = new AppAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.list);

        LinearLayoutManager linearLayout
                = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayout);

        adapter.setPersons(testApps());

        recyclerView.setAdapter(adapter);

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