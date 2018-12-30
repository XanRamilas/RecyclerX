package com.android.recyclerx.sample.entity;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

/**
 * A music album entity
 *
 * @author Vadim Firsov
 */
public class MusicAlbumEntity {

    private final Drawable mCover;

    private final String mTitle;

    /**
     * @param cover cover image
     * @param title title
     */
    public MusicAlbumEntity(Drawable cover, String title) {
        mCover = cover;
        mTitle = title;
    }

    /**
     * @return cover image
     */
    public Drawable getCover() {
        return mCover;
    }

    /**
     * @return title
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

        if (!(o instanceof MusicAlbumEntity)) {
            return false;
        }

        MusicAlbumEntity that = (MusicAlbumEntity) o;

        return mCover.equals(that.mCover) && mTitle.equals(that.mTitle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (mCover != null ? mCover.hashCode() : 0);
        result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "mCover=" + getToString(mCover) + ", "
                + "mTitle=" + getToString(mTitle);
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