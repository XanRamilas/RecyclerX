package com.android.recyclerx.sample.adapter.aggregator;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.android.recyclerx.adapter.aggregator.BaseSectionAggregator;
import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.adapter.delegate.BaseSectionDelegate;
import com.android.recyclerx.adapter.factory.ViewHolderFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vadim Firsov
 */
public class SectionDelegateAggregator extends BaseSectionAggregator {

    public SectionDelegateAggregator(@NonNull List<BaseSectionDelegate> delegates) {
        super(delegates);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    @SuppressLint("UseSparseArrays")
    @SuppressWarnings("unchecked")
    protected Map<Integer, ViewHolderFactory> createViewHolderFactories() {
        Map<Integer, ViewHolderFactory> factories = new HashMap<>();

        for (BaseSectionDelegate delegate : getDelegates()) {
            factories.putAll(delegate.createViewHolderFactories());
        }

        return factories;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    protected List<BaseViewHolderBinder> createViewHolderBinders() {
        List<BaseViewHolderBinder> binders = new ArrayList<>();

        for (BaseSectionDelegate delegate : getDelegates()) {
            binders.addAll(delegate.createAllBinders());
        }

        return binders;
    }
}