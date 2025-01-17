/*******************************************************************************
 * Copyright (c) 2004, 2010 IBM Corporation and others.
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

package org.eclipse.gef.examples.text.model.commands;

import java.util.List;

import org.eclipse.gef.examples.text.model.Container;
import org.eclipse.gef.examples.text.model.ModelElement;
import org.eclipse.gef.examples.text.model.ModelLocation;
import org.eclipse.gef.examples.text.model.ModelUtil;
import org.eclipse.gef.examples.text.model.TextRun;

/**
 * @since 3.1
 */
public class RemoveRange extends MiniEdit {

	private ModelElement removed[];

	private int removalIndices[];

	private Container removedFrom[];

	MiniEdit clipBeginning;

	MiniEdit clipEnding;

	/**
	 * This class removes a range in the model starting from a given TextRun and
	 * offset and terminating at another TextRun and offset location. Removing the
	 * range in the model may result in the merging of 2 or more pieces of the
	 * model. After this edit is applied, the resulting location in the merged model
	 * may be obtained. This location should be used for subsequent insertions.
	 *
	 * @since 3.1
	 */
	public RemoveRange(TextRun begin, int so, TextRun end, int eo) {
		if (begin == end) {
			clipBeginning = new RemoveText(begin, so, eo);
		} else {
			List<ModelElement> remove = ModelUtil.getModelSpan(begin, so, end, eo);

			removed = new ModelElement[remove.size()];
			removedFrom = new Container[removed.length];

			for (int i = remove.size() - 1; i >= 0; i--) {
				removed[i] = (ModelElement) remove.get(i);
				removedFrom[i] = removed[i].getContainer();
			}

			if (so > 0)
				clipBeginning = new RemoveText(begin, so, begin.size());
			if (eo < end.size())
				clipEnding = new RemoveText(end, 0, eo);
		}
	}

	@Override
	public void apply() {
		if (removed != null) {
			removalIndices = new int[removed.length];
			for (int i = removed.length - 1; i >= 0; i--) {
				removalIndices[i] = removedFrom[i].remove(removed[i]);
			}
		}
		if (clipBeginning != null)
			clipBeginning.apply();
		if (clipEnding != null)
			clipEnding.apply();
	}

	@Override
	public boolean canApply() {
		return true;
	}

	@Override
	public ModelLocation getResultingLocation() {
		return clipBeginning.getResultingLocation();
	}

	/**
	 * @see org.eclipse.gef.examples.text.model.commands.MiniEdit#reapply()
	 */
	@Override
	public void reapply() {
		apply();
	}

	@Override
	public void rollback() {
		if (removed != null)
			for (int i = 0; i < removed.length; i++)
				removedFrom[i].add(removed[i], removalIndices[i]);

		if (clipBeginning != null)
			clipBeginning.rollback();
		if (clipEnding != null)
			clipEnding.rollback();
	}

}
