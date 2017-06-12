package de.tuberlin.snet.prog2.ue08.hashmaps;

import java.io.File;

public class MusicRecognizer {

	public static void main(String[] args) {
		String root = "resources/knownSongs/";
				

		MusicLibrary ml = new MusicLibrary(root);
		
	
		for(String test: new File("resources/unknownSongs/").list()){
			AudioFile af = new AudioFile("resources/unknownSongs/"+test);
			ml.printAudioFile(af);
		}
		
	}
}
	
