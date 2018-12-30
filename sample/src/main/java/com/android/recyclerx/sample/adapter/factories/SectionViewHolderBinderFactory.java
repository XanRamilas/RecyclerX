package com.android.recyclerx.sample.adapter.factories;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.adapter.factory.ViewHolderBinderFactory;
import com.android.recyclerx.sample.R;
import com.android.recyclerx.sample.adapter.binders.SectionViewHolderBinder;
import com.android.recyclerx.sample.adapter.section.MainSection;

/**
 * @author Vadim Firsov
 */
public class SectionViewHolderBinderFactory implements ViewHolderBinderFactory<BaseViewHolderBinder, MainSection> {

    private static final int VIEW_TYPE = R.layout.section_item;

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseViewHolderBinder createViewHolderBinder(MainSection mainSection) {
        return new SectionViewHolderBinder(VIEW_TYPE, mainSection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isForClass(Class aClass) {
        return MainSection.class.equals(aClass);
    }
}