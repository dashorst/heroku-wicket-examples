/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.examples.resourcedecoration;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderResponseDecorator;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.resource.filtering.JavaScriptFilteredIntoFooterHeaderResponse;

/**
 * An application showing how to return several resources into a single http response.
 * 
 * <p>
 * The key is to register a custom {@link IHeaderResponseDecorator} via
 * {@link Application#setHeaderResponseDecorator(IHeaderResponseDecorator)} that will intercept all
 * resource contributions.
 * 
 * @author jthomerson
 */
public class ResourceDecorationApplication extends WebApplication
{

	@Override
	protected void init()
	{
		super.init();

		getSharedResources().add("merged-resources", new MergedResourcesResource());

		setHeaderResponseDecorator(new IHeaderResponseDecorator()
		{

			public IHeaderResponse decorate(IHeaderResponse response)
			{
				// use grouping header response for the CSS resources, this way we can load several
				// .css files in one http request. See HomePage#renderHead() header.css and
				// footer.css
				GroupingHeaderResponse groupingHeaderResponse = new GroupingHeaderResponse(response);

				// use this header resource decorator to load all JavaScript resources in the page
				// footer (after </body>)
				JavaScriptFilteredIntoFooterHeaderResponse javaScriptFooterResponse = new JavaScriptFilteredIntoFooterHeaderResponse(
					response, "footerJS");

				// finally use one that delegates to the two above
				return new GroupingAndFilteringHeaderResponse(groupingHeaderResponse,
					javaScriptFooterResponse);
			}
		});
	}

	@Override
	public Class<HomePage> getHomePage()
	{
		return HomePage.class;
	}

	public static ResourceDecorationApplication get()
	{
		return (ResourceDecorationApplication)WebApplication.get();
	}
}
