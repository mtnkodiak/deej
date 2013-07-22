package com.bakesale.deejmixer;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;


public class View extends ViewPart {
	public View() {
	}
	public static final String ID = "deejmixer.view";

	private TableViewer viewer;

	private TableColumn tblclmnTrackName;

	private TableColumn tblclmnLength;

	private TableColumn tblclmnRunningTime;

	/**
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			UserPrefs userPrefs = UserPrefs.getInstance();
			String libraryDir = userPrefs.getLibraryLocation();

		    Path p = Paths.get(libraryDir);
		    final List<Path> fileArray = new ArrayList<Path>();
		    SimpleFileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
		      @Override
		      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
		          throws IOException {
		    	String fileExtension = FilenameUtils.getExtension(file.getFileName().toString());
		    	if (fileExtension.equalsIgnoreCase("MP3")) {
			        System.out.println(file);
			        fileArray.add(file);		    		
		    	}
		        return FileVisitResult.CONTINUE;
		      }
		    };
		    
			try {
				Files.walkFileTree(p, fv);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// now we have a list of paths. Make them into MP3Files.
		    List<MP3File> mp3Files = new ArrayList<>();
		    for (Path path : fileArray) {
		    	MP3File mp3File = null;
				try {
					mp3File = new MP3File(path.toFile());
				} catch (IOException | TagException e) {
					System.out.println("trouble parsing " + path.toString() + ": " + e.getMessage());
					//e.printStackTrace();
					continue; //skip it!
				}
				mp3Files.add(mp3File);
		    }

		    System.out.println("returning the list of files: ");
		    for (MP3File file : mp3Files) {
		    	System.out.println("\t" + file.getMp3file().getName());
		    }
		    return mp3Files.toArray();
			
		}
	}

	class ViewLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			MP3File mp3File = (MP3File)obj;
			String resultStr = null;
			if (mp3File.hasID3v2Tag()) {
				switch (index) {
				case 0: //artist
					System.out.println("getColumnText found '" + mp3File.getID3v2Tag().getLeadArtist() + "' for artist name (filename=" + mp3File.getMp3file().getName());
					resultStr = mp3File.getID3v2Tag().getLeadArtist();
					break;
				case 1: //song title
					System.out.println("getColumnText found '" + mp3File.getID3v2Tag().getSongTitle() + "' for song name (filename=" + mp3File.getMp3file().getName());
					resultStr = mp3File.getID3v2Tag().getSongTitle();
					break;
				case 2: //duration
//					File file = mp3File.getMp3file();
//					AudioFileFormat audioFileFormat = null;
//					try {
//						audioFileFormat = AudioSystem.getAudioFileFormat(file);
//					} catch (UnsupportedAudioFileException | IOException e) {
//						System.out.println("OOPSIE on file " + file.getName() + ": " + e.getMessage());
//						break;
//					}
//					// get all properties
//					Map<String, Object> properties = audioFileFormat.properties();
//					// duration is in microseconds
//					Long duration = (Long) properties.get("duration");
//
//					return duration.toString();
					break;
				default:
					resultStr = "UNKNOWN";
				}
			} else  if (mp3File.hasID3v1Tag()){
				resultStr = mp3File.getID3v1Tag().getArtist();
			} else {
				resultStr = "WTF - no mp3 tags at all!";
			}
			
			return resultStr;
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);

		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		tblclmnTrackName = new TableColumn(table, SWT.NONE);
		tblclmnTrackName.setWidth(100);
		tblclmnTrackName.setText("Name");
		
		tblclmnLength = new TableColumn(table, SWT.NONE);
		tblclmnLength.setWidth(354);
		tblclmnLength.setText("Length");
		
		tblclmnRunningTime = new TableColumn(table, SWT.NONE);
		tblclmnRunningTime.setWidth(100);
		tblclmnRunningTime.setText("Running Time");
		
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		// Provide the input to the ContentProvider
		viewer.setInput(new String("One"));
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}