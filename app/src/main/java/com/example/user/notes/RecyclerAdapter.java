package com.example.user.notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 1/15/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    static List<DatabaseModel> dblist;
    static Context context;


    public RecyclerAdapter(Context context, List<DatabaseModel> dblist) {
        this.dblist = new ArrayList<DatabaseModel>();
        this.context = context;
        this.dblist = dblist;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, null);
        ViewHolder viewHolder = new ViewHolder(layoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.names.setText(dblist.get(position).getTitle());
        holder.email.setText(dblist.get(position).getSubject());

    }

    @Override
    public int getItemCount() {
        return dblist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView names, email;

        public ViewHolder(final View itemView) {
            super(itemView);
            names = (TextView) itemView.findViewById(R.id.rvname);
            email = (TextView) itemView.findViewById(R.id.rvemail);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder alertDialogBuilter = new AlertDialog.Builder(context);
                    alertDialogBuilter.setTitle("Change your notes.....! ");
                    alertDialogBuilter.setCancelable(false);

                    alertDialogBuilter.setPositiveButton("Yes ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent uintent = new Intent(context, ModifyActivity.class);
                            Bundle uextras = new Bundle();
                            uextras.putInt("uposition", getAdapterPosition());
                            uintent.putExtras(uextras);
                            context.startActivity(uintent);

                        }
                    });
                    alertDialogBuilter.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //DatabaseHandler dhhandler = new DatabaseHandler(context);
                            // int position=dblist.get(itemView);

                            //   dhhandler.deleteInfo(position);


                        }

                    });


                    AlertDialog alertDialog = alertDialogBuilter.create();
                    alertDialog.show();

                    return true;
                }
            });

        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DetailesActivity.class);
            Bundle extras = new Bundle();
            extras.putInt("position", getAdapterPosition());
            intent.putExtras(extras);
            context.startActivity(intent);

        }
    }


}
