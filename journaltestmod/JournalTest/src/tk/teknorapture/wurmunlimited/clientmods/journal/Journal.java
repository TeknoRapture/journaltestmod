/**
 * Client side Journal Mod
 */
package tk.teknorapture.wurmunlimited.clientmods.journal;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.gotti.wurmunlimited.modloader.classhooks.HookManager;

import com.wurmonline.client.renderer.gui.JournalView;
import com.wurmonline.client.renderer.gui.JournalIndexPageView;
import com.wurmonline.client.renderer.gui.JournalPageView;
import com.wurmonline.client.renderer.gui.JournalSearchNIWindow;
import com.wurmonline.client.renderer.gui.JournalSearchView;
import com.wurmonline.client.renderer.gui.JournalWindow;
import com.wurmonline.client.resources.*;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.*;

/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 */

//Main code behind the Journal mod
public class Journal {

	public static final boolean RESIZABLE = false;
	
	
	
	//Too many statics GRRRRRR!!!
	public static Path journalDataPath;
	public static Path journalModFolderPath;
	public static final String MODFILENAME = "journal.jar";
	
	private static final boolean DEBUG = true;
	private static final String INSTRUCTIONSFILE = "instructions.txt"; 
	public static final String INSTRUCTIONSMAPPING = "text.instructions";
	public static final String IMAGEICONMAPPING = "images.icons";
	private JournalView journalView;
	
	
	public static Resources resources;
	
	//Remove, may not be necessary
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
	
	@SuppressWarnings("unused")
	private Journal()
	{

	}
	
	public Journal(JournalView journalView)
	{
		super();
		//resources = new Resources(aPackDir,packNames);
		setJournalModFolderPath(Paths.get((Paths.get(".").toAbsolutePath()).toString(),File.separator+"mods"+File.separator+"journal"+File.separator));
		setJournalDataPath(getJournalDataPath(getJournalModFolderPath()));
		
		this.journalView  = journalView;
		
		List<String> packNames = new ArrayList<String>();
		packNames.add(MODFILENAME);
		resources = getResources(getJournalModFolderPath(),packNames);
		
		InitFileStructure(getJournalDataPath(),resources);
		
	}
	
	/**
	 * @return the journalModFolderPath
	 */
	public static Path getJournalModFolderPath() {
		return journalModFolderPath;
	}

	/**
	 * @param journalModFolderPath the journalModFolderPath to set
	 */
	private static void setJournalModFolderPath(Path journalModFolderPath) {
		Journal.journalModFolderPath = journalModFolderPath;
	}
	
	/**
	 * @return the journalDataPath
	 */
	public static Path getJournalDataPath() {
		return journalDataPath;
	}

	/**
	 * @param journalDataPath the journalDataPath to set
	 */
	private static void setJournalDataPath(Path journalDataPath) {
		Journal.journalDataPath = journalDataPath;
	}

	public static BufferedImage getIconImageFromResources()
	{
		//TODO: Error Checking
		return resources.getResourceAsImage(IMAGEICONMAPPING);
	}
	
	private Resources getResources(Path journalModFolderPath, List<String> packNames) {
		File packFolder = new File(journalModFolderPath.toUri());
		
		if(!packFolder.exists()){
			JournalMod.logger.log(Level.SEVERE, "packFolder doesn't exist for: " + journalModFolderPath.toString());
			return null;
		}
		
		Resources resources = new Resources(packFolder,packNames);
		//I can't find a way to error check resources...maybe with "packnames"?
		
		return resources;
	}

	public void addNewPage() {
		JournalMod.logger.log(Level.INFO, "addNewPageCalled");
		
		JournalPageView journalPage = new JournalPageView(JournalWindow.ViewWidth,JournalWindow.ViewHeight);
		
		journalView.setPage(journalPage);
		
		
	}
	
	public void showIndex() {
		
		JournalMod.logger.log(Level.INFO, "showIndexCalled");
		JournalIndexPageView indexPage = new JournalIndexPageView(JournalWindow.ViewWidth,JournalWindow.ViewHeight);
		
		journalView.setPage(indexPage);
		
	}
	
	public void showSearch() {
		JournalMod.logger.log(Level.INFO, "showSearchCalled");
		
		/*
		JournalSearchNIWindow NIPopup = new JournalSearchNIWindow();
		NIPopup.setPosition(200, 200);
		*/
		JournalSearchView searchView = new JournalSearchView(JournalWindow.ViewWidth,JournalWindow.ViewHeight);
		journalView.setPage(searchView);
	}
	
