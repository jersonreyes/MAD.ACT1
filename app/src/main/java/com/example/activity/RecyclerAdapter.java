package com.example.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CardView>{

    private Context con;
    private List<EntryCards> cardList;
    private int id;
    public RecyclerAdapter(Context con, List<EntryCards> cardList) {
        this.con = con;
        this.cardList = cardList;
    }


    @NonNull
    @Override
    public CardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(con);
        View view = inflater.inflate(R.layout.card, null);
        return new CardView(view);
    }
    public void clickedFields(CardView holder){
        Intent toView = new Intent(con, ViewEntry.class);
        toView.putExtra("Position", String.valueOf(holder.getAdapterPosition()));
        con.startActivity(toView);
    };
    @Override
    public void onBindViewHolder(@NonNull CardView holder, int position) {
        EntryCards card = cardList.get(position);
        holder.cardImage.setImageResource(card.getPicture());
        holder.cardTitle.setText(card.getName());
        holder.cardDesc.setText(card.getDescription());
        holder.cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedFields(holder);
            }
        });

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toEdit = new Intent(con, EditEntry.class);
                toEdit.putExtra("Position", String.valueOf(holder.getAdapterPosition()));
                con.startActivity(toEdit);

            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EntryList.cardlist.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    class CardView extends RecyclerView.ViewHolder{
        ImageView cardImage;
        TextView cardTitle;
        TextView cardDesc;
        ImageButton editBtn;
        ImageButton deleteBtn;
        public CardView(@NonNull View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.cardImage);
            cardTitle = itemView.findViewById(R.id.cardTitle);
            cardDesc = itemView.findViewById(R.id.cardDescription);
            editBtn = itemView.findViewById(R.id.editEntry);
            deleteBtn = itemView.findViewById(R.id.deleteEntry);
        }
    }
}
