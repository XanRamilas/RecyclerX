package com.android.recyclerx.sample.adapter.binders;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.sample.adapter.holders.MusicAlbumViewHolder;
import com.android.recyclerx.sample.entity.MusicAlbumEntity;

/**
 * @author Vadim Firsov
 */
public class MusicAlbumViewHolderBinder extends BaseViewHolderBinder<MusicAlbumEntity> {

    public MusicAlbumViewHolderBinder(int viewType, @Nullable MusicAlbumEntity item) {
        super(viewType, item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindViewHolderWithItem(@NonNull RecyclerView.ViewHolder viewHolder) {
        MusicAlbumViewHolder castedHolder = (MusicAlbumViewHolder) viewHolder;
        castedHolder.bindViewHolderWithEntity(getItem());
    }
}