package de.tuberlin.snet.prog2.ue08.hashmaps;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MusicLibrary extends HashMap<AudioFile, Song> {

	public MusicLibrary(String path) {

		File folder = new File(path);

		ArrayList<File> fileArray = new ArrayList<>(Arrays.asList(folder.listFiles()));
		
		//filters Files for mp3s and puts them into the Library
		fileArray.stream().filter(d -> d.getName().endsWith(".mp3"))
				 .forEach(d -> this.put(new AudioFile(d.getPath()), toSong(d)));

	}
	
	/**
	 * Converts a File to a Song
	 * @param e Filename
	 * @return song
	 */
	public Song toSong(File e) {

		String name = e.getName();

		String[] ext = name.split(".mp3");

		String[] meta = ext[0].split("-");

		return new Song(meta[0], meta[1], meta[2]);
	}
	
	/**
	 * Prints out the song that matches the hash of the given audiofile
	 * @param af
	 */
	public void printAudioFile(AudioFile af) {

		System.out.println(this.get(af));

	}

}
