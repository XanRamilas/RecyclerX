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

package com.android.recyclerx.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.android.recyclerx.entity.Section;

/**
 * The base repository is intended to keep items for the specific {@link Section}
 *
 * @param <T> is the type of any entity
 * @author Vadim Firsov
 */
public class BaseSectionRepository<T> {

    private final List<T> mItems;

    /**
     * By default
     */
    public BaseSectionRepository() {
        mItems = new ArrayList<>();
    }

    /**
     * This method adds a new item to the existing items
     *
     * @param item is a new item to add to the existing items
     */
    public void addItem(T item) {
        mItems.add(item);
    }

    /**
     * This method adds new items to the existing items
     *
     * @param allItems is a list with new items
     */
    public void addAllItems(Collection<T> allItems) {
        mItems.addAll(allItems);
    }

    /**
     * This method clears the old items and fills in with new items
     *
     * @param allItems is a list with new items
     */
    public void clearAndFillItems(Collection<T> allItems) {
        mItems.clear();
        mItems.addAll(allItems);
    }

    /**
     * This method gets an item by position in a list
     *
     * @param position is the position of the item
     * @return the item by position
     */
    public T getItemByPosition(int position) {
        return mItems.get(position);
    }

    /**
     * @return all items
     */
    public List<T> getItems() {
        return mItems;
    }
}