package com.example.administrator.wangyiitem;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Administrator on 2018/2/8 0008.
 */

public class ItemTouchHelperCallback  extends ItemTouchHelper.Callback{
    private OnItemPositionChangeListener mListener;

    public ItemTouchHelperCallback(OnItemPositionChangeListener listener) {
        mListener = listener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlags,0);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (mListener != null){
            return mListener.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
    public interface OnItemPositionChangeListener{
        boolean onItemMove(int fromPos,int toPos);
    }
}
