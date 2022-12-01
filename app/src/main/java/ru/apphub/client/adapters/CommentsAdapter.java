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
import ru.apphub.client.model.Comment;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.AppViewHolder> {

    private final Context context;
    private List<Comment> comments;

    public CommentsAdapter(Context context) {
        this.context = context;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentsAdapter.AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("CREATE HOLDER", "create holder");
        View viewItem = LayoutInflater.from(context).inflate(R.layout.comments_item, parent, false);
        return new CommentsAdapter.AppViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.AppViewHolder holder, int position) {
        Log.i("BIND DATA", "bind date" + position);
        Comment comment = comments.get(position);
        holder.setData(comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class AppViewHolder extends RecyclerView.ViewHolder {

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setData(Comment comment) {
            TextView id = itemView.findViewById(R.id.id);
            id.setText(comment.getId().toString());

            TextView payload = itemView.findViewById(R.id.payload);
            payload.setText(comment.getPayload());
        }
    }
}

