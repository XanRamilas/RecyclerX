package com.android.recyclerx.sample.adapter.dispatcher;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.adapter.delegate.Dispatcher;
import com.android.recyclerx.adapter.factory.ViewHolderBinderFactory;
import com.android.recyclerx.sample.entity.MovieEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadim Firsov
 */
public class MovieDispatcher implements Dispatcher<BaseViewHolderBinder, MovieEntity, ViewHolderBinderFactory> {

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<BaseViewHolderBinder> dispatchAndBind(List<MovieEntity> items, List<ViewHolderBinderFactory> factories) {
        List<BaseViewHolderBinder> binders = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            MovieEntity movieEntity = items.get(i);
            for (int j = 0; j < factories.size(); j++) {
                if (factories.get(j).isForClass(movieEntity.getClass())) {
                    binders.add(factories.get(j).createViewHolderBinder(movieEntity));
                    break;
                }
            }
        }

        return binders;
    }
}