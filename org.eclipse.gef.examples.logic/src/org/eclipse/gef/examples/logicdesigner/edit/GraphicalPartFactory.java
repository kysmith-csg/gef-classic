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
package org.eclipse.gef.examples.logicdesigner.edit;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import org.eclipse.gef.examples.logicdesigner.model.Circuit;
import org.eclipse.gef.examples.logicdesigner.model.Gate;
import org.eclipse.gef.examples.logicdesigner.model.LED;
import org.eclipse.gef.examples.logicdesigner.model.LogicDiagram;
import org.eclipse.gef.examples.logicdesigner.model.LogicFlowContainer;
import org.eclipse.gef.examples.logicdesigner.model.LogicLabel;
import org.eclipse.gef.examples.logicdesigner.model.SimpleOutput;
import org.eclipse.gef.examples.logicdesigner.model.Wire;

public class GraphicalPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart child = null;

		if (model instanceof LogicFlowContainer)
			child = new LogicFlowContainerEditPart();
		else if (model instanceof Wire)
			child = new WireEditPart();
		else if (model instanceof LED)
			child = new LEDEditPart();
		else if (model instanceof LogicLabel)
			child = new LogicLabelEditPart();
		else if (model instanceof Circuit)
			child = new CircuitEditPart();
		else if (model instanceof Gate)
			child = new GateEditPart();
		else if (model instanceof SimpleOutput)
			child = new OutputEditPart();
		// Note that subclasses of LogicDiagram have already been matched above,
		// like Circuit
		else if (model instanceof LogicDiagram)
			child = new LogicDiagramEditPart();

		if (child != null) {
			child.setModel(model);
		}

		return child;
	}

}
