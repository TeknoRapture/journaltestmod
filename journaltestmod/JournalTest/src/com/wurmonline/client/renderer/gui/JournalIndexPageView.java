/**
 * 
 */
package com.wurmonline.client.renderer.gui;
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

		WurmArrayPanel<FlexComponent> mainPanel = new WurmArrayPanel<FlexComponent>("Index",WurmArrayPanel.DIR_VERTICAL);
		
		WurmLabel testLabel = new WurmLabel("Index Page Not Implemented","Test Index Page");
		
		mainPanel.addComponent(testLabel);
		
		this.setComponent(mainPanel, WurmBorderPanel.CENTER);
		
		this.layout();
	}

	private void testTreeList(WurmArrayPanel<FlexComponent> indexView)
	{
		
	}
}
