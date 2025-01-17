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
import org.eclipse.gef.examples.flow.model.Transition;

/**
 * @author Daniel Lee
 */
public class CreateAndAssignSourceCommand extends Command {

	private StructuredActivity parent;
	private Activity child;
	private Activity source;
	private Transition transition;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		parent.addChild(child);
		transition = new Transition(source, child);
	}

	/**
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public void redo() {
		source.addOutput(transition);
		child.addInput(transition);
		parent.addChild(child);
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
	 * Sets the source to the passed activity
	 *
	 * @param activity the source
	 */
	public void setSource(Activity activity) {
		source = activity;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		source.removeOutput(transition);
		child.removeInput(transition);
		parent.removeChild(child);
	}

}
