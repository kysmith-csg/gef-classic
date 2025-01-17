/*******************************************************************************
 * Copyright (c) 2004, 2023 IBM Corporation and others.
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

package org.eclipse.gef.examples.text.figures;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

/**
 * @since 3.1
 */
public class TreeBorder extends AbstractBorder {

	private final Image image;
	private final String text;
	private Insets insets;

	public TreeBorder(Image image, String text) {
		this.image = image;
		this.text = text;
	}

	@Override
	public Insets getInsets(IFigure figure) {
		if (insets == null) {
			FigureUtilities.getTextExtents(text, figure.getFont(), Dimension.SINGLETON);
			insets = new Insets(Math.max(16, Dimension.SINGLETON.height), 9, 0, 0);
		}
		return insets;
	}

	@Override
	public void paint(IFigure figure, Graphics g, Insets insets) {
		Rectangle where = getPaintRectangle(figure, insets);

		g.translate(where.x, where.y);

		for (int i = 16; i < where.height - 10; i += 2)
			g.drawPoint(9, i);

		g.drawImage(image, 0, 0);
		int h = FigureUtilities.getFontMetrics(g.getFont()).getHeight();
		g.drawText(text, 19, 16 - h);
	}

}
