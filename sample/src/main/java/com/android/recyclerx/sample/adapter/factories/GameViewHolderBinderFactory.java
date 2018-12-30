package com.android.recyclerx.sample.adapter.factories;

import com.android.recyclerx.adapter.factory.ViewHolderBinderFactory;
import com.android.recyclerx.sample.R;
import com.android.recyclerx.sample.adapter.binders.GameViewHolderBinder;
import com.android.recyclerx.sample.entity.GameEntity;

/**
 * @author Vadim Firsov
 */
public class GameViewHolderBinderFactory implements ViewHolderBinderFactory<GameViewHolderBinder, GameEntity> {

    private static final int VIEW_TYPE = R.layout.game_item;

    /**
     * {@inheritDoc}
     */
    @Override
    public GameViewHolderBinder createViewHolderBinder(GameEntity gameEntity) {
        return new GameViewHolderBinder(VIEW_TYPE, gameEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isForClass(Class aClass) {
        return GameEntity.class.equals(aClass);
    }
}