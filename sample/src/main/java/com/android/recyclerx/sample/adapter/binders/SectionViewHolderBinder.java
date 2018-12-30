package com.android.recyclerx.sample.adapter.binders;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.sample.adapter.holders.SectionViewHolder;
import com.android.recyclerx.sample.adapter.section.MainSection;

/**
 * @author Vadim Firsov
 */
public class SectionViewHolderBinder extends BaseViewHolderBinder<MainSection> {

    public SectionViewHolderBinder(int viewType, @Nullable MainSection item) {
        super(viewType, item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindViewHolderWithItem(@NonNull RecyclerView.ViewHolder viewHolder) {
        SectionViewHolder castedHolder = (SectionViewHolder) viewHolder;
        castedHolder.bindViewHolderWithEntity(getItem());
    }
}