package com.example.baby_buy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ListViewHolder>{
    private List<Lists> mListItem;
    public void setData(List<Lists>list){
        this.mListItem =list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);

        return new ListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Lists lists=mListItem.get(position);
        if (lists==null){
            return;
        }
        holder.item_image.setImageResource(lists.getItemId());
        holder.item_name.setText(lists.getName());
        holder.item_price.setText(lists.getPrice());
        holder.item_dis.setText(lists.getDis());

    }

    @Override
    public int getItemCount() {
        if (mListItem!=null){
            return mListItem.size();
        }
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        private ImageView item_image;
        private TextView item_name,item_price,item_dis;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            item_image=itemView.findViewById(R.id.itemImage);
            item_name=itemView.findViewById(R.id.itemName);
            item_price=itemView.findViewById(R.id.itemPrice);
            item_dis=itemView.findViewById(R.id.itemDis);
        }
    }
}
