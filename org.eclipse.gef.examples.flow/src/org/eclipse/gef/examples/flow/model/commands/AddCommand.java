/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
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
 * AddCommand
 *
 * @author Daniel Lee
 */
public class AddCommand extends Command {

	private Activity child;
	private StructuredActivity parent;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		parent.addChild(child);
	}

	/**
	 * Returns the StructuredActivity that is the parent
	 *
	 * @return the parent
	 */
	public StructuredActivity getParent() {
		return parent;
	}

	/**
	 * Sets the child to the passed Activity
	 *
	 * @param subpart the child
	 */
	public void setChild(Activity newChild) {
		child = newChild;
	}

	/**
	 * Sets the parent to the passed StructuredActiivty
	 *
	 * @param newParent the parent
	 */
	public void setParent(StructuredActivity newParent) {
		parent = newParent;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		parent.removeChild(child);
	}

}
