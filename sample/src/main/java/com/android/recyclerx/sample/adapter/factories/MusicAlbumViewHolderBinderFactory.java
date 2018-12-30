package com.android.recyclerx.sample.adapter.factories;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.adapter.factory.ViewHolderBinderFactory;
import com.android.recyclerx.sample.R;
import com.android.recyclerx.sample.adapter.binders.MusicAlbumViewHolderBinder;
import com.android.recyclerx.sample.entity.MusicAlbumEntity;

/**
 * @author Vadim Firsov
 */
public class MusicAlbumViewHolderBinderFactory implements ViewHolderBinderFactory<BaseViewHolderBinder, MusicAlbumEntity> {

    private static final int VIEW_TYPE = R.layout.music_album_item;

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseViewHolderBinder createViewHolderBinder(MusicAlbumEntity musicAlbumEntity) {
        return new MusicAlbumViewHolderBinder(VIEW_TYPE, musicAlbumEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isForClass(Class aClass) {
        return MusicAlbumEntity.class.equals(aClass);
    }
}