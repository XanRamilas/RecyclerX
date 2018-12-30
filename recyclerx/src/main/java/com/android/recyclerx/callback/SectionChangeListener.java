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

import com.android.recyclerx.entity.Section;

/**
 * A callback interface is for listening to and handling any actions inside the {@link Section} or the subclasses
 *
 * @author Vadim Firsov
 */
public interface SectionChangeListener {

    /**
     * This method is called when any actions happen inside the {@link Section} or the subclasses
     *
     * @param section where any actions happened
     */
    <S extends Section> void onChanged(S section);
}