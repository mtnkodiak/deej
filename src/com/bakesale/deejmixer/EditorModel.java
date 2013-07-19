package com.bakesale.deejmixer;

import java.util.ArrayList;
import java.util.List;

public class EditorModel {

	private static EditorModel model;
	private ArrayList<ITrack> tracks;
	
	private EditorModel() {
		Track track = new Track("Genesis", "Abacab", "UNKNOWN", 1000, "Driving", "Rock");
		tracks.add(track);
		track = new Track("The Presets", "Youth In Trouble", "UNKNOWN", 2100, "Upbeat", "Electronic");
		tracks.add(track);
	}
	
	public static EditorModel getInstance() {
		if (model == null) {
			model = new EditorModel();
		}
		return model;
	}
	
	public List<ITrack> getTracks() {
		return tracks;
	}
	
	public Track getTrackByID(int id) {
		return null;
	}
}
