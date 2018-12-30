package com.android.recyclerx.sample.adapter.binders;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.sample.adapter.holders.MovieViewHolder;
import com.android.recyclerx.sample.entity.MovieEntity;

/**
 * @author Vadim Firsov
 */
public class MovieViewHolderBinder extends BaseViewHolderBinder<MovieEntity> {

    public MovieViewHolderBinder(int viewType, @Nullable MovieEntity item) {
        super(viewType, item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindViewHolderWithItem(@NonNull RecyclerView.ViewHolder viewHolder) {
        MovieViewHolder castedHolder = (MovieViewHolder) viewHolder;
        castedHolder.bindViewHolderWithEntity(getItem());
    }
}