/**
 * 
 */
package com.wurmonline.client.renderer.gui;

import java.util.logging.Level;

import com.wurmonline.client.GameCrashedException;

/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 * Index Page View for the Journal mod
 */

import tk.teknorapture.wurmunlimited.clientmods.journal.Journal;
import tk.teknorapture.wurmunlimited.clientmods.journal.JournalMod;

public class JournalIndexPageView extends FlexComponent {

	Journal journal;
	WurmArrayPanel mainPagePanel;//main panel of the page
	static String pagename = "JournalIndexPage";
	
	
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
		      } */
			return "not used";
		}
		
	}
	
	
	
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
		super(pagename);//name may have to be the same as parent???

		//this.journal = journal;//not needed
		
		setInitialSize(width,height,false);
		
		if(!Journal.RESIZABLE)
		{
			sizeFlags= FlexComponent.FIXED_WIDTH | FlexComponent.FIXED_HEIGHT;// I wish there was a "fill available space" hopefully default is that...
		}
		
		
		//Initialize the file structure
		/*
		if(this.journal.getFirstRun())//not needed
		{
			//this.journal.InitFileStructure();
		}
		*/
		
		//set up main journal page panel, TODO: Put in separate class?  Probably not, each "page's" layout is pretty different.
		WurmArrayPanel<FlexComponent> mainPagePanel = new WurmArrayPanel(pagename, WurmArrayPanel.DIR_VERTICAL);
		
		
	}

	private void testTreeList(WurmArrayPanel<FlexComponent> indexView)
	{
		
	}
}
