package com.bakesale.deejmixer.editors;

import java.sql.DatabaseMetaData;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

import com.bakesale.deejmixer.Track;

public class MixEditorInputFactory implements IElementFactory {

	public static final String ID="com.bakesale.deejmixer.mixeditorinputfactory";
	
	@Override
	public IAdaptable createElement(IMemento memento) {
		String trackText = memento.getString(MixEditorInput.KEY_NAME);
		String trackArtist = memento.getString(MixEditorInput.ARTIST_KEY_NAME);
		String trackName = memento.getString(MixEditorInput.SONG_KEY_NAME);
		String trackAlbum = memento.getString(MixEditorInput.ALBUM_KEY_NAME);
		if (trackText != null) {
			return new MixEditorInput(new Track(trackArtist, trackName, trackAlbum, 0, "unknownmood", "unknowngenre")); 
		}
		
		return null;
		
	}

}
