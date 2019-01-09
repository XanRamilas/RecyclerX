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

package com.android.recyclerx.adapter.aggregator;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.adapter.delegate.BaseSectionDelegate;
import com.android.recyclerx.adapter.factory.ViewHolderFactory;

/**
 * The basic aggregator is for aggregating all data and for sending it outside
 *
 * @author Vadim Firsov
 */
public abstract class BaseSectionAggregator {

    private final List<BaseSectionDelegate> mDelegates;

    private final Map<Integer, ViewHolderFactory> mFactories;

    private final List<BaseViewHolderBinder> mBinders;

    /**
     * Create an instance of the {@link BaseSectionAggregator} with {@link BaseSectionDelegate}
     *
     * @param delegates a list with all ready delegates
     */
    public BaseSectionAggregator(@NonNull List<BaseSectionDelegate> delegates) {
        mDelegates = new ArrayList<>(delegates);
        mFactories = new HashMap<>(createViewHolderFactories());
        mBinders = new ArrayList<>(createViewHolderBinders());
    }

    /**
     * This method sets a new value of section state
     *
     * @param clazz      a specific class by which an associated delegate is found and
     *                   new section state value is set
     * @param isExpanded a new section state. If {@code true} section is expanded otherwise {@code false}
     */
    public void setExpanded(@NonNull Class clazz, boolean isExpanded) {
        for (BaseSectionDelegate delegate : mDelegates) {
            if (delegate.isForClass(clazz)) {
                delegate.setExpanded(isExpanded);
                break;
            }
        }
    }

    /**
     * This method returns the section state value
     *
     * @param clazz a specific class by which an associated delegate is found and
     *              new section state value is set
     * @return {@code true} if section is expanded otherwise {@code false}
     */
    public boolean isExpanded(@NonNull Class clazz) {
        boolean isExpanded = false;

        for (BaseSectionDelegate delegate : mDelegates) {
            if (delegate.isForClass(clazz)) {
                isExpanded = delegate.isExpanded();
                break;
            }
        }

        return isExpanded;
    }

    /**
     * This method adds a new item to the existing ones
     *
     * @param clazz searching for the associated delegate's entity via {@link Class}
     * @param item  a new item for adding
     * @param <T>   the type of any item
     */
    public <T> void addItem(Class clazz, T item) {
        for (BaseSectionDelegate delegate : getDelegates()) {
            if (delegate.isForClass(clazz)) {
                delegate.addItem(item);
                break;
            }
        }
    }

    /**
     * This method adds new items to the existing items
     *
     * @param clazz searching for the associated delegate's entity via {@link Class}
     * @param items new items for adding
     * @param <T>   the type of any item
     */
    public <T> void addAllItems(Class clazz, List<T> items) {
        for (BaseSectionDelegate delegate : getDelegates()) {
            if (delegate.isForClass(clazz)) {
                delegate.addAllItems(items);
                break;
            }
        }
    }

    /**
     * This method clears the old items and fills in with new items
     *
     * @param clazz searching for the associated delegate's entity via {@link Class}
     * @param items new items
     * @param <T>   the type of any item
     */
    public <T> void clearAndFillItems(Class clazz, List<T> items) {
        for (BaseSectionDelegate delegate : getDelegates()) {
            if (delegate.isForClass(clazz)) {
                delegate.clearAndFillItems(items);
                break;
            }
        }
    }

    /**
     * This method returns a {@link ViewHolderFactory} by view type {@link BaseViewHolderBinder#getViewType()}
     *
     * @param viewType an identifier to get a {@link ViewHolderFactory}
     * @return a view holder factory by identifier
     */
    @NonNull
    public ViewHolderFactory getFactoryByViewType(int viewType) {
        return mFactories.get(viewType);
    }

    /**
     * This method resets all binders
     *
     * @return a new size of the binders
     */
    public int reloadAllBinders() {
        mBinders.clear();
        mBinders.addAll(createViewHolderBinders());

        return mBinders.size();
    }

    /**
     * @return all binders
     */
    @NonNull
    public List<BaseViewHolderBinder> getBinders() {
        return mBinders;
    }

    /**
     * @return all delegates
     */
    @NonNull
    protected List<BaseSectionDelegate> getDelegates() {
        return mDelegates;
    }

    /**
     * This method creates all {@link ViewHolderFactory} of the all delegates
     *
     * @return a ready map with all {@link ViewHolderFactory}
     */
    @NonNull
    protected abstract Map<Integer, ViewHolderFactory> createViewHolderFactories();

    /**
     * This method creates all {@link BaseViewHolderBinder} of the all delegates
     *
     * @return a ready list with all {@link BaseViewHolderBinder}
     */
    @NonNull
    protected abstract List<BaseViewHolderBinder> createViewHolderBinders();
}