	public void showOptions() {
		JournalMod.logger.log(Level.INFO, "showOptionsCalled");
		
		JournalPageView journalPage = new JournalPageView(JournalWindow.ViewWidth,JournalWindow.ViewHeight,"test read page");
		
		journalView.setPage(journalPage);
	}
	
	//TODO: Move to data layer?
	public boolean InitFileStructure(Path journalDataPath, Resources res)
	{
		/*/// TEST *************************************************************************************************
		LogJarPaths("",Paths.get((Paths.get(".").toAbsolutePath()).toString(),"\\mods\\journal\\journal.jar").toString());
		
		//TestWriteInstructionsAsset();
		
		List<String> packNames = new ArrayList();
		packNames.add("journal.jar");
		File packFile = new File(Paths.get((Paths.get(".").toAbsolutePath()).toString(),"\\mods\\journal\\").toUri());
		
		TestResources(packFile,packNames);
		///TEST *************************************************************************************************
		//*/
		
		//See if data folder exists
		if(Files.exists(journalDataPath))
		{
			if(DEBUG){
				JournalMod.logger.log(Level.INFO,"journalDataPath="+journalDataPath+" Exists, do nothing.");
			}
			
			if(DEBUG)
			{
				//InputStream junk = WriteInstructionsAsset();
				//boolean test = junk == null;
				//String testS = (test) ? "true":"false";
				//JournalMod.logger.log(Level.INFO, "testS==null=" + testS );
				
			}
			//do nothing return true
			return true;
		}
		else
		{
			//create journal data folder if it doesn't exist
			try {
				Files.createDirectories(journalDataPath);
				
				if(DEBUG)
				{
					//a lil fun to test IO speed/memory hit and index page
					Files.write(Paths.get(journalDataPath + File.separator+"test.txt"),GenerateTestLines());
				}
				
				//load Instructions
				boolean instructionsOK = writeInstructionsAsset(journalDataPath, res, INSTRUCTIONSMAPPING);
				//List<String> lines = LoadInstructionsAsset();
				
				//and dump instructions .jrn (a standard .txt)
				//Files.write(Paths.get(journalDataPath + "\\Instructions.jrn"),stream);//nope
				if(DEBUG)
				{
					//JournalMod.logger.log(Level.INFO, stream.toString());
					//boolean test = stream == null;
					//String testS = (test) ? "true":"false";
					//JournalMod.logger.log(Level.INFO, "testS==null=" + testS );
				}
				
			} catch (IOException e) {
				JournalMod.logger.log(Level.WARNING, "Journal failed to create "+journalDataPath+" directory...no Journal for you!!! + " + e.toString());
			}

			//TODO: add a Finally??? (whatever javas equivalent is) to close the streams and IO stuff
		}
		
		return true;
	}

	/**
	 * @return Path to the journal's data
	 */
	private Path getJournalDataPath(Path modFolderPath) {
		//ClassLoader loader = this.getClass().getClassLoader();
		
		//String thisClassNameFixed = this.getClass().getName();
		//thisClassNameFixed = thisClassNameFixed.replace(".", "\\");

		//Path currentDir = Paths.get((Paths.get(".").toAbsolutePath()).toString(),"\\mods\\journal");
		//Path currentDir = modFolderPath;
		
		if(DEBUG){
		//JournalMod.logger.log(Level.INFO,"currentDir.toAbsolutePath()"+currentDir.toAbsolutePath());
		}
		
		//Create Journal data path
		//Path journalDataPath = Paths.get((Paths.get(".").toAbsolutePath()).toString(),"\\mods\\journal\\data");//TODO: Move to the player path?
		Path journalDataPath = Paths.get(modFolderPath.toString(),File.separator+"data");//TODO: Move to the player path?
		
		if(DEBUG){
		JournalMod.logger.log(Level.INFO,"journalDataPath="+journalDataPath);
		}
		return journalDataPath;
	}
	
	//TODO: Move to Data Layer?
	//writes Instructions from resource
	private boolean writeInstructionsAsset(Path journalDataPath, Resources res, String instructionsAssetMapping) {
		JournalMod.logger.log(Level.INFO,"Writing Instructions file to " + journalDataPath);
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			
			
			br = new BufferedReader(new InputStreamReader(res.getResourceAsStream(instructionsAssetMapping)));
			bw = Files.newBufferedWriter(Paths.get(journalDataPath.toString(), File.separator + INSTRUCTIONSFILE));
			
			String line = "";
			//List<String> lines = new ArrayList();
			
			//lines.addAll(br.lines().iterator());
			
			while ((line = br.readLine()) != null)
			{
				bw.write(line);
				bw.newLine();
			}
			/*
			 while ((line = reader.readLine()) != null) {
			        doSomethingWith(line);
			        writer.write(line);
			        // must do this: .readLine() will have stripped line endings
			        writer.newLine();
			 }
			 */
			
			//Files.write(Paths.get(journalDataPath + "\\"+INSTRUCTIONSFILE),br.lines());
			//br.lines().
			
			
		}catch (IOException e) {
			JournalMod.logger.log(Level.WARNING,e.getMessage(), e);
			return false;
		}
		try {
			if(br != null){
				br.close();
			}
			if(bw != null){
				bw.close();
			}
		} catch (IOException e1) {
			JournalMod.logger.log(Level.WARNING,e1.getMessage(), e1);
		}
		
