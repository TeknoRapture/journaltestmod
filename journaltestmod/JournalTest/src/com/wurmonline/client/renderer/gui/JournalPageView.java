/**
 * 
 */
package com.wurmonline.client.renderer.gui;

import tk.teknorapture.wurmunlimited.clientmods.journal.Journal;

/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 */
public class JournalPageView extends WurmBorderPanel{

	/**
	 * @param viewHeight 
	 * @param viewWidth 
	 * 
	 */
	public JournalPageView(int viewWidth, int viewHeight) {
		super("JournalPageView");
		
		// New page
		WurmArrayPanel<WurmLabel> mainView = new WurmArrayPanel<WurmLabel>("Journal Labels", WurmArrayPanel.DIR_VERTICAL);
		WurmLabel testLabel = new WurmLabel("Journal Add Page View","Test Journal Add Page View" );
		
		setInitialSize(viewWidth,viewHeight,false);
		//*
		if(!Journal.RESIZABLE)
		{
			sizeFlags= FlexComponent.FIXED_WIDTH | FlexComponent.FIXED_HEIGHT;// I wish there was a "fill available space" hopefully default is that...
		}
		//*/
		
		mainView.addComponent(testLabel);
		setComponent(mainView,WurmBorderPanel.CENTER);
		
		layout();
	}

	public JournalPageView(int viewWidth, int viewHeight, String pageID)
	{
		this(viewWidth, viewHeight, pageID, true);
	}
	
	public JournalPageView(int viewWidth, int viewHeight, String pageID, boolean editable)//"editable" for future use
	{
		super("JournalPageView");
		
		WurmArrayPanel<WurmLabel> mainView = new WurmArrayPanel<WurmLabel>("Journal Labels", WurmArrayPanel.DIR_VERTICAL);
		WurmLabel testLabel = new WurmLabel("Journal View Page: " + pageID,"Test Journal View Page: " + pageID);
		
		setInitialSize(viewWidth,viewHeight,false);
		//*
		if(!Journal.RESIZABLE)
		{
			sizeFlags= FlexComponent.FIXED_WIDTH | FlexComponent.FIXED_HEIGHT;// I wish there was a "fill available space" hopefully default is that...
		}
		//*/
		
		mainView.addComponent(testLabel);
		setComponent(mainView,WurmBorderPanel.CENTER);
		
		layout();
	}
}
