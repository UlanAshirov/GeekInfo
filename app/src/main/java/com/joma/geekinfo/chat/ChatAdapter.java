package com.joma.geekinfo.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joma.geekinfo.R;
import com.joma.geekinfo.databinding.ItemChatBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<ChatModel> list = new ArrayList<>();
    private ItemChatBinding binding;
    private LayoutInflater inflater;
    private ChatModel model;

    public ChatAdapter(Context context, List<ChatModel> list) {
    this.list = list;
    this.inflater = LayoutInflater.from(context);
    }

    public void setList(Collection<ChatModel> models) {
        list.addAll(models);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemChatBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ChatViewHolder(binding.getRoot());
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        model = list.get(position);
        binding.txtItemName.setText(model.getName());
        binding.imgItemPerson.setImageResource(model.getImageResource());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