		return true;
	}
	
	//TODO: Move to Data Layer?
	private void LogJarPaths(String pathToScan, String pathToJar) {
		

		JournalMod.logger.log(Level.INFO,"pathToScan, pathToJar = " + pathToScan + ","+ pathToJar + " in Journal.LogJarPaths()");
		final File jarFile = new File(pathToJar);
		//final File jarFile = currentDir.toFile();
		JarFile jar = null;
		if(jarFile.isFile()) {  // Run with JAR file
			if(DEBUG){
				JournalMod.logger.log(Level.INFO, "jarFile IS a file in Journal.LogJarPaths()");
			}
			try {
				
				jar = new JarFile(jarFile);

			    final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
			    while(entries.hasMoreElements()) {
			    	final String name = entries.nextElement().getName();
			    	if(DEBUG){
			    		JournalMod.logger.log(Level.INFO, "path in "+pathToScan+":"+ name );
			    	}
			        
			        if (name.startsWith(pathToScan + "/")) { //filter according to the path
			            
			        	//
			        	JournalMod.logger.log(Level.INFO, "path in "+pathToScan+":"+ name );
			        	
			        }
			    }
			} catch (IOException e1) {
				JournalMod.logger.log(Level.WARNING,e1.getMessage(), e1);
			}
			finally{
			    try {
			    	if(jar != null){
			    		jar.close();
			    	}
			    		else{
			    			JournalMod.logger.log(Level.WARNING,"jar is null while trying to close");
			    		}			    			
				} catch (IOException e) {
					JournalMod.logger.log(Level.WARNING,e.getMessage(), e);
				}
			}
		}
		else
		{
			JournalMod.logger.log(Level.WARNING, "jarFile is not a file in Journal.LogJarPaths()");
		}
	}
	
	//TODO: Move to Data Layer?
	private void TestResources(File aPackDir,List<String> packNames)
	{
		Logger.getLogger(Journal.class.getName()).log(Level.WARNING,"Only for Test purposes. TestResources() BEGIN");
		Logger.getLogger(Journal.class.getName()).log(Level.INFO,"aPackDir.getPath().toString() = "+aPackDir.getPath().toString());
		
		Resources resources = new Resources(aPackDir,packNames);
		
		Logger.getLogger(Journal.class.getName()).log(Level.INFO,"resources.resourceExists(\""+INSTRUCTIONSMAPPING+"\")="+resources.resourceExists(INSTRUCTIONSMAPPING, false));
		
		Logger.getLogger(Journal.class.getName()).log(Level.INFO,"resources.getResource(\""+INSTRUCTIONSMAPPING+"\")="+resources.getResource(INSTRUCTIONSMAPPING));

		InputStream is = null;
		BufferedReader br = null;
		try {
			is = resources.getResourceAsStream(INSTRUCTIONSMAPPING);
			
			if(is == null){
				Logger.getLogger(Journal.class.getName()).log(Level.WARNING,"InputStream is == null");
			}
			else{
				Logger.getLogger(Journal.class.getName()).log(Level.INFO,"InputStream is not null.  WOOT!!!");
				
			}
			
			br = new BufferedReader(new InputStreamReader(is));
			
			if(br!=null){
				String line = "";
			      while ((line = br.readLine()) != null)
			      {
			    	  Logger.getLogger(Journal.class.getName()).log(Level.INFO,"Line from "+INSTRUCTIONSMAPPING+":"+line);
			      }
			}

			
		} catch (IOException e) {
			Logger.getLogger(Journal.class.getName()).log(Level.WARNING,e.getMessage());
		}
		finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Logger.getLogger(Journal.class.getName()).log(Level.WARNING,e.getMessage());
				}
			}
		}
		
		
		Logger.getLogger(Journal.class.getName()).log(Level.WARNING,"Only for Test purposes. TestResources() END");
	}
	
	//TODO: Move to Data Layer?
	private boolean TestWriteInstructionsAsset() {

		try {
			Logger.getLogger(Journal.class.getName()).log(Level.WARNING,"Only for Test purposes. TestWriteInstructionsAsset() BEGIN");
			
			InputStream in = null;
			BufferedReader reader = null;
			try {
				
				///not needed?
				URL url = this.getClass().getClassLoader().getResource(INSTRUCTIONSFILE);
				if (url == null && this.getClass().getClassLoader() == HookManager.getInstance().getLoader()) {
					if(DEBUG){
						Logger.getLogger(Journal.class.getName()).log(Level.INFO, "(url == null && this.getClass().getClassLoader() == HookManager.getInstance().getLoader()");
					}
					url = HookManager.getInstance().getClassPool().find(Journal.class.getName());
					if (url != null) {
						if(DEBUG){
							Logger.getLogger(Journal.class.getName()).log(Level.INFO, "(url != null)");
						}
						String path = url.toString();
						int pos = path.lastIndexOf('!');
						if (pos != -1) {
							if(DEBUG){
								Logger.getLogger(Journal.class.getName()).log(Level.INFO, "(pos != -1)");
							}
							path = path.substring(0, pos) + "!/"+INSTRUCTIONSFILE;
						}
						url = new URL(path);
					}
				}
				if (url != null) {
					//return ImageIO.read(url);
					//Files.rea
					if(DEBUG)
					{
						Logger.getLogger(Journal.class.getName()).log(Level.INFO, "url.toString()=" + url.toString());
					}
					/*url = url.toExternalForm();
					Logger.getLogger(Journal.class.getName()).log(Level.INFO, "url.toExternalForm()=" +url.toExternalForm());
					Logger.getLogger(Journal.class.getName()).log(Level.INFO, "url.getFile()="+url.getFile());
					Logger.getLogger(Journal.class.getName()).log(Level.INFO, "url.getPath()="+url.getPath());
					//return this.getClass().getClassLoader().getResourceAsStream(url.getFile());//nope
					Logger.getLogger(Journal.class.getName()).log(Level.INFO, "blah blah blah blah blah blah blah blah ");
					return this.getClass().getClassLoader().getResourceAsStream("JournalTest/"+instructionsfile);
					// */
					
					Logger.getLogger(Journal.class.getName()).log(Level.INFO, "getClass().getResource(\"/assets/"+INSTRUCTIONSFILE+"\")="+getClass().getResource("/assets/"+INSTRUCTIONSFILE));
					
					
					Logger.getLogger(Journal.class.getName()).log(Level.INFO, "Journal.java: instructionsfile url.getPath()="+url.getPath());
					
					File file = new File(url.toURI());
					
					Logger.getLogger(Journal.class.getName()).log(Level.INFO, "url.toURI().toString()="+url.toURI().toString());
					Logger.getLogger(Journal.class.getName()).log(Level.INFO, "file.exists() returned "+file.exists()+" for URL:"+url);

					
					in = getClass().getClassLoader().getResourceAsStream(INSTRUCTIONSFILE); 
					
					if(in == null)
					{
						Logger.getLogger(Journal.class.getName()).log(Level.INFO, "in is null");
					}
					
					if(in != null)
					{
						reader = new BufferedReader(new InputStreamReader(in));
					}
					
					
					
					return true;
					
				} else {
					return false;
				}
				//*/
				
				//return this.getClass().getClassLoader()
			    //        .getResourceAsStream("/Instructions.txt");
				
			//} catch (IOException e) {
			} catch (Exception e) {
				Logger.getLogger(Journal.class.getName()).log(Level.WARNING, e.getMessage(), e);
				return false;
			}
			finally
			{
				try {
					if(in != null)
					{
						in.close();
					}
					if(reader != null)
					{
						reader.close();
					}
				} catch (IOException e) {
					Logger.getLogger(Journal.class.getName()).log(Level.WARNING, e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			Logger.getLogger(Journal.class.getName()).log(Level.WARNING,"TestWriteInstructionsAsset() END");
		}
		return false;
	}

	//TODO: Move to Data Layer?
	private List<String> GenerateTestLines()
	{
		List<String> testlines = new ArrayList<String>();
		for(int total = 0; total < 5; total ++)
		{
			for(int a = 0;a < 20; a++)
			{
				String spaces = new String();
				for(int numspaces = 0; numspaces < a; numspaces ++)
				{
					spaces = spaces + " ";
				}
				testlines.add(spaces + "aaaaaahhhhhh!!!!!");
			}
			for(int a = 20;a > 0; a--)
			{
				String spaces = new String();
				for(int numspaces = 0; numspaces < a; numspaces ++)
				{
					spaces = spaces + " ";
				}
				testlines.add(spaces + "aaaaaahhhhhh!!!!!");
			}
		}
		
		testlines.add("SPLAT!!!!!!!!!");
		
		return testlines;
	}
	
}
