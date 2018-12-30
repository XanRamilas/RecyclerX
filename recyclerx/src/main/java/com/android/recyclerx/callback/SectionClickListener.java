/*
 * Copyright © 2018, Vadim Firsov.
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

package com.android.recyclerx.callback;

import android.support.annotation.NonNull;

import com.android.recyclerx.entity.Section;

/**
 * A callback interface for listening to the clicks on the {@link Section}
 *
 * @author Vadim Firsov
 */
public interface SectionClickListener {

    /**
     * This method is listening to the clicks on the {@link Section}
     *
     * @param section the section where changes happened
     */
    <T extends Section> void onSectionClick(@NonNull T section);
}