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

package com.android.recyclerx.callback.click;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * The callback interface for listening a long clicks on an item like self view holder or other views in a view holder
 *
 * @author Vadim Firsov
 */
public interface RecyclerViewLongItemClickListener {

    /**
     * It is called at long click on a view holder
     *
     * @param viewHolder a self view holder
     * @param position   a position of a view holder when occurred a click
     * @param viewType   a view type from view holder {@link RecyclerView.ViewHolder#getItemViewType()}
     * @param viewId     an identifier of a view
     */
    void onLongItemClick(@NonNull RecyclerView.ViewHolder viewHolder, int position, int viewType, int viewId);
}