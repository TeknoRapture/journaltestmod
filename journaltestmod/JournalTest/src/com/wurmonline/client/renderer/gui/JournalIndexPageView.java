/**
 * 
 */
package com.wurmonline.client.renderer.gui;

import java.util.logging.Level;

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


}
