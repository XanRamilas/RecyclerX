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
import com.android.recyclerx.sample.adapter.dispatcher.GameDispatcher;
import com.android.recyclerx.sample.adapter.factories.GameViewHolderBinderFactory;
import com.android.recyclerx.sample.adapter.factories.GameViewHolderFactory;
import com.android.recyclerx.sample.adapter.factories.SectionViewHolderBinderFactory;
import com.android.recyclerx.sample.adapter.section.MainSection;
import com.android.recyclerx.sample.entity.GameEntity;
import com.android.recyclerx.repository.BaseSectionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vadim Firsov
 */
public class GamesSectionDelegate extends BaseSectionDelegate<MainSection, GameEntity> {

    private static final String TITLE = "Games";

    public GamesSectionDelegate(@NonNull BaseSectionRepository<GameEntity> baseSectionRepository) {
        super(baseSectionRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isForClass(@NonNull Class aClass) {
        return GameEntity.class.equals(aClass);
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
        factories.put(R.layout.game_item, new GameViewHolderFactory());
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
        factories.add(new GameViewHolderBinderFactory());

        Dispatcher<BaseViewHolderBinder, GameEntity, ViewHolderBinderFactory> dispatcher = new GameDispatcher();
        return dispatcher.dispatchAndBind(getRepository().getItems(), factories);
    }
}