/*
 * Copyright © 2018 - 2019, Vadim Firsov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.recyclerx.adapter.delegate;

import java.util.List;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;
import com.android.recyclerx.adapter.factory.ViewHolderBinderFactory;

/**
 * The interface is for creating a custom dispatcher.
 * The dispatcher is an object which can create the view holder binders using associated items and factories.
 *
 * @param <B> any *ViewHolderBinder which extends the {@link BaseViewHolderBinder}
 * @param <T> the type of any items (entities)
 * @param <F> any *ViewHolderBinderFactory which extends the {@link ViewHolderBinderFactory}
 * @author Vadim Firsov
 */
public interface Dispatcher<B extends BaseViewHolderBinder, T, F extends ViewHolderBinderFactory> {

    /**
     * This method creates view holder binders inside cycles and returns ready data
     *
     * @param items     items (entities) for searching the associated binders and binding data
     * @param factories is binder's factories
     * @return ready list with view holder binders
     */
    List<B> dispatchAndBind(List<T> items, List<F> factories);
}