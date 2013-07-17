package com.bakesale.deejmixer;


public class Track implements ITrack {

	private int length;
	private String artistName;
	private String songName;
	private String albumName;
	private String mood;
	private String genre;
	
	public Track(String artistName, String songName, String albumName,
			int length, String mood, String genre) {
		super();
		this.artistName = artistName;
		this.songName = songName;
		this.albumName = albumName;
		this.length = length;
		this.mood = mood;
		this.genre = genre;
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return length;
	}

	@Override
	public String getArtistName() {
		// TODO Auto-generated method stub
		return artistName;
	}

	@Override
	public String getSongName() {
		// TODO Auto-generated method stub
		return songName;
	}

	@Override
	public String getAlbumName() {
		// TODO Auto-generated method stub
		return albumName;
	}

	@Override
	public String getMood() {
		// TODO Auto-generated method stub
		return mood;
	}

	@Override
	public String getGenre() {
		// TODO Auto-generated method stub
		return genre;
	}

}
