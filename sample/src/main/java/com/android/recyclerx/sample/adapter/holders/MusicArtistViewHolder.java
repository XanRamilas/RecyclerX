package com.android.recyclerx.sample.adapter.holders;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.android.recyclerx.adapter.holder.BaseViewHolder;
import com.android.recyclerx.callback.click.RecyclerViewItemClickListener;
import com.android.recyclerx.sample.R;
import com.android.recyclerx.sample.entity.MusicArtistEntity;

/**
 * @author Vadim Firsov
 */
public class MusicArtistViewHolder extends BaseViewHolder<MusicArtistEntity> {

    private final TextView mArtistNameTextView;
    private final TextView mArtistGenreTextView;

    public MusicArtistViewHolder(@NonNull View itemView, @NonNull RecyclerViewItemClickListener listener) {
        super(itemView, listener);
        mArtistNameTextView = itemView.findViewById(R.id.music_artist_item_name_text_view);
        mArtistGenreTextView = itemView.findViewById(R.id.music_artist_item_genre_text_view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindViewHolderWithEntity(@Nullable MusicArtistEntity musicArtistEntity) {
        if (musicArtistEntity != null) {
            mArtistNameTextView.setText(musicArtistEntity.getArtistName());
            String genre = "Genre: " + musicArtistEntity.getArtistGenre();
            mArtistGenreTextView.setText(genre);
        }
    }
}