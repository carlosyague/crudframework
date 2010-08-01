/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.myfaces.custom.suggestajax.inputsuggestajax;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;

import org.apache.myfaces.custom.suggestajax.SuggestAjax;

/**
 * Provides an input textbox with "suggest" functionality, using an ajax request to the server.
 * <p>
 * A tag that defines an autosuggest control complete with Ajax binding.<br/>
 * This allows you to do real time autocompletion via asynchronous
 * server requests. Note, this control is experimental and it is currently
 * located in the MyFaces sandbox and can be subject to
 * alteration in the immediate future. So use it with care.
 * </p>
 * 
 * @JSFComponent
 *   name = "s:inputSuggestAjax"
 *   class = "org.apache.myfaces.custom.suggestajax.inputsuggestajax.InputSuggestAjax"
 *   tagClass = "org.apache.myfaces.custom.suggestajax.inputsuggestajax.InputSuggestAjaxTag"
 *   tagHandler = "org.apache.myfaces.custom.suggestajax.inputsuggestajax.InputSuggestAjaxTagHandler"
 *   
 * @author Gerald Muellan (latest modification by $Author: skitching $)
 * @author Martin Marinschek
 *
 * @version $Revision: 673833 $ $Date: 2008-07-03 23:58:05 +0200 (Thu, 03 Jul 2008) $
 */

public abstract class AbstractInputSuggestAjax extends SuggestAjax
{
    public static final String COMPONENT_TYPE = "org.apache.myfaces.InputSuggestAjax";
    public static final String DEFAULT_RENDERER_TYPE = "org.apache.myfaces.InputSuggestAjax";

    public AbstractInputSuggestAjax()
    {
        super();

        setRendererType(DEFAULT_RENDERER_TYPE);

        // it makes absolutely no sense to have two autocompletes active at the same time
        // ensure to disable the browser one - this has nothing to do with the
        // autocomplete attribute this component provides
        setAutocomplete("off"); // NON-NLS
    }

    public void encodeChildren(FacesContext context) throws IOException
    {
        super.encodeChildren(context);
    }

    /**
     * If false, the input field is not automatically populated with the first suggested value. 
     * 
     * Default: true
     * 
     * @JSFProperty
     *   defaultValue = "true"
     * @return
     */
    public abstract Boolean getAutoComplete();

    /**
     * Method which gets a suggested Object as an argument and returns a 
     * calculated String label. With this attribute it is possible to 
     * achieve the same mechanism as it can be found at select menues 
     * with the label/value pair.
     * 
     * @JSFProperty
     *   methodSignature = "java.lang.Object"
     *   returnSignature = "java.lang.String"
     *   stateHolder = "true"
     * @return
     */
    public abstract MethodBinding getItemLabelMethod();
}
