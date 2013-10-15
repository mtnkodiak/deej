package com.bakesale.deejmixer;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.ui.part.EditorInputTransfer;
import org.farng.mp3.MP3File;

import com.bakesale.deejmixer.editors.MixEditor;
import com.bakesale.deejmixer.editors.MixEditorInput;

public class LibraryDragListener implements DragSourceListener {

	private final TableViewer viewer;

	public LibraryDragListener(TableViewer viewer) {
		super();
		this.viewer = viewer;
	}

	@Override
	public void dragStart(DragSourceEvent event) {
		System.out.println("drag started");
		
		//save selection
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		
		LocalSelectionTransfer.getTransfer().setSelection(selection);

		event.doit = (selection.size() > 0) ? true : false;
	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		// Here you do the conversion to the type which is expected.

		if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
			IStructuredSelection selection = (IStructuredSelection) viewer
					.getSelection();
			MP3File firstElement = (MP3File) selection.getFirstElement();
			Object[] selectedObjects = selection.toArray();
			if (selectedObjects.length > 0) {
				EditorInputTransfer.EditorInputData[] inputs = new EditorInputTransfer.EditorInputData[selectedObjects.length];
				for (int i=0; i < selectedObjects.length; i++) {
					if (selectedObjects[i] instanceof Track) {
						Track track = (Track)selectedObjects[i];
						EditorInputTransfer.EditorInputData data = EditorInputTransfer.createEditorInputData(MixEditor.ID, new MixEditorInput(null));
						if (data != null) {
							inputs[i] = data;
						}						
					}
				}
				event.data = inputs;
				return;
			}
		}
		event.doit = false;
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		System.out.println("drag finished");

	}

}
