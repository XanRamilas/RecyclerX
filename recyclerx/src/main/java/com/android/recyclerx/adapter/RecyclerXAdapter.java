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

package com.android.recyclerx.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.recyclerx.adapter.aggregator.BaseSectionAggregator;
import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.adapter.factory.ViewHolderFactory;
import com.android.recyclerx.adapter.util.XDiffUtil;
import com.android.recyclerx.callback.SectionClickListener;
import com.android.recyclerx.callback.click.RecyclerViewItemClickListener;
import com.android.recyclerx.entity.Section;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Base class, which works from scratch with restriction functionality.
 * If you want more than functionality of this Adapter you may subclass it or write your implementation.
 *
 * @author Vadim Firsov
 */
public class RecyclerXAdapter extends RecyclerView.Adapter implements RecyclerViewItemClickListener,
        RecyclerView.ItemAnimator.ItemAnimatorFinishedListener {

    private final BaseSectionAggregator mBaseSectionAggregator;

    private final Queue<Runnable> mActionQueue;

    private RecyclerView mRecyclerView;

    private SectionClickListener mSectionClickListener;

    private final Runnable mContentChangedTask = new Runnable() {
        @Override
        public void run() {
            contentChanged();
        }
    };

    /**
     * Create an instance of the {@link RecyclerXAdapter} with {@link BaseSectionAggregator}
     *
     * @param baseSectionAggregator aggregates an information and sends it outside
     */
    public RecyclerXAdapter(@NonNull BaseSectionAggregator baseSectionAggregator) {
        mBaseSectionAggregator = baseSectionAggregator;
        mActionQueue = new ArrayDeque<>();
        addTaskToQueue(new Runnable() {
            @Override
            public void run() {
                reloadBinders();
                contentChanged();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(viewType, parent, false);
        ViewHolderFactory factory = mBaseSectionAggregator.getFactoryByViewType(viewType);
        return factory.createViewHolder(itemView, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        mBaseSectionAggregator.getBinders().get(position).bindViewHolderWithItem(holder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemViewType(int position) {
        return mBaseSectionAggregator.getBinders().get(position).getViewType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return mBaseSectionAggregator.getBinders().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onItemClick(@NonNull RecyclerView.ViewHolder viewHolder, int position, int viewType, int viewId) {
        if (position == RecyclerView.NO_POSITION) {
            return;
        }

        if (mBaseSectionAggregator.getBinders().get(position).getItem() instanceof Section) {
            Section section = (Section) mBaseSectionAggregator.getBinders().get(position).getItem();
            if (section != null) {
                section.setExpanded(!section.isExpanded());
                addTaskToQueue(mContentChangedTask);

                if (mSectionClickListener != null) {
                    mSectionClickListener.onSectionClick(section);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAnimationsFinished() {
        executeTasks();
    }

    /**
     * This method sets the {@link SectionClickListener}
     *
     * @param sectionClickListener listening the clicks on the {@link Section}
     */
    public void setSectionClickListener(@Nullable SectionClickListener sectionClickListener) {
        mSectionClickListener = sectionClickListener;
    }

    /**
     * Sets the new section state value
     *
     * @param clazz      a specific class which helps to found an associated delegate and sets
     *                   new section state value
     * @param isExpanded new section state. If {@code true} the section is expanded otherwise {@code false}
     */
    public void setExpanded(@NonNull Class clazz, boolean isExpanded) {
        mBaseSectionAggregator.setExpanded(clazz, isExpanded);
        addTaskToQueue(mContentChangedTask);
    }

    /**
     * Returns the section state value
     *
     * @param clazz a specific class which helps to found an associated delegate and sets
     *              new section state value
     * @return {@code true} if the section is expanded, otherwise {@code false}
     */
    public boolean isExpanded(@NonNull Class clazz) {
        return mBaseSectionAggregator.isExpanded(clazz);
    }

    /**
     * This method adds new item to the existing collection
     *
     * @param clazz a specific class which helps to found an associated delegate and sets
     *              new item
     * @param item  a new item for adding
     * @param <T>   a type of the item
     */
    @UiThread
    public <T> void addItem(Class clazz, T item) {
        mBaseSectionAggregator.addItem(clazz, item);
        addTaskToQueue(mContentChangedTask);
    }

    /**
     * Adds new items to the existing collection
     *
     * @param clazz a specific class which helps to found an associated delegate and sets
     *              new items
     * @param items a list with new items
     * @param <T>   a type of the items
     */
    @UiThread
    public <T> void addAllItems(Class clazz, List<T> items) {
        mBaseSectionAggregator.addAllItems(clazz, items);
        addTaskToQueue(mContentChangedTask);
    }

    /**
     * Clears old items and fills the collection by new items
     *
     * @param clazz a specific class which helps to found an associated delegate and sets
     *              new items
     * @param items new items
     * @param <T>   a type of the items
     */
    @UiThread
    public <T> void clearAndFillItems(Class clazz, List<T> items) {
        mBaseSectionAggregator.clearAndFillItems(clazz, items);
        addTaskToQueue(mContentChangedTask);
    }

    private void contentChanged() {
        List<BaseViewHolderBinder> oldBinders = new ArrayList<>(mBaseSectionAggregator.getBinders());
        reloadBinders();
        List<BaseViewHolderBinder> newBinders = mBaseSectionAggregator.getBinders();

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new XDiffUtil(oldBinders, newBinders));
        diffResult.dispatchUpdatesTo(this);
    }

    private void reloadBinders() {
        mBaseSectionAggregator.reloadAllBinders();
    }

    private boolean isAnimating() {
        boolean isAnimating = false;
        if (mRecyclerView != null) {
            isAnimating = mRecyclerView.isAnimating();
        }
        return isAnimating;
    }

    private void addTaskToQueue(Runnable runnable) {
        mActionQueue.offer(runnable);
        if (!isAnimating()) {
            executeTasks();
        } else {
            if (mRecyclerView != null && mRecyclerView.getItemAnimator() != null) {
                mRecyclerView.getItemAnimator().isRunning(this);
            }
        }
    }

    private void executeTasks() {
        if (!mActionQueue.isEmpty()) {
            Runnable runnable;
            while (!isAnimating() && (runnable = mActionQueue.poll()) != null) {
                runnable.run();
            }
        }
    }
}