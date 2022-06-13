package ru.apphub.client.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.apphub.client.R;
import ru.apphub.client.model.Application;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {

    private final Context context;
    private List<Application> applications;

    public AppAdapter(Context context) {
        this.context = context;
    }

    public void setPersons(List<Application> applications) {
        this.applications = applications;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("CREATE HOLDER", "create holder");
        View viewItem = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new AppViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        Log.i("BIND DATA", "bind date" + position);
        Application app = applications.get(position);
        holder.setData(app);
    }

    @Override
    public int getItemCount() {
        return applications.size();
    }

    class AppViewHolder extends RecyclerView.ViewHolder {

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setData(Application app) {
            TextView name = itemView.findViewById(R.id.title);
            name.setText(app.getTitle());

            TextView phone = itemView.findViewById(R.id.description);
            phone.setText(app.getDescription());
        }
    }
}
