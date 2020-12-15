package com.example.sesiune_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView list;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.rv_list);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        list.setLayoutManager(linearLayoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(getApplicationContext(), linearLayoutManager.getOrientation());
        list.addItemDecoration(divider);
        list.setAdapter(new ItemAdapter(DataSource.getItems(100)));

    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView firstName;
        private final TextView lastName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.tv_first_name);
            lastName = itemView.findViewById(R.id.tv_last_name);
        }

        void bind(@NonNull final Item item) {
            firstName.setText(item.getFirstName());
            lastName.setText(item.getLastName());
        }

        void updateBackground(int pos) {
            if (pos % 2 == 0) {
                itemView.setBackgroundColor(Color.rgb(250, 250, 250));
            } else {
                itemView.setBackgroundColor(Color.rgb(150, 150, 150));
            }
        }
    }

    static class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

        @NonNull
        private final List<Item> item;

        public ItemAdapter(List<Item> item) {
            this.item = item;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list_item, parent, false);

            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            holder.bind(item.get(position));
            holder.updateBackground(position);
        }

        @Override
        public int getItemCount() {
            return this.item.size();
        }
    }
}