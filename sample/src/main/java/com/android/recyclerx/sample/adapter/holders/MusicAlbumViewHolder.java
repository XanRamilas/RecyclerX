package com.android.recyclerx.sample.adapter.holders;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.recyclerx.adapter.holder.BaseViewHolder;
import com.android.recyclerx.callback.click.RecyclerViewItemClickListener;
import com.android.recyclerx.sample.R;
import com.android.recyclerx.sample.entity.MusicAlbumEntity;

/**
 * @author Vadim Firsov
 */
public class MusicAlbumViewHolder extends BaseViewHolder<MusicAlbumEntity> {

    private final ImageView mCoverImageView;
    private final TextView mAlbumNameTextView;

    public MusicAlbumViewHolder(@NonNull View itemView, @NonNull RecyclerViewItemClickListener listener) {
        super(itemView, listener);
        mCoverImageView = itemView.findViewById(R.id.music_album_item_cover_image_view);
        mAlbumNameTextView = itemView.findViewById(R.id.music_album_item_title_text_view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindViewHolderWithEntity(@Nullable MusicAlbumEntity musicAlbumEntity) {
        if (musicAlbumEntity != null) {
            mCoverImageView.setImageDrawable(musicAlbumEntity.getCover());
            mAlbumNameTextView.setText(musicAlbumEntity.getTitle());
        }
    }
}