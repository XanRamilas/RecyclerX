package com.android.recyclerx.sample.adapter.dispatcher;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.adapter.delegate.Dispatcher;
import com.android.recyclerx.adapter.factory.ViewHolderBinderFactory;
import com.android.recyclerx.sample.entity.GameEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadim Firsov
 */
public class GameDispatcher implements Dispatcher<BaseViewHolderBinder, GameEntity, ViewHolderBinderFactory> {

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<BaseViewHolderBinder> dispatchAndBind(List<GameEntity> items, List<ViewHolderBinderFactory> factories) {
        List<BaseViewHolderBinder> binders = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            GameEntity gameEntity = items.get(i);
            for (int j = 0; j < factories.size(); j++) {
                if (factories.get(j).isForClass(gameEntity.getClass())) {
                    binders.add(factories.get(j).createViewHolderBinder(gameEntity));
                    break;
                }
            }
        }

        return binders;
    }
}