/*******************************************************************************
 * Copyright (c) 2000, 2023 IBM Corporation and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.gef.examples.flow.model.commands;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.examples.flow.model.Activity;
import org.eclipse.gef.examples.flow.model.StructuredActivity;

/**
 * @author Daniel Lee
 */
public class CreateCommand extends Command {

	private StructuredActivity parent;
	private Activity child;
	private int index = -1;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (index > 0)
			parent.addChild(child, index);
		else
			parent.addChild(child);
	}

	/**
	 * Sets the index to the passed value
	 *
	 * @param i the index
	 */
	public void setIndex(int i) {
		index = i;
	}

	/**
	 * Sets the parent ActivityDiagram
	 *
	 * @param sa the parent
	 */
	public void setParent(StructuredActivity sa) {
		parent = sa;
	}

	/**
	 * Sets the Activity to create
	 *
	 * @param activity the Activity to create
	 */
	public void setChild(Activity activity) {
		child = activity;
		child.setName("a " + (parent.getChildren().size() + 1)); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		parent.removeChild(child);
	}

}
