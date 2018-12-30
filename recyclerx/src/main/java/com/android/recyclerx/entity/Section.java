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

package com.android.recyclerx.entity;

import android.support.annotation.Nullable;

import com.android.recyclerx.callback.SectionChangeListener;

/**
 * The base class is for creating custom sections.
 * In the basic understanding, there can be two states of the section:
 * 1. The section is collapsed;
 * 2. The section is expanded.
 *
 * @author Vadim Firsov
 */
public abstract class Section {

    private boolean mIsExpanded;

    private SectionChangeListener mSectionChangeListener;

    /**
     * This method sets the section state such as expanded or collapsed
     *
     * @param isExpanded {@code true} if the section is expanded otherwise {@code false}
     * @return self object
     */
    public Section setExpanded(boolean isExpanded) {
        mIsExpanded = isExpanded;
        if (mSectionChangeListener != null) {
            mSectionChangeListener.onChanged(this);
        }
        return this;
    }

    /**
     * This method sets the listener for handling the changes inside the Section
     *
     * @param sectionChangeListener is a listener for handling the changes
     * @return self object
     */
    public Section setSectionChangeListener(@Nullable SectionChangeListener sectionChangeListener) {
        mSectionChangeListener = sectionChangeListener;
        return this;
    }

    /**
     * @return {@code true} if the section is expanded otherwise {@code false}
     */
    public boolean isExpanded() {
        return mIsExpanded;
    }

    /**
     * @return listener for handling the changes inside the section
     */
    @Nullable
    public SectionChangeListener getSectionChangeListener() {
        return mSectionChangeListener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Section)) {
            return false;
        }

        Section that = (Section) o;

        return mIsExpanded == that.mIsExpanded &&
                mSectionChangeListener != null &&
                mSectionChangeListener.equals(that.mSectionChangeListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Boolean.valueOf(mIsExpanded).hashCode();
        return 31 * result + (mSectionChangeListener != null ? mSectionChangeListener.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "mIsExpanded=" + mIsExpanded + ", "
                + "mSectionChangeListener=" + getToString(mSectionChangeListener);
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