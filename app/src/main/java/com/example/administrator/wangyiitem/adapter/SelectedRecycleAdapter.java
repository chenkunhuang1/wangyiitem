package com.example.administrator.wangyiitem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wangyiitem.ItemTouchHelperCallback;
import com.example.administrator.wangyiitem.MainActivity;
import com.example.administrator.wangyiitem.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/2/8 0008.
 */

public class SelectedRecycleAdapter extends RecyclerView.Adapter<SelectedRecycleAdapter.MyViewHolder> implements ItemTouchHelperCallback.OnItemPositionChangeListener{
    private MainActivity mContext;
    private List<String> mDatas;
    private OnDeleteIconClickListener mDeleteListener;
    public void setOnDeleteIconClickListener(OnDeleteIconClickListener deleteListener) {
        mDeleteListener = deleteListener;
    }


    public interface OnDeleteIconClickListener{
        void onDeleteIconClick(int pos);
    }


    public interface OnItemClickListener{
        void onItemClickListener(MyViewHolder viewHolder,int pos);
        void onItemLongClickListener(MyViewHolder viewHolder,int pos);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    private OnItemClickListener mListener;


    public SelectedRecycleAdapter(Context context, List<String> datas) {
        mContext = (MainActivity) context;
        mDatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));
        if (mContext.isDeleteIconShow()){
            holder.ivDelete.setVisibility(View.VISIBLE);
        }else {
            holder.ivDelete.setVisibility(View.GONE);
        }
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDeleteListener != null){
                    mDeleteListener.onDeleteIconClick(holder.getLayoutPosition());
                }
            }
        });
        if (mListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClickListener(holder,position);
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
    //根据用户手势，交换Adapter数据集中item的位置
    @Override
    public boolean onItemMove(int fromPos, int toPos) {
        Collections.swap(mDatas,fromPos,toPos);
        notifyItemMoved(fromPos,toPos);
        return true;
    }
    public void addData(String data,int pos){
        mDatas.add(pos,data);
        notifyItemInserted(pos);
    }
    public void removeData(int pos){
        mDatas.remove(pos);
        notifyDataSetChanged();
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
