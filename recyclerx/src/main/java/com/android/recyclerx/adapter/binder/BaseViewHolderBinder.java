/*
 * Copyright © 2018 - 2019, Vadim Firsov.
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

package com.android.recyclerx.adapter.binder;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.android.recyclerx.adapter.factory.ViewHolderFactory;

/**
 * The abstract class is for creating specific binders
 *
 * @param <T> an item (entity) for binding
 * @author Vadim Firsov
 */
public abstract class BaseViewHolderBinder<T> {

    @IdRes
    private final int mViewType;

    private final T mItem;

    /**
     * @param viewType a view type to get associated {@link ViewHolderFactory}
     * @param item     is an entity
     */
    public BaseViewHolderBinder(@IdRes int viewType, @Nullable T item) {
        mViewType = viewType;
        mItem = item;
    }

    /**
     * @return an item (entity) for a view holder
     */
    @Nullable
    public T getItem() {
        return mItem;
    }

    /**
     * @return a view type to get associated {@link ViewHolderFactory}
     */
    @IdRes
    public int getViewType() {
        return mViewType;
    }

    /**
     * This method binds an item (entity) with a view holder
     *
     * @param viewHolder view holder for binding with an item
     */
    public abstract void bindViewHolderWithItem(@NonNull RecyclerView.ViewHolder viewHolder);
}