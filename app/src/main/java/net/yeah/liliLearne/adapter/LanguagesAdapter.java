package net.yeah.liliLearne.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.yeah.liliLearne.R;
import net.yeah.liliLearne.model.LanguageBean;

import java.util.List;

public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.ViewHolder> {
    private Context mContext;
    private List<LanguageBean> languageList;
    private OnItemClickLitener mOnItemClickLitener;

    private int preposition = 0;

    public LanguagesAdapter(Context mContext, List<LanguageBean> languageList) {
        this.mContext = mContext;
        this.languageList = languageList;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position,int preposition);
    }

    public void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LanguagesAdapter.ViewHolder holder  = new LanguagesAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_switch_language, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        LanguageBean languageBean = languageList.get(position);
        holder.languageFullName.setText(languageBean.getFullName());
        if (languageBean.isSelected()) {
            holder.selectedImage.setVisibility(View.VISIBLE);
        } else {
            holder.selectedImage.setVisibility(View.GONE);
        }

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos,preposition);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return languageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView selectedImage;
        TextView languageFullName;

        public ViewHolder(View itemView) {
            super(itemView);
            selectedImage = (ImageView) itemView.findViewById(R.id.selected_img);
            languageFullName = (TextView) itemView.findViewById(R.id.language_full_name);
        }

    }
}
