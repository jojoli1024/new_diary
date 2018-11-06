package com.jojo.diary.memo;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jojo.diary.R;
import com.jojo.diary.db.DBManager;
import com.jojo.diary.db.DBhelper;
import com.jojo.diary.view.ViewFragment;
import com.jojo.diary.view.diaryItem;
import com.jojo.diary.view.recycleAdapter;

import java.util.List;

public class memo_recycleAdapter extends RecyclerView.Adapter<memo_recycleAdapter.MyViewHolder> {

    public interface OnItemClickListener  {
        void onItemClick(View v, int position);
    }

    public OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener  listener) {
        this.mOnItemClickListener  = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView IV_memo_item_delete;
        private TextView TV_memo_item_info;
        private long memoId;
        private SQLiteDatabase db;
        private DBManager dbManager;

        public MyViewHolder(View view) {
            super(view);
            IV_memo_item_delete = (ImageView) view.findViewById(R.id.IV_memo_item_delete);
            TV_memo_item_info = (TextView) view.findViewById(R.id.TV_memo_item_info);

            this.itemView.setOnClickListener(this);
            IV_memo_item_delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, getAdapterPosition());
            }

//            if(v.getId() == R.id.IV_memo_item_delete){
//                //删除memo
//                memoId = memoItemList.get(getAdapterPosition()).getMemoId();
//
//                db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.jojo.diary/databases/mydiary.db",null);
//                dbManager = new DBManager(db);
//                dbManager.delMemo(memoId);
//            }
        }
    }

    private List<memoItem> memoItemList;
    private MemoFragment memoFragment;

    public memo_recycleAdapter(MemoFragment memoFragment,List<memoItem> memoItemList){
        this.memoFragment=memoFragment;
        this.memoItemList=memoItemList;
    }

    public memo_recycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_item,parent,false);
        memo_recycleAdapter.MyViewHolder holder = new memo_recycleAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return memoItemList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        memoItem item = memoItemList.get(position);
        myViewHolder.TV_memo_item_info.setText(item.getInfo());
    }

    public void add(int position){
        notifyItemInserted(position);
    }
    public void delete(int position){
        notifyItemRemoved(position);
    }
}
