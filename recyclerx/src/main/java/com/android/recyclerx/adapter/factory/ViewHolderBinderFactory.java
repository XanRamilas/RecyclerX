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

package com.android.recyclerx.adapter.factory;

import com.android.recyclerx.adapter.binder.BaseViewHolderBinder;

/**
 * This interface creates a custom *ViewHolderBinder
 *
 * @param <B> *ViewHolderBinder which extends the {@link BaseViewHolderBinder}
 * @param <T> item (entity) is for binding the model with UI
 * @author Vadim Firsov
 */
public interface ViewHolderBinderFactory<B extends BaseViewHolderBinder, T> {

    /**
     * This method creates the view holder binder
     *
     * @param item is an entity
     * @return a new view holder binder
     */
    B createViewHolderBinder(T item);

    /**
     * This method checks the associate entity class for this factory
     *
     * @param clazz class for checking
     * @return {@code true} if this class is associated with this factory otherwise {@code false}
     */
    boolean isForClass(Class clazz);
}