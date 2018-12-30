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
import com.android.recyclerx.sample.adapter.dispatcher.MovieDispatcher;
import com.android.recyclerx.sample.adapter.factories.MovieViewHolderBinderFactory;
import com.android.recyclerx.sample.adapter.factories.MovieViewHolderFactory;
import com.android.recyclerx.sample.adapter.factories.SectionViewHolderBinderFactory;
import com.android.recyclerx.sample.adapter.factories.SectionViewHolderFactory;
import com.android.recyclerx.sample.adapter.section.MainSection;
import com.android.recyclerx.sample.entity.MovieEntity;
import com.android.recyclerx.repository.BaseSectionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vadim Firsov
 */
public class MoviesSectionDelegate extends BaseSectionDelegate<MainSection, MovieEntity> {

    private static final String TITLE = "Movies";

    public MoviesSectionDelegate(@NonNull BaseSectionRepository<MovieEntity> baseSectionRepository) {
        super(baseSectionRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isForClass(@NonNull Class aClass) {
        return MovieEntity.class.equals(aClass);
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
        factories.put(R.layout.section_item, new SectionViewHolderFactory());
        factories.put(R.layout.movie_item, new MovieViewHolderFactory());
        return factories;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    protected MainSection createSection() {
        return new MainSection(TITLE);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    protected BaseViewHolderBinder createSectionViewHolderBinder(@Nullable MainSection mainSection) {
        return new SectionViewHolderBinderFactory().createViewHolderBinder(mainSection);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    protected List<BaseViewHolderBinder> createItemsViewHolderBinders() {
        List<ViewHolderBinderFactory> factories = new ArrayList<>();
        factories.add(new MovieViewHolderBinderFactory());

        Dispatcher<BaseViewHolderBinder, MovieEntity, ViewHolderBinderFactory> dispatcher = new MovieDispatcher();
        return dispatcher.dispatchAndBind(getRepository().getItems(), factories);
    }
}