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
package org.apache.wicket.examples.compref;

import org.apache.wicket.examples.WicketExamplePage;
import org.apache.wicket.markup.html.link.Link;


/**
 * Page with examples on {@link org.apache.wicket.markup.html.link.Link}.
 * 
 * @author Eelco Hillenius
 */
public class PageLinkPage extends WicketExamplePage
{
	/**
	 * Constructor
	 */
	public PageLinkPage()
	{
		// Add a page link. You can use PageLink in several ways.
		// An alternative option would have been to use the constructor with
		// the Page instance argument like:
		//
		// add(new PageLink("pageLink", new NonBookmarkablePage(this)));
		//
		// The disadvantage of that is that you need to create an instance right
		// away, which
		// will be kept as session data. Instead, we use the more verbose
		// IPageLink
		// anonymous class, which will create the page instance only when
		// needed.

		add(new Link<Void>("pageLink")
		{
			@Override
			public void onClick()
			{
				setResponsePage(new NonBookmarkablePage(PageLinkPage.this));
			}
		});

		// Note that this would have had the same effect, except that the link
		// wouldn't check
		// whether it points to the current page and thus should be 'turned off'
		// (but actually
		// we don't need that check here, as we are certain that is not the
		// case)
		//
		// add(new Link("navigateBackLink")
		// {
		// public void onClick()
		// {
		// setResponsePage(return new NonBookmarkablePage(PageLinkPage.this););
		// }
		// });
	}

	/**
	 * Override base method to provide an explanation
	 */
	@Override
	protected void explain()
	{
		String html = "<a wicket:id=\"pageLink\">go to our private/ non bookmarkable page</a>";
		String code = "&nbsp;&nbsp;&nbsp;&nbsp;add(new PageLink(\"pageLink\", new IPageLink() {\n"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public Page getPage() {\n"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return new NonBookmarkablePage(PageLinkPage.this);\n"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public Class getPageIdentity() {\n"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return NonBookmarkablePage.class;\n"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;}));";
		add(new ExplainPanel(html, code));

	}

}