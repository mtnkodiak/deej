package com.bakesale.deejmixer;

public class UserPrefs {

	private String libraryLocation;
	private String saveDir;
	
	static private UserPrefs userPrefs;
	
	private UserPrefs(String libraryLocation, String saveDir) {
		super();
		this.libraryLocation = libraryLocation;
		this.saveDir = saveDir;
	}
	
	static public UserPrefs getInstance() {
		if (userPrefs == null) {
			userPrefs = new UserPrefs("/tmp/library-location", "/tmp/playlists");
		}
		return userPrefs;
	}
	
	public String getLibraryLocation() {
		return libraryLocation;
	}
	public void setLibraryLocation(String libraryLocation) {
		this.libraryLocation = libraryLocation;
	}
	public String getSaveDir() {
		return saveDir;
	}
	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}
	
	
	
	
}
