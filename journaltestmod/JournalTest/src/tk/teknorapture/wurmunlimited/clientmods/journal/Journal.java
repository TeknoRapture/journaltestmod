/**
 * Client side Journal Mod
 */
package tk.teknorapture.wurmunlimited.clientmods.journal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.*;

/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 */


public class Journal {

	public static boolean resizable = false;
	public static boolean debug = true;
	
	private boolean firstRun = true;
	/**
	 * @return firstRun
	 */
	public boolean getFirstRun(){
		return firstRun;
	}

	/**
	 * @param firstRun the firstRun to set
	 */
	protected void setFirstRun(boolean firstRun) { ///probably pointless to add this...
		this.firstRun = firstRun;
	}
	
	public Journal()
	{
		super();
		
	}
	
	
	public void addNewPage() {
		JournalMod.logger.log(Level.INFO, "addNewPageCalled");
	}
	
	public void showIndex() {
		
		JournalMod.logger.log(Level.INFO, "showIndexCalled");
		
	}
	
	public void showSearch() {
		JournalMod.logger.log(Level.INFO, "showSearchCalled");
		
	}
	
	public void showOptions() {
		JournalMod.logger.log(Level.INFO, "showOptionsCalled");
		
	}
	
	public boolean InitFileStructure()
	{
		ClassLoader loader = this.getClass().getClassLoader();
		
		String thisClassNameFixed = this.getClass().getName();
		thisClassNameFixed = thisClassNameFixed.replace(".", "\\");
		//JournalMod.logger.log(Level.INFO, loader.getResource(thisClassNameFixed).toString());//nope
		//JournalMod.logger.log(Level.INFO,"this.getClass().getName()"+this.getClass().getName());//nope
		
		//JournalMod.logger.log(Level.INFO,"thisClassNameFixed="+thisClassNameFixed);//nope
		//JournalMod.logger.log(Level.INFO, loader.getResource(thisClassNameFixed+".class").toString());//nope
		
		//String path = "none";
		
		//path = Journal.class.getResource("Journal.java").toString();//nope
		
		/*
		try (Stream<Path> stream = Files.list(Paths.get(""))) {
		    String joined = stream
		        .map(String::valueOf)
		        .filter(path -> !path.startsWith("."))
		        .sorted()
		        .collect(Collectors.joining("; "));
		    //System.out.println("List: " + joined);
		    JournalMod.logger.log(Level.INFO,"paths="+ joined);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JournalMod.logger.log(Level.INFO,"Stream file patyh failed:"+e.toString());
		}
		*/
		
		Path currentDir = Paths.get(".");
		
		if(debug){
		JournalMod.logger.log(Level.INFO,"currentDir.toAbsolutePath()"+currentDir.toAbsolutePath());
		}
		
		//JournalMod.logger.log(Level.INFO,"Path.this.toString()="+ path);
		
		Path journalDataPath = Paths.get((Paths.get(".").toAbsolutePath()).toString(),"\\mods\\journal\\data");
		
		if(debug){
		JournalMod.logger.log(Level.INFO,"journalDataPath="+journalDataPath);
		}
		
		if(Files.exists(journalDataPath))
		{
			if(debug){
				JournalMod.logger.log(Level.INFO,"journalDataPath="+journalDataPath+" Exists, do nothing.");
			}
			//do nothing return true
			return true;
		}
		else
		{
			//create folder 
			try {
				Files.createDirectories(journalDataPath);
				List<String> lines = new ArrayList<String>();
				lines.add("Test");
				Files.write(Paths.get(journalDataPath + "\\test.txt"),lines);
			} catch (IOException e) {
				JournalMod.logger.log(Level.WARNING, "Journal failed to create /./mods/journal/data directory...no Journal for you!!! + " + e.toString());
			}
			
			//and dump instructions .jrn (.txt)
			
			
			//TODO: add a Finally??? (whatever javas equivalent is) to close the streams and IO stuff
		}
		
		return true;
		//return false;
	}
	
	
}
