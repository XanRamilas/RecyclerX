package com.android.recyclerx.sample.adapter.section;

import android.support.annotation.Nullable;

import com.android.recyclerx.entity.Section;

/**
 * The simple subclass which stores the title of the section
 *
 * @author Vadim Firsov
 */
public class MainSection extends Section {

    private final String mTitle;

    /**
     * By default
     *
     * @param title title for the section
     */
    public MainSection(String title) {
        mTitle = title;
    }

    /**
     * @return title of the section
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof MainSection)) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        MainSection that = (MainSection) o;

        return mTitle.equals(that.mTitle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        return 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString() + ", mTitle=" + getToString(mTitle);
    }

    /**
     * The simple helper method for getting a {@link String} from any {@link Object}
     *
     * @param object any object for getting a {@link String}
     * @return a ready {@link String}
     */
    private static String getToString(@Nullable Object object) {
        return object != null ? object.toString() : "null";
    }
}