/*
 * Copyright © 2018, Vadim Firsov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.recyclerx.adapter.holder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.recyclerx.callback.click.RecyclerViewItemClickListener;

/**
 * The base view holder is for creating specific view holders
 *
 * @param <T> an item (entity) for binding
 * @author Vadim Firsov
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final RecyclerViewItemClickListener mListener;

    /**
     * @param itemView root view
     * @param listener listener is for listening to clicks on the view holder
     */
    public BaseViewHolder(@NonNull View itemView, @NonNull RecyclerViewItemClickListener listener) {
        super(itemView);
        mListener = listener;
        itemView.setOnClickListener(this);
    }

    /**
     * @return listener is for listening to clicks on the view holder
     */
    @NonNull
    protected RecyclerViewItemClickListener getListener() {
        return mListener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(View v) {
        mListener.onItemClick(this, getAdapterPosition(), getItemViewType(), v.getId());
    }

    /**
     * This method binds an item (entity) with this view holder
     *
     * @param entity item (entity) for binding
     */
    public abstract void bindViewHolderWithEntity(@Nullable T entity);
}