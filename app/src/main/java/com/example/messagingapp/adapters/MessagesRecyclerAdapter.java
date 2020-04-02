package com.example.messagingapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.messagingapp.R;
import com.example.messagingapp.models.Message;

import java.util.ArrayList;

public class MessagesRecyclerAdapter extends RecyclerView.Adapter<MessagesRecyclerAdapter.ViewHolder> {

    private ArrayList<Message> mMessages = new ArrayList<>();
    private OnMessageListener mOnMessageListener;

    public MessagesRecyclerAdapter(ArrayList<Message> mMessages, OnMessageListener mOnMessageListener) {
        this.mMessages = mMessages;
        this.mOnMessageListener = mOnMessageListener;
    }

    @NonNull
    @Override
    public MessagesRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_message_list_item, parent, false);
        return new ViewHolder(view, mOnMessageListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesRecyclerAdapter.ViewHolder holder, int position) {
        holder.timestamp.setText(mMessages.get(position).getTimestamp());
        holder.sender.setText(mMessages.get(position).getSender());
        holder.last_message.setText(mMessages.get(position).getLastMessage());
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView sender, last_message, timestamp;
        OnMessageListener mOnMessageListener;

        public ViewHolder(View itemView, OnMessageListener onMessageListener) {
            super(itemView);
            sender = itemView.findViewById(R.id.sender);
            last_message = itemView.findViewById(R.id.last_message);
            timestamp = itemView.findViewById(R.id.timestamp);
            mOnMessageListener = onMessageListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnMessageListener.onMessageClick(getAdapterPosition());
        }
    }

    public interface OnMessageListener{
        void onMessageClick(int position);
    }
}
