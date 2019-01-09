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

package com.android.recyclerx.entity;

import android.support.annotation.Nullable;

/**
 * This container is needed for wrapping one of the entities
 *
 * @param <T> item for wrapping
 * @author Vadim Firsov
 */
public class ItemContainer<T> {

    private T mItem;

    /**
     * The item is {@code null} by default in a new instance
     */
    public ItemContainer() {
        this(null);
    }

    /**
     * When we create the instance, we can set the item for wrapping
     *
     * @param item item for wrapping
     */
    public ItemContainer(@Nullable T item) {
        mItem = item;
    }

    /**
     * This method sets the item for wrapping
     *
     * @param item item for wrapping
     */
    public void setItem(@Nullable T item) {
        mItem = item;
    }

    /**
     * @return the wrapped item
     */
    @Nullable
    public T getItem() {
        return mItem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ItemContainer<?>)) {
            return false;
        }

        ItemContainer<?> that = (ItemContainer<?>) o;

        return mItem.equals(that.mItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return 31 * 17 + (mItem != null ? mItem.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "mItem=" + getToString(mItem);
    }

    /**
     * The simple helper method is used to get a {@link String} from any {@link Object}
     *
     * @param object is any object for getting a {@link String}
     * @return a ready {@link String}
     */
    private static String getToString(@Nullable Object object) {
        return object != null ? object.toString() : "null";
    }
}