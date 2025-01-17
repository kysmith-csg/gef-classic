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
package org.eclipse.gef.examples.logicdesigner.model;

import org.eclipse.swt.graphics.Image;

import org.eclipse.gef.examples.logicdesigner.LogicMessages;

public class AndGate extends Gate {

	private static final Image AND_ICON = createImage(AndGate.class, "icons/and16.gif"); //$NON-NLS-1$
	static final long serialVersionUID = 1;

	@Override
	public Image getIconImage() {
		return AND_ICON;
	}

	@Override
	public boolean getResult() {
		return getInput(TERMINAL_A) & getInput(TERMINAL_B);
	}

	@Override
	public String toString() {
		return LogicMessages.AndGate_LabelText + " #" + getID(); //$NON-NLS-1$
	}

}
