package com.ovez.booklibrary;

import static com.ovez.booklibrary.AllBooksActivity.ALL_BOOKS_ACTIVITY;
import static com.ovez.booklibrary.AlreadyReadBookActivity.ALREADY_READ_BOOK_ACTIVITY;
import static com.ovez.booklibrary.CurrentlyReadingBookActivity.CURRENTLY_READING_BOOK_ACTIVITY;
import static com.ovez.booklibrary.FavouriteBookActivity.FAVOURITE_BOOK_ACTIVITY;
import static com.ovez.booklibrary.WantToReadActivity.WANT_TO_READ_ACTIVITY;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {

    public BookRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    private ArrayList<Book> books;
    private Context mContext;
    private String parentActivity;

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
            Intent intent = new Intent(mContext, BookActivity.class);
            intent.putExtra("bookId", books.get(position).getId());
            mContext.startActivity(intent);
        });

        holder.tv_authorText.setText(books.get(position).getAuthor());
        holder.tv_shortDesc.setText(books.get(position).getShortDesc());


        // Expanded/collapse book view
        if (books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.rl_expanded.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);


            // Delete button options for books
            switch (parentActivity) {
                case ALL_BOOKS_ACTIVITY:
                    holder.btn_delete.setVisibility(View.GONE);
                    break;
                case ALREADY_READ_BOOK_ACTIVITY:
                    holder.btn_delete.setVisibility(View.VISIBLE);
                    holder.btn_delete.setOnClickListener(view -> {
                        String deletedBook = books.get(position).getName();
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete " + deletedBook + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (Utils.getInstance(mContext).deleteFromAlreadyRead(books.get(position))) {
                                    Toast.makeText(mContext, deletedBook + " Deleted!", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    });
                    break;
                case CURRENTLY_READING_BOOK_ACTIVITY:
                    holder.btn_delete.setVisibility(View.VISIBLE);
                    holder.btn_delete.setOnClickListener(view -> {
                        String deletedBook = books.get(position).getName();
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete " + deletedBook + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (Utils.getInstance(mContext).deleteFromCurrentlyReading(books.get(position))) {
                                    Toast.makeText(mContext, deletedBook + " Deleted!", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    });
                    break;
                case FAVOURITE_BOOK_ACTIVITY:
                    holder.btn_delete.setVisibility(View.VISIBLE);
                    holder.btn_delete.setOnClickListener(view -> {
                        String deletedBook = books.get(position).getName();
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete " + deletedBook + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (Utils.getInstance(mContext).deleteFromFavourites(books.get(position))) {
                                    Toast.makeText(mContext, deletedBook + " Deleted!", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    });
                    break;
                case WANT_TO_READ_ACTIVITY:
                    holder.btn_delete.setVisibility(View.VISIBLE);
                    holder.btn_delete.setOnClickListener(view -> {
                        String deletedBook = books.get(position).getName();
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete " + deletedBook + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (Utils.getInstance(mContext).deleteFromWishlist(books.get(position))) {
                                    Toast.makeText(mContext, deletedBook + " Deleted!", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    });
                    break;
                default:
                    break;
            }
        }
        else{
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.rl_expanded.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private ImageView img_book, downArrow, upArrow;
        private TextView tv_bookName, tv_authorText, tv_shortDesc, btn_delete;
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
            btn_delete = itemView.findViewById(R.id.btn_deleteBook);

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
