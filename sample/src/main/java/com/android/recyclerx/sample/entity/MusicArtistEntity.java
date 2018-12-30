package com.android.recyclerx.sample.entity;

import android.support.annotation.Nullable;

/**
 * A music artist entity
 *
 * @author Vadim Firsov
 */
public class MusicArtistEntity {

    private String mArtistName;
    private String mArtistGenre;

    /**
     * @param artistName  artist name
     * @param artistGenre artist genre
     */
    public MusicArtistEntity(String artistName, String artistGenre) {
        mArtistName = artistName;
        mArtistGenre = artistGenre;
    }

    /**
     * @return artist name
     */
    public String getArtistName() {
        return mArtistName;
    }

    /**
     * @return artist genre
     */
    public String getArtistGenre() {
        return mArtistGenre;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof MusicArtistEntity)) {
            return false;
        }

        MusicArtistEntity that = (MusicArtistEntity) o;

        return mArtistName.equals(that.mArtistName) && mArtistGenre.equals(that.mArtistGenre);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (mArtistName != null ? mArtistName.hashCode() : 0);
        result = 31 * result + (mArtistGenre != null ? mArtistGenre.hashCode() : 0);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "mArtistName=" + getToString(mArtistName) + ", "
                + "mArtistGenre=" + getToString(mArtistGenre);
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