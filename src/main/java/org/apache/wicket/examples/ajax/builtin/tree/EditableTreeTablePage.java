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
package org.apache.wicket.examples.ajax.builtin.tree;

import org.apache.wicket.extensions.markup.html.tree.table.ColumnLocation;
import org.apache.wicket.extensions.markup.html.tree.table.ColumnLocation.Alignment;
import org.apache.wicket.extensions.markup.html.tree.table.ColumnLocation.Unit;
import org.apache.wicket.extensions.markup.html.tree.table.IColumn;
import org.apache.wicket.extensions.markup.html.tree.table.PropertyTreeColumn;
import org.apache.wicket.extensions.markup.html.tree.table.TreeTable;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.tree.AbstractTree;


/**
 * Page that shows a tree table with editable cells.
 * 
 * @author Matej Knopp
 */
public class EditableTreeTablePage extends BaseTreePage
{
	private static final long serialVersionUID = 1L;

	private final TreeTable tree;

	/**
	 * Page constructor.
	 */
	public EditableTreeTablePage()
	{
		IColumn columns[] = new IColumn[] {
				new PropertyTreeColumn<String>(new ColumnLocation(Alignment.LEFT, 18, Unit.EM),
					"Tree Column", "userObject.property1"),
				new PropertyEditableColumn<String>(new ColumnLocation(Alignment.LEFT, 12, Unit.EM),
					"L2", "userObject.property2"),
				new PropertyEditableColumn<String>(new ColumnLocation(Alignment.MIDDLE, 2,
					Unit.PROPORTIONAL), "M1", "userObject.property3"),
				new PropertyEditableColumn<String>(new ColumnLocation(Alignment.MIDDLE, 2,
					Unit.PROPORTIONAL), "M2", "userObject.property4"),
				new PropertyEditableColumn<String>(new ColumnLocation(Alignment.MIDDLE, 3,
					Unit.PROPORTIONAL), "M3", "userObject.property5"),
				new PropertyEditableColumn<String>(new ColumnLocation(Alignment.RIGHT, 8, Unit.EM),
					"R1", "userObject.property6"),

		};

		Form<Void> form = new Form<Void>("form");
		add(form);

		tree = new TreeTable("treeTable", createTreeModel(), columns);
		form.add(tree);
		tree.getTreeState().collapseAll();
	}

	/**
	 * @see BaseTreePage#getTree()
	 */
	@Override
	protected AbstractTree getTree()
	{
		return tree;
	}

}
