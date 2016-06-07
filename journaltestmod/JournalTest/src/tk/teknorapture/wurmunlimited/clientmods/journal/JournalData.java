package tk.teknorapture.wurmunlimited.clientmods.journal;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 */
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class JournalData {

	Path DataPath;
	Path ModFolderPath;
	
	public static final String extString = ".";
	
	JournalData() {
		
	}
	
	public JournalData(Path dataPath, Path modFolderPath) {
		DataPath = dataPath;
		ModFolderPath = modFolderPath;
	}
	
	public List<Path> getIndexFileList(Path dataPath)
	{
		List<Path> indexFiles = new ArrayList<Path>();
		
		try
		{
		  DirectoryStream<Path> dirStream;
		  dirStream = Files.newDirectoryStream(dataPath);
		  for (Path entry : dirStream)
		  {
			  indexFiles.add(entry);
		  }
		  dirStream.close();
		}
		catch (IOException e)
		{
			JournalMod.logger.log(Level.WARNING, e.getMessage(), e);
		}
		
		return indexFiles;
	}
	
}
