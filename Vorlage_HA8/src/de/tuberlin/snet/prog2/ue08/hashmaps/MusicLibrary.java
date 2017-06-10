package de.tuberlin.snet.prog2.ue08.hashmaps;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MusicLibrary extends HashMap<AudioFile, Song> {

	HashMap<AudioFile, Song> mlib;

	public MusicLibrary(String path) {

		File folder = new File(path);

		ArrayList<File> fileArray = new ArrayList<>(Arrays.asList(folder.listFiles()));

		FileNameExtensionFilter mp3Filter = new FileNameExtensionFilter("mp3");

		fileArray.stream().filter(d -> mp3Filter.accept(d))
				 .forEach(d -> mlib.put(new AudioFile(d.getPath()), toSong(d)));

	}

	public Song toSong(File e) {

		String name = e.getName();

		String[] ext = name.split(".");

		String[] meta = ext[1].split("-");

		for (int i = 0; i < 2; i++) {
			meta[i].replace("_", " ");
		}

		return new Song(meta[0], meta[1], meta[2]);
	}

	public void printAudioFile(AudioFile af) {

		System.out.println(mlib.get(af));

	}

}
