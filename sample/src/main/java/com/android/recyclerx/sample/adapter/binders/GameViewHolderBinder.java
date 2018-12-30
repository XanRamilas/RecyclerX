package com.android.recyclerx.sample.adapter.binders;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.sample.adapter.holders.GameViewHolder;
import com.android.recyclerx.sample.entity.GameEntity;

/**
 * @author Vadim Firsov
 */
public class GameViewHolderBinder extends BaseViewHolderBinder<GameEntity> {

    public GameViewHolderBinder(int viewType, @Nullable GameEntity item) {
        super(viewType, item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindViewHolderWithItem(@NonNull RecyclerView.ViewHolder viewHolder) {
        GameViewHolder castedHolder = (GameViewHolder) viewHolder;
        castedHolder.bindViewHolderWithEntity(getItem());
    }
}