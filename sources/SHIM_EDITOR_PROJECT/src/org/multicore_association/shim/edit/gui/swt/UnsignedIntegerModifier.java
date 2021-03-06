/*
 * Copyright (c) 2014 eSOL Co.,Ltd. and Nagoya University
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package org.multicore_association.shim.edit.gui.swt;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;
import org.multicore_association.shim.edit.gui.jface.ErrorMessagePool;

/**
 * A ModifyListener implementation for an unsigned integer value.
 */
public class UnsignedIntegerModifier implements ModifyListener {

	private ErrorMessagePool pool;

	private String paramName;

	private boolean nillable;

	/**
	 * Constructs a new instance of UnsignedIntegerModifier.
	 * 
	 * @param pool
	 *            the error message pool
	 * @param paramName
	 *            the parameter name to check
	 * @param nillable
	 *            whether the specified parameter can be null or not
	 */
	public UnsignedIntegerModifier(ErrorMessagePool pool, String paramName,
			boolean nillable) {
		this.pool = pool;
		this.paramName = paramName;
		this.nillable = nillable;
	}

	/**
	 * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
	 */
	@Override
	public void modifyText(ModifyEvent event) {
		Object source = event.getSource();
		String msg = null;
		if (source instanceof Text) {
			try {
				String text = ((Text) source).getText();
				boolean nullOrEmpty = (text == null || text.isEmpty());
				if (nullOrEmpty) {
					if (!nillable) {
						msg = "Input an unsigned positive number in \'"
								+ paramName + "\' field.";
					}
				} else {
					int num = Integer.parseInt(text);
					if (num < 0) {
						msg = "Input an unsigned positive number in \'"
								+ paramName + "\' field.";
					}
				}
			} catch (NumberFormatException nf) {
				msg = "Input an unsigned positive number in \'" + paramName
						+ "\' field.";
			}
		}

		pool.setErrorMessage(this, msg);
	}

}
