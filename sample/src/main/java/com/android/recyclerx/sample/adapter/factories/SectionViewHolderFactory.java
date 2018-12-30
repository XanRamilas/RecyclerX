package com.android.recyclerx.sample.adapter.factories;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.recyclerx.adapter.factory.ViewHolderFactory;
import com.android.recyclerx.callback.click.RecyclerViewItemClickListener;
import com.android.recyclerx.sample.adapter.holders.SectionViewHolder;

/**
 * @author Vadim Firsov
 */
public class SectionViewHolderFactory implements ViewHolderFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public RecyclerView.ViewHolder createViewHolder(@NonNull View view,
                                                    @NonNull RecyclerViewItemClickListener recyclerViewItemClickListener) {
        return new SectionViewHolder(view, recyclerViewItemClickListener);
    }
}