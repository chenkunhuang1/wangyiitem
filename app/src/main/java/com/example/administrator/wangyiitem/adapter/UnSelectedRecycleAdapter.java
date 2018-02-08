package com.example.administrator.wangyiitem.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wangyiitem.R;

import java.util.List;

/**
 * Created by Administrator on 2018/2/8 0008.
 */

public class UnSelectedRecycleAdapter extends RecyclerView.Adapter<UnSelectedRecycleAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mDatas;

    public UnSelectedRecycleAdapter(Context context, List<String> datas) {
        mContext = context;
        mDatas = datas;
    }

    public interface OnItemClickListener{
        void onItemClick(MyViewHolder holder,int pos);
        void onItemLongClickListener(MyViewHolder viewHolder,int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    private OnItemClickListener mListener;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item,parent,false));
        return holder;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));
        holder.ivDelete.setImageDrawable(mContext.getDrawable(R.drawable.add));
        if (mListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        mListener.onItemClick(holder,holder.getLayoutPosition());
                    }
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.onItemLongClickListener(holder,position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public void addData(String data, int pos) {
        mDatas.add(pos, data);
        notifyItemInserted(pos);
    }

    public void removeData(int pos) {
        mDatas.remove(pos);

        notifyDataSetChanged();
        notifyItemRemoved(pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView ivDelete;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            ivDelete = itemView.findViewById(R.id.delete);
        }
    }
}
