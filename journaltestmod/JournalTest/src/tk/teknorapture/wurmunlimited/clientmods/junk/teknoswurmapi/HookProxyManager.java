/**
 * 
 */
package tk.teknorapture.wurmunlimited.clientmods.junk.teknoswurmapi;
/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 * 
 * Manages the hooks I use in my mods to make updates easier
 * Going to work on this later Java scope is a little different than what I'm used to
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


public class HookProxyManager implements WurmMod, Initable, PreInitable, Configurable {
	
	
	@Override
	public void configure(Properties properties) {
		
	}
	
	@Override
	public void preInit() {
	}
	
	@Override
	public void init() {
		
	}
	
	private static Object HUDProxy;
	public void initHudProxy()
	{
		HookManager.getInstance().registerHook("com.wurmonline.client.renderer.gui.HeadsUpDisplay", "init", "(II)V",
				new InvocationHandlerFactory() {
				@Override
				public InvocationHandler createInvocationHandler() {
					return new InvocationHandler() {

						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							method.invoke(proxy, args);

							//initJournalTest((HeadsUpDisplay) proxy);
							SetWurmHUDProxy(proxy);
							
							return null;
						}
					};
				}
			});
	}
	
	
	
	public void SetWurmHUDProxy(Object proxy)
	{
		HUDProxy = proxy;
		
	}
	
	public Object GetWurmHUDProxy(){
		return HUDProxy;
	}
}























