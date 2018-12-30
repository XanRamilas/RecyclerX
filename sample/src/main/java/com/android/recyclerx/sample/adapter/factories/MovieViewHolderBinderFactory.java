package com.android.recyclerx.sample.adapter.factories;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.adapter.factory.ViewHolderBinderFactory;
import com.android.recyclerx.sample.R;
import com.android.recyclerx.sample.adapter.binders.MovieViewHolderBinder;
import com.android.recyclerx.sample.entity.MovieEntity;

/**
 * @author Vadim Firsov
 */
public class MovieViewHolderBinderFactory implements ViewHolderBinderFactory<BaseViewHolderBinder, MovieEntity> {

    private static final int VIEW_TYPE = R.layout.movie_item;

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseViewHolderBinder createViewHolderBinder(MovieEntity movieEntity) {
        return new MovieViewHolderBinder(VIEW_TYPE, movieEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isForClass(Class aClass) {
        return MovieEntity.class.equals(aClass);
    }
}