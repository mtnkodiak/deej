package com.bakesale.deejmixer.editors;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.ui.IWorkbenchWindow;


public class MixEditorDropListener implements DropTargetListener {

	private IWorkbenchWindow mixEditorWindow;
	
	
	public MixEditorDropListener(IWorkbenchWindow iWorkbenchWindow) {
		this.mixEditorWindow = iWorkbenchWindow;
	}

	@Override
	public void dragEnter(DropTargetEvent event) {
		System.out.println("dragenter");
	}

	@Override
	public void dragLeave(DropTargetEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragOperationChanged(DropTargetEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragOver(DropTargetEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drop(DropTargetEvent event) {
		if (LocalSelectionTransfer.getTransfer().isSupportedType(event.currentDataType))
		{
		    ISelection sel = LocalSelectionTransfer.getTransfer().getSelection();
		    
		    
		    
		}
	}

	@Override
	public void dropAccept(DropTargetEvent event) {

	}

}
