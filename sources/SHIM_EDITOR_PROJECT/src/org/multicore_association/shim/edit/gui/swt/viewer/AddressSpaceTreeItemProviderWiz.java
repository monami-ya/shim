/*
 * Copyright (c) 2014 eSOL Co.,Ltd. and Nagoya University
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package org.multicore_association.shim.edit.gui.swt.viewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.multicore_association.shim.api.AddressSpace;
import org.multicore_association.shim.api.AddressSpaceSet;
import org.multicore_association.shim.api.SubSpace;
import org.multicore_association.shim.edit.model.ShimModelManager;

/**
 * An AddressSpaceTreeItemProviderWiz for use with a ShimAddressSpaceTreeViewer
 * on a wizard page, which uses the AddressSpaceSet to obtain the elements of a
 * tree.
 */
public class AddressSpaceTreeItemProviderWiz implements ITreeContentProvider {

	/**
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {
		// NOOP

	}

	/**
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// NOOP
	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(Object parentElement) {

		if (parentElement instanceof AddressSpaceSet) {
			AddressSpaceSet cset = (AddressSpaceSet) parentElement;
			List<AddressSpace> csetList = cset.getAddressSpace();
			return csetList.toArray();
		}

		// When using AddressSpaceTree From Wizard.
		if (parentElement instanceof AddressSpace) {
			AddressSpace as = (AddressSpace) parentElement;
			List<SubSpace> sslist = as.getSubSpace();
			return sslist.toArray();
		}

		return new Object[0];
	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object[] getElements(Object inputElement) {

		if (ShimModelManager.getModelList().size() == 0) {
			Object[] root = new String[] { "NoData2" };
			return root;
		}

		if (inputElement instanceof List) {
			return ((List) inputElement).toArray();

		} else if (inputElement instanceof AddressSpaceSet) {
			AddressSpaceSet cs1 = (AddressSpaceSet) inputElement;
			ArrayList<Object> children = new ArrayList<Object>();

			children.addAll(cs1.getAddressSpace());

			return children.toArray();
		}

		return new Object[0];

	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object element) {
		return null;
	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(Object element) {
		// When using AddressSpaceTree From Wizard.
		if (element instanceof AddressSpace) {
			return true;
		}

		return false;
	}

}
