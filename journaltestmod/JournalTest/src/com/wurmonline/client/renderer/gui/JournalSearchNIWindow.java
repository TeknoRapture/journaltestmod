package com.wurmonline.client.renderer.gui;


//Lazy Copy Pasta: Doesnt work just playing around
public class JournalSearchNIWindow extends WWindow
implements ButtonListener{

	private final WButton yesButton;
  
	protected void closePressed()
	{
		hud.removeComponent(this);
	}
  
	public JournalSearchNIWindow()
	{
	    super("Search Not Implemented.");
	    setTitle("Search Not Implemented.");
	    
	    this.resizable = false;
	    
	    String quitQuestion = "Search is not yet implemented.";
	    WurmTextPanel textPanel = new WurmTextPanel(true);
	    textPanel.addLine(quitQuestion);
	    textPanel.newLineInset = "";
	    
	    this.yesButton = new WButton("NI!", this);
	    WurmPanel spacer = new WurmPanel(16, this.yesButton.height + 8, false);
	    
	    WurmArrayPanel<FlexComponent> array = new WurmArrayPanel<FlexComponent>(1, this.yesButton.width, spacer.height);
	    
	    array.addComponent(this.yesButton);
	    
	    WurmDecorator buttonPanel = new WurmDecorator(array);
	    buttonPanel.align = 3;
	    buttonPanel.pack();
	    
	    WurmBorderPanel borderPanel = new WurmBorderPanel("Search NI main panel");
	    borderPanel.setComponent(textPanel, 4);
	    borderPanel.setComponent(buttonPanel, 2);
	    
	    setComponent(borderPanel);
	    
	    setSize(this.text.getWidth("Search is not yet implemented."), 3 * this.yesButton.height + 32);
	}
  
	public void buttonPressed(WButton button) {}
	  
	public void buttonClicked(WButton button)
	{
	  if (button == this.yesButton) {
	    hud.removeComponent(this);
	  }
	}
}
