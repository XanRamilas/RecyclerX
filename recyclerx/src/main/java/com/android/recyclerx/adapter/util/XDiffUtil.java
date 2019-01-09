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

package com.android.recyclerx.adapter.util;

import android.support.v7.util.DiffUtil;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;

import java.util.List;

/**
 * {@inheritDoc}
 *
 * @author Vadim Firsov
 */
public class XDiffUtil extends DiffUtil.Callback {

    private final List<BaseViewHolderBinder> mOldBinders;
    private final List<BaseViewHolderBinder> mNewBinders;

    /**
     * Creating the new instance of the {@link XDiffUtil} with old items and new
     * items
     *
     * @param oldBinders the list of old items
     * @param newBinders the list of new items
     */
    public XDiffUtil(List<BaseViewHolderBinder> oldBinders, List<BaseViewHolderBinder> newBinders) {
        mOldBinders = oldBinders;
        mNewBinders = newBinders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOldListSize() {
        return mOldBinders.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNewListSize() {
        return mNewBinders.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldBinders.get(oldItemPosition).getViewType() == mNewBinders.get(newItemPosition).getViewType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Object oldObject = mOldBinders.get(oldItemPosition).getItem();
        Object newObject = mNewBinders.get(newItemPosition).getItem();

        boolean isEquals = false;

        if (oldObject != null && newObject != null) {
            isEquals = oldObject.equals(newObject);
        }

        return isEquals;
    }
}