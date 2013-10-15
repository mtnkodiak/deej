package com.bakesale.deejmixer.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

import com.bakesale.deejmixer.Track;

public class MixEditorInput implements IEditorInput, IPersistableElement {

    //private final int id;
    public static String KEY_NAME = "PersistedName";
    public static String ARTIST_KEY_NAME = "PersistedArtist";
    public static String SONG_KEY_NAME = "PersistedSong";
    public static String ALBUM_KEY_NAME = "PersistedAlbum";
    
    private Track track;

    public MixEditorInput(Track track) {
    	super();
    	
    	if (track != null) {
        	this.track = track;    		
    	}
    }

//    public int getId() {
//    	return id;
//    }
    
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return track.toString();
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void saveState(IMemento memento) {
		memento.putString(KEY_NAME, this.track.getAlbumName() + this.track.getArtistName() + this.track.getSongName());
	}
	
	@Override
	public String getFactoryId() {
		// TODO Auto-generated method stub
		return MixEditorInputFactory.ID;
	}

}
