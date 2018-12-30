package com.android.recyclerx.sample.adapter.holders;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.recyclerx.adapter.holder.BaseViewHolder;
import com.android.recyclerx.callback.click.RecyclerViewItemClickListener;
import com.android.recyclerx.sample.R;
import com.android.recyclerx.sample.entity.GameEntity;

/**
 * @author Vadim Firsov
 */
public class GameViewHolder extends BaseViewHolder<GameEntity> {

    private final ImageView mCoverImageView;
    private final TextView mTitleTextView;

    public GameViewHolder(@NonNull View itemView, @NonNull RecyclerViewItemClickListener listener) {
        super(itemView, listener);
        mCoverImageView = itemView.findViewById(R.id.music_album_item_cover_image_view);
        mTitleTextView = itemView.findViewById(R.id.music_album_item_title_text_view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindViewHolderWithEntity(@Nullable GameEntity gameEntity) {
        if (gameEntity != null) {
            mCoverImageView.setImageDrawable(gameEntity.getCover());
            mTitleTextView.setText(gameEntity.getTitle());
        }
    }
}