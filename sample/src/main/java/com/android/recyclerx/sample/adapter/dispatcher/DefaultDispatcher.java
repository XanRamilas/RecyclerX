package com.android.recyclerx.sample.adapter.dispatcher;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.adapter.delegate.Dispatcher;
import com.android.recyclerx.adapter.factory.ViewHolderBinderFactory;
import com.android.recyclerx.entity.ItemContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadim Firsov
 */
public class DefaultDispatcher implements Dispatcher<BaseViewHolderBinder, ItemContainer, ViewHolderBinderFactory> {

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<BaseViewHolderBinder> dispatchAndBind(List<ItemContainer> items, List<ViewHolderBinderFactory> factories) {
        List<BaseViewHolderBinder> binders = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            Object item = items.get(i).getItem();

            if (item == null) continue;

            for (int j = 0; j < factories.size(); j++) {
                if (factories.get(j).isForClass(item.getClass())) {
                    binders.add(factories.get(j).createViewHolderBinder(item));
                    break;
                }
            }
        }

        return binders;
    }
}