package com.ovez.booklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {

    public BookRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    private ArrayList<Book> books = new ArrayList<>();
    private Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_bookName.setText(books.get(position).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getImageURL())
                .into(holder.img_book);

        holder.parent.setOnClickListener(view -> {
            Toast.makeText(mContext, books.get(position).getName() + " Selected", Toast.LENGTH_SHORT).show();
        });

        holder.tv_authorText.setText(books.get(position).getAuthor());
        holder.tv_shortDesc.setText(books.get(position).getShortDesc());

        if (books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.rl_expanded.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
        }
        else{
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.rl_expanded.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private ImageView img_book, downArrow, upArrow;
        private TextView tv_bookName, tv_authorText, tv_shortDesc;
        private RelativeLayout rl_expanded;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            img_book = itemView.findViewById(R.id.img_book);
            tv_bookName = itemView.findViewById(R.id.tv_bookName);
            downArrow = itemView.findViewById(R.id.btn_downArrow);
            upArrow = itemView.findViewById(R.id.btn_upArrow);
            rl_expanded = itemView.findViewById(R.id.rl_expanded);
            tv_authorText = itemView.findViewById(R.id.tv_authorText);
            tv_shortDesc = itemView.findViewById(R.id.tv_shortDesc);

            downArrow.setOnClickListener(view -> {
                Book book = books.get(getAdapterPosition());
                book.setExpanded(!book.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });

            upArrow.setOnClickListener(view -> {
                Book book = books.get(getAdapterPosition());
                book.setExpanded(!book.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });


        }
    }
}
