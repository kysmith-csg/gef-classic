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
package org.eclipse.gef.examples.flow.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.examples.flow.model.Activity;
import org.eclipse.gef.examples.flow.model.StructuredActivity;
import org.eclipse.gef.examples.flow.model.commands.DeleteCommand;
import org.eclipse.gef.requests.GroupRequest;

/**
 * @author Daniel Lee
 */
public class ActivityEditPolicy extends ComponentEditPolicy {

	/**
	 * @see ComponentEditPolicy#createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		StructuredActivity parent = (StructuredActivity) (getHost().getParent().getModel());
		DeleteCommand deleteCmd = new DeleteCommand();
		deleteCmd.setParent(parent);
		deleteCmd.setChild((Activity) (getHost().getModel()));
		return deleteCmd;
	}

}
