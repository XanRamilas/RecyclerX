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

package com.android.recyclerx.adapter.delegate;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.adapter.factory.ViewHolderFactory;
import com.android.recyclerx.entity.ItemContainer;
import com.android.recyclerx.entity.Section;
import com.android.recyclerx.repository.BaseSectionRepository;

/**
 * The delegate is for one of any Sections. DONE, BUT DOC CONSTRUCTOR
 * The functions of the delegate:
 * 1. To create the Section;
 * 2. To monitor the Section state (expanded or collapsed);
 * 3. To create all view holder factories;
 * 4. To create Section view holder binder;
 * 5. To create items view holder binders.
 *
 * @param <S> is the Section
 * @param <T> is a type of any items. If there can be any type of the items inside your sections, use the {@link ItemContainer}
 * @author Vadim Firsov
 */
public abstract class BaseSectionDelegate<S extends Section, T> {

    private final S mSection;

    private final BaseSectionRepository<T> mBaseSectionRepository;

    /**
     * Create an instance of the {@link BaseSectionDelegate} with {@link BaseSectionRepository}
     */
    public BaseSectionDelegate(@NonNull BaseSectionRepository<T> baseSectionRepository) {
        mBaseSectionRepository = baseSectionRepository;
        mSection = createSection();
    }

    /**
     * This method creates all view holder binders
     *
     * @return a list with all view holder binders
     */
    @NonNull
    public final List<BaseViewHolderBinder> createAllBinders() {
        List<BaseViewHolderBinder> binders = new ArrayList<>();

        if (isShowSection()) {
            if (mSection != null) {
                binders.add(createSectionViewHolderBinder(mSection));
            }
            if (isExpanded()) {
                binders.addAll(createItemsViewHolderBinders());
            }
        }

        return binders;
    }

    /**
     * This method adds a new item to the existing items
     *
     * @param item is a new item to add to the existing items
     */
    public void addItem(T item) {
        mBaseSectionRepository.addItem(item);
    }

    /**
     * This method adds new items to the existing items
     *
     * @param allItems is a list with new items
     */
    public void addAllItems(Collection<T> allItems) {
        mBaseSectionRepository.addAllItems(allItems);
    }

    /**
     * This method clears the old items and fills in with new items
     *
     * @param allItems is a list with new items
     */
    public void clearAndFillItems(Collection<T> allItems) {
        mBaseSectionRepository.clearAndFillItems(allItems);
    }

    /**
     * This method sets the section state such as expanded or collapsed
     *
     * @param isExpanded {@code true} if section is expanded otherwise {@code false}
     */
    public void setExpanded(boolean isExpanded) {
        if (mSection != null) {
            mSection.setExpanded(isExpanded);
        }
    }

    /**
     * @return {@code true} if the section is expanded otherwise {@code false}
     */
    public boolean isExpanded() {
        boolean isExpanded = true;

        if (mSection != null) {
            isExpanded = mSection.isExpanded();
        }

        return isExpanded;
    }

    /**
     * @return repository with items
     */
    @NonNull
    protected BaseSectionRepository<T> getRepository() {
        return mBaseSectionRepository;
    }

    /**
     * This method checks the associated entity class for this delegate
     *
     * @param clazz a class for checking
     * @return {@code true} if this class is associated with this delegate otherwise {@code false}
     */
    public abstract boolean isForClass(@NonNull Class clazz);

    /**
     * This method shows the section when the implemented conditions are met
     *
     * @return {@code true} if necessary, show the Section otherwise {@code false}
     */
    public abstract boolean isShowSection();

    /**
     * This method creates the custom view holder factories
     *
     * @return map with factories
     */
    @NonNull
    public abstract Map<Integer, ViewHolderFactory> createViewHolderFactories();

    /**
     * This method creates the {@link Section} or the extended Section
     *
     * @return a ready Section
     */
    @Nullable
    protected abstract S createSection();

    /**
     * This method creates the Section view holder binder
     *
     * @param section {@link Section} or extended Section
     * @return a ready view holder binder
     */
    @Nullable
    protected abstract BaseViewHolderBinder createSectionViewHolderBinder(@Nullable S section);

    /**
     * This method creates the view holder binders for the items inside the Section
     *
     * @return a list with the ready view holder binders
     */
    @NonNull
    protected abstract List<BaseViewHolderBinder> createItemsViewHolderBinders();
}