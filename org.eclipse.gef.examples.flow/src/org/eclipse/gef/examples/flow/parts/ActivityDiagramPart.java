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
package org.eclipse.gef.examples.flow.parts;

import java.util.Map;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackEventListener;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.gef.examples.flow.policies.ActivityContainerEditPolicy;
import org.eclipse.gef.examples.flow.policies.StructuredActivityLayoutEditPolicy;

/**
 * @author hudsonr Created on Jul 16, 2003
 */
public class ActivityDiagramPart extends StructuredActivityPart {

	CommandStackEventListener stackListener = event -> {
		if ((event.getDetail() & CommandStack.POST_MASK) != 0) {
			if (!GraphAnimation.captureLayout(getFigure()))
				return;
			while (GraphAnimation.step())
				getFigure().getUpdateManager().performUpdate();
			GraphAnimation.end();
		}
	};

	@Override
	protected void applyOwnResults(CompoundDirectedGraph graph, Map map) {
	}

	/**
	 * @see org.eclipse.gef.examples.flow.parts.ActivityPart#activate()
	 */
	@Override
	public void activate() {
		super.activate();
		getViewer().getEditDomain().getCommandStack().addCommandStackEventListener(stackListener);
	}

	/**
	 * @see org.eclipse.gef.examples.flow.parts.ActivityPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.NODE_ROLE, null);
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new StructuredActivityLayoutEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new ActivityContainerEditPolicy());
	}

	@Override
	protected IFigure createFigure() {
		Figure f = new Figure() {
			@Override
			public void setBounds(Rectangle rect) {
				int x = bounds.x, y = bounds.y;

				boolean resize = (rect.width != bounds.width) || (rect.height != bounds.height);
				boolean translate = (rect.x != x) || (rect.y != y);

				if (isVisible() && (resize || translate))
					erase();
				if (translate) {
					int dx = rect.x - x;
					int dy = rect.y - y;
					primTranslate(dx, dy);
				}
				bounds.width = rect.width;
				bounds.height = rect.height;
				if (resize || translate) {
					fireFigureMoved();
					repaint();
				}
			}
		};
		f.setLayoutManager(new GraphLayoutManager(this));
		return f;
	}

	/**
	 * @see org.eclipse.gef.examples.flow.parts.ActivityPart#deactivate()
	 */
	@Override
	public void deactivate() {
		getViewer().getEditDomain().getCommandStack().removeCommandStackEventListener(stackListener);
		super.deactivate();
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#isSelectable()
	 */
	@Override
	public boolean isSelectable() {
		return false;
	}

	/**
	 * @see org.eclipse.gef.examples.flow.parts.StructuredActivityPart#refreshVisuals()
	 */
	@Override
	protected void refreshVisuals() {
	}

}
