/**
 * 
 */
package tk.teknorapture.wurmonline.clientmods.journaltest;

/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.gotti.wurmunlimited.modloader.ReflectionUtil;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import org.gotti.wurmunlimited.modloader.classhooks.InvocationHandlerFactory;
import org.gotti.wurmunlimited.modloader.interfaces.Configurable;
import org.gotti.wurmunlimited.modloader.interfaces.Initable;
import org.gotti.wurmunlimited.modloader.interfaces.PreInitable;
import org.gotti.wurmunlimited.modloader.interfaces.WurmMod;

import com.wurmonline.client.renderer.gui.HeadsUpDisplay;
import com.wurmonline.client.renderer.gui.MainMenu;
import com.wurmonline.client.renderer.gui.WurmComponent;
import com.wurmonline.client.renderer.gui.JournalWindow;
import com.wurmonline.client.settings.SavePosManager; 

import com.wurmonline.client.renderer.gui.JunkJournal;

public class JournalTestMod implements WurmMod, Initable, PreInitable, Configurable {

	private static Logger logger = Logger.getLogger(JournalTestMod.class.getName());
	private Object journal;

	@Override
	public void configure(Properties properties) {
		// TODO Auto-generated method stub
		int x = 0;
	}
	
	@Override
	public void preInit() {
	}
	
	@Override
	public void init() {
		
		// VOID com.wurmonline.client.renderer.gui.HeadsUpDisplay.init(int, int) (thanks alot ago...putting that void in front would have really helped me figure out the "(II)V" thing lol)
		HookManager.getInstance().registerHook("com.wurmonline.client.renderer.gui.HeadsUpDisplay", "init", "(II)V",
				new InvocationHandlerFactory() {
				@Override
				public InvocationHandler createInvocationHandler() {
					return new InvocationHandler() {
	
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							method.invoke(proxy, args);
	
							initJournalTest((HeadsUpDisplay) proxy);
	
							return null;
						}
					};
				}
			});
	}
//		HookManager.getInstance().registerHook("com.wurmonline.client.console.WurmConsole", "handleInput2", "(Ljava/lang/String;Z)V",
//		new InvocationHandlerFactory() {		
//
//		handle the esc menu click, and set up the GUI and Journal
		
	private void initJournalTest(HeadsUpDisplay hud) {
		new Runnable() {		
			@Override
			public void run() {
				try {
					
					//World world = ReflectionUtil.getPrivateField(hud, ReflectionUtil.getField(hud.getClass(), "world"));
		
					//LiveMapWindow liveMapWindow = new LiveMapWindow(world);
					//liveMap = liveMapWindow;///local to maintain it when its hidden?
					
					logger.log(Level.INFO, "initJournalTest started."); //Level.info probably isn't displayed
					
					//JunkJournal junkJournal = new JunkJournal();
					
					JournalWindow journalWindow = new JournalWindow();
					
					journal = journalWindow;//why not just cast to object?
					
					MainMenu mainMenu = ReflectionUtil.getPrivateField(hud, ReflectionUtil.getField(hud.getClass(), "mainMenu"));
					mainMenu.registerComponent("Journal", journalWindow);
		
					List<WurmComponent> components = ReflectionUtil.getPrivateField(hud, ReflectionUtil.getField(hud.getClass(), "components"));
					components.add(journalWindow);
					
					SavePosManager savePosManager = ReflectionUtil.getPrivateField(hud, ReflectionUtil.getField(hud.getClass(), "savePosManager"));
					savePosManager.registerAndRefresh(journalWindow, "journalwindow");
				}
				catch (IllegalArgumentException | IllegalAccessException | ClassCastException | NoSuchFieldException e) {
					throw new RuntimeException(e);
				}
			}	
		}.run();		
	}


}
















