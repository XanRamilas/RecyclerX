package com.android.recyclerx.sample.adapter.delegate;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.adapter.delegate.BaseSectionDelegate;
import com.android.recyclerx.adapter.delegate.Dispatcher;
import com.android.recyclerx.adapter.factory.ViewHolderBinderFactory;
import com.android.recyclerx.adapter.factory.ViewHolderFactory;
import com.android.recyclerx.sample.R;
import com.android.recyclerx.sample.adapter.dispatcher.DefaultDispatcher;
import com.android.recyclerx.sample.adapter.factories.MusicAlbumViewHolderBinderFactory;
import com.android.recyclerx.sample.adapter.factories.MusicAlbumViewHolderFactory;
import com.android.recyclerx.sample.adapter.factories.MusicArtistViewHolderBinderFactory;
import com.android.recyclerx.sample.adapter.factories.MusicArtistViewHolderFactory;
import com.android.recyclerx.sample.entity.MusicAlbumEntity;
import com.android.recyclerx.sample.entity.MusicArtistEntity;
import com.android.recyclerx.entity.ItemContainer;
import com.android.recyclerx.entity.Section;
import com.android.recyclerx.repository.BaseSectionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vadim Firsov
 */
public class MusicSectionDelegate extends BaseSectionDelegate<Section, ItemContainer> {

    public MusicSectionDelegate(@NonNull BaseSectionRepository<ItemContainer> baseSectionRepository) {
        super(baseSectionRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExpanded() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isForClass(@NonNull Class aClass) {
        return MusicArtistEntity.class.equals(aClass) || MusicAlbumEntity.class.equals(aClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShowSection() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    @SuppressLint("UseSparseArrays")
    public Map<Integer, ViewHolderFactory> createViewHolderFactories() {
        Map<Integer, ViewHolderFactory> factories = new HashMap<>();
        factories.put(R.layout.music_artist_item, new MusicArtistViewHolderFactory());
        factories.put(R.layout.music_album_item, new MusicAlbumViewHolderFactory());
        return factories;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    protected Section createSection() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    protected BaseViewHolderBinder createSectionViewHolderBinder(@Nullable Section section) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    protected List<BaseViewHolderBinder> createItemsViewHolderBinders() {
        List<ViewHolderBinderFactory> factories = new ArrayList<>();
        factories.add(new MusicArtistViewHolderBinderFactory());
        factories.add(new MusicAlbumViewHolderBinderFactory());

        Dispatcher<BaseViewHolderBinder, ItemContainer, ViewHolderBinderFactory> dispatcher = new DefaultDispatcher();
        return dispatcher.dispatchAndBind(getRepository().getItems(), factories);
    }
}