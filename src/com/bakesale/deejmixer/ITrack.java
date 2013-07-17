/**
 * 
 */
package com.bakesale.deejmixer;

/**
 * @author hbl2686
 *
 */
public interface ITrack {

	/**
	 * Returns the length of the track in ms
	 * @return length of the track
	 */
	public int getLength();  
	
	public String getArtistName();
	public String getSongName();
	public String getAlbumName();
	public String getMood();
	public String getGenre();
	
}
