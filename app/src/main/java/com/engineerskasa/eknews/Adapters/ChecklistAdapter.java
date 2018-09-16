package com.engineerskasa.eknews.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.engineerskasa.eknews.Models.GetNews;
import com.engineerskasa.eknews.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


class CheckListViewHolder extends RecyclerView.ViewHolder{

    CheckBox mSource;
    TextView test;

    public CheckListViewHolder(View itemView) {
        super(itemView);
        mSource = (CheckBox)itemView.findViewById(R.id.sourceCheck);
        test = (TextView)itemView.findViewById(R.id.test);
    }
}

public class ChecklistAdapter extends RecyclerView.Adapter<CheckListViewHolder>{
    private List<GetNews> source;
    private Context mContext;
    private SparseBooleanArray mSparseBooleanArray;


    public ChecklistAdapter(List<GetNews> source, Context context) {
        this.source = source;
        mContext = context;
        mSparseBooleanArray = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public CheckListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v =  inflater.inflate(R.layout.check_layout,parent,false);
        return new CheckListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CheckListViewHolder holder, final int position) {

        holder.mSource.setText(source.get(position).getName());
        holder.mSource.setChecked(mSparseBooleanArray.get(position));

        holder.mSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCheckBox(position, !mSparseBooleanArray.get(position));
            }
        });
    }


    public void checkCheckBox(int position, boolean value) {
        if (value)
            mSparseBooleanArray.put(position, true);
        else
            mSparseBooleanArray.delete(position);

        notifyDataSetChanged();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSparseBooleanArray;
    }

    @Override
    public int getItemCount() {
        return source.size();
    }
}
