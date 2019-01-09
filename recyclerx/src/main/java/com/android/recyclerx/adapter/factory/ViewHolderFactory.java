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

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.recyclerx.callback.click.RecyclerViewItemClickListener;

/**
 * The interface is for creating a {@link RecyclerView.ViewHolder}
 *
 * @author Vadim Firsov
 */
public interface ViewHolderFactory {

    /**
     * This method creates a {@link RecyclerView.ViewHolder}
     *
     * @param itemView root view to create a view holder
     * @param listener listener that handles clicks on a view holder
     * @return ready view holder
     */
    RecyclerView.ViewHolder createViewHolder(@NonNull View itemView,
                                             @NonNull RecyclerViewItemClickListener listener);
}