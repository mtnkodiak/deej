package com.bakesale.deejmixer.editors;

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorInputTransfer;


public class MixEditorDropAdapter extends DropTargetAdapter {

	private IWorkbenchWindow workbenchWindow = null;
	
	public MixEditorDropAdapter(IWorkbenchWindow window) {
		workbenchWindow = window;
	}

	@Override
	public void drop(DropTargetEvent dtde) {
		System.out.println("IN DROP ADAPTER'S drop()");
		if (EditorInputTransfer.getInstance().isSupportedType(dtde.currentDataType)) {
			EditorInputTransfer.EditorInputData[] editorInputs = (EditorInputTransfer.EditorInputData[]) dtde.data;
			for (EditorInputTransfer.EditorInputData inputData : editorInputs) {
				if (inputData != null) {
					IEditorInput input = inputData.input;
					String editorID = inputData.editorId;
					System.out.println("Drop found an input of editor id=" + editorID);
					try {
						workbenchWindow.getActivePage().openEditor(input, editorID);
					} catch (PartInitException e) {
						System.out.println(e.getMessage());
					}
					
				}
			}
			dtde.detail = DND.DROP_COPY;
		}

		//insert dropped data into the list of files (?)
		
//		System.out.println("in MixEditorDropAdapter's Drop(), location = " + dtde. + ", and origin = " + dtde.getSource().toString());
	}
	
	
	

	@Override
	public void dragOver(DropTargetEvent event) {
		event.feedback = DND.FEEDBACK_NONE;
	//	super.dragOver(event);
	}

	@Override
	public void dragEnter(DropTargetEvent event) {
		System.out.println("in dragEnter()");
		if (event.detail == DND.DROP_DEFAULT) {
			if ((event.operations & DND.DROP_COPY) != 0){
				event.detail = DND.DROP_COPY;
			} else {
				event.detail = DND.DROP_NONE;
			}
		}
//		super.dragEnter(event);
	}

	@Override
	public void dropAccept(DropTargetEvent event) {
		System.out.println("in dropAccept()");
		super.dropAccept(event);
	}

	
	
}
