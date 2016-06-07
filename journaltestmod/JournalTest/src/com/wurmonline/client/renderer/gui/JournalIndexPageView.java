/**
 * 
 */
package com.wurmonline.client.renderer.gui;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 * Index Page View
 */
import java.util.logging.Level;

import com.wurmonline.client.GameCrashedException;

/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 * Index Page View for the Journal mod
 */

import tk.teknorapture.wurmunlimited.clientmods.journal.Journal;
import tk.teknorapture.wurmunlimited.clientmods.journal.JournalData;
import tk.teknorapture.wurmunlimited.clientmods.journal.JournalMod;

public class JournalIndexPageView extends WurmBorderPanel {

	Journal journal;
	//WurmArrayPanel mainPanel = new WurmArrayPanel<WurmLabel>("Journal Index", WurmArrayPanel.DIR_VERTICAL);//main panel of the page
	static String pagename = "JournalIndexPage";
	
/*	
	private static final class JournalIndexListItem extends TreeListItem
	{
		private final String indexItemName;
		private final String description;
		
		JournalIndexListItem(String name, String description){
			indexItemName = name;
			this.description = description;
		}
		
		@Override
		int compareTo(TreeListItem item, int sortOn) {
			if (!(item instanceof JournalIndexListItem))
		      {
		        GameCrashedException.warn("Incompatible ITLI comparison");
		        return 0;
		      }
			
			
			
			// TODO Auto-generated method stub: Fix Later
			
			return 0;
		}

		@Override
		String getName() {
			// TODO Auto-generated method stub
			return indexItemName;
		}

		@Override
		String getParameter(int param) {
			/*switch (param)
		      {
		      case 0: 
		    	  return "not used";
		      } // * /
			return "not used";
		}
		
	}
	*/
	WurmArrayPanel<FlexComponent> mainPanel = new WurmArrayPanel<FlexComponent>("Index",WurmArrayPanel.DIR_VERTICAL);
	
	/**
	 * @param _name
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param journal
	 */
	//public JournalIndexPageView(int width, int height, Journal journal) {
	public JournalIndexPageView(int width, int height) {
		super("Journal Index");
		
		setInitialSize(width,height,false);
		
		if(!Journal.RESIZABLE)
		{
			sizeFlags= FlexComponent.FIXED_WIDTH | FlexComponent.FIXED_HEIGHT;// I wish there was a "fill available space" hopefully default is that...
		}

		WurmLabel testLabel = new WurmLabel("Index Page Not Implemented","Test Index Page");
		
		mainPanel.addComponent(testLabel);
		
		this.setComponent(mainPanel, WurmBorderPanel.CENTER);
		
		this.layout();
		
		showIndex(Journal.getJournalDataPath());//I don't like relying on data in another class like this...Fix in refactoring...
	}

	///TODO: Move to custom Label class?
	String stripExtension(String fileName)
	{
		String fileNameNoExt = "Error.";
		fileNameNoExt = fileName.substring(0, fileName.indexOf(JournalData.extString));
		
		return fileNameNoExt;
	}
	
	public void showIndex(Path dataPath)
	{
		List<Path> indexFiles = getIndexList(dataPath);
		
		mainPanel.removeAllComponents();
		
		///TODO: Pull instructions page and list first.
		///TODO: Descend from one of the labels, maybe WurmLabel with a custom renderer? Or a  and include the path in that
		
		for(Path filePath : indexFiles)
		{
			WurmLabel testLabel = new WurmLabel(stripExtension(filePath.getFileName().toString()),"Show entry for page: "+stripExtension(filePath.getFileName().toString()));
			mainPanel.addComponent(testLabel);
			
			
		}
		
		this.layout();
	}
	
	List<Path> getIndexList(Path dataPath)
	{
		List<Path> indexFiles = Journal.jData.getIndexFileList(dataPath);
		return indexFiles;
	}
	
	private void testTreeList(WurmArrayPanel<FlexComponent> indexView)
	{
		
	}
}
