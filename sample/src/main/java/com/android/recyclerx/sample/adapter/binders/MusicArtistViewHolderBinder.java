package com.android.recyclerx.sample.adapter.binders;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.sample.adapter.holders.MusicArtistViewHolder;
import com.android.recyclerx.sample.entity.MusicArtistEntity;

/**
 * @author Vadim Firsov
 */
public class MusicArtistViewHolderBinder extends BaseViewHolderBinder<MusicArtistEntity> {

    public MusicArtistViewHolderBinder(int viewType, @Nullable MusicArtistEntity item) {
        super(viewType, item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindViewHolderWithItem(@NonNull RecyclerView.ViewHolder viewHolder) {
        MusicArtistViewHolder castedHolder = (MusicArtistViewHolder) viewHolder;
        castedHolder.bindViewHolderWithEntity(getItem());
    }
}