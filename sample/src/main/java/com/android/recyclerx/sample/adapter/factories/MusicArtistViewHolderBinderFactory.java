package com.android.recyclerx.sample.adapter.factories;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.adapter.factory.ViewHolderBinderFactory;
import com.android.recyclerx.sample.R;
import com.android.recyclerx.sample.adapter.binders.MusicArtistViewHolderBinder;
import com.android.recyclerx.sample.entity.MusicArtistEntity;

/**
 * @author Vadim Firsov
 */
public class MusicArtistViewHolderBinderFactory implements ViewHolderBinderFactory<BaseViewHolderBinder, MusicArtistEntity> {

    private static final int VIEW_TYPE = R.layout.music_artist_item;

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseViewHolderBinder createViewHolderBinder(MusicArtistEntity musicArtistEntity) {
        return new MusicArtistViewHolderBinder(VIEW_TYPE, musicArtistEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isForClass(Class aClass) {
        return MusicArtistEntity.class.equals(aClass);
    }
}