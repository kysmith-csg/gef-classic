/*******************************************************************************
 * Copyright (c) 2000, 2022 IBM Corporation and others.
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
package org.eclipse.gef.examples.logicdesigner.model.commands;

import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

import org.eclipse.gef.examples.logicdesigner.LogicMessages;
import org.eclipse.gef.examples.logicdesigner.model.Circuit;
import org.eclipse.gef.examples.logicdesigner.model.LED;
import org.eclipse.gef.examples.logicdesigner.model.LogicDiagram;
import org.eclipse.gef.examples.logicdesigner.model.LogicSubpart;

public class CreateCommand extends org.eclipse.gef.commands.Command {

	private LogicSubpart child;
	private Rectangle rect;
	private LogicDiagram parent;
	private int index = -1;

	public CreateCommand() {
		super(LogicMessages.CreateCommand_Label);
	}

	@Override
	public boolean canExecute() {
		return child != null && parent != null;
	}

	@Override
	public void execute() {
		if (rect != null) {
			Insets expansion = getInsets();
			if (!rect.isEmpty())
				rect.expand(expansion);
			else {
				rect.x -= expansion.left;
				rect.y -= expansion.top;
			}
			child.setLocation(rect.getLocation());
			if (!rect.isEmpty())
				child.setSize(rect.getSize());
		}
		redo();
	}

	private Insets getInsets() {
		if (child instanceof LED || child instanceof Circuit)
			return new Insets(2, 0, 2, 0);
		return new Insets();
	}

	@Override
	public void redo() {
		parent.addChild(child, index);
	}

	public void setChild(LogicSubpart subpart) {
		child = subpart;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setLocation(Rectangle r) {
		rect = r;
	}

	public void setParent(LogicDiagram newParent) {
		parent = newParent;
	}

	@Override
	public void undo() {
		parent.removeChild(child);
	}

}
