package tk.teknorapture.wurmunlimited.clientmods.journal;
/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 */
import java.nio.file.Path;

public class JournalData {

	Path DataPath;
	Path ModFolderPath;
	
	JournalData() {
		
	}
	
	public JournalData(Path dataPath, Path modFolderPath) {
		DataPath = dataPath;
		ModFolderPath = modFolderPath;
	}

}
