package clear.ui.panel;

public class SystemSet {
	
	static boolean musicOn;

	public static boolean isMusicOn() {
		return musicOn;
	}

	public static void setMusicOn(boolean musicOn) {
		SystemSet.musicOn = musicOn;
	}
	
}
