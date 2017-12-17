package Snake;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public abstract class KeyController
{	
	private static Map<Integer, Boolean> keysState = new HashMap<Integer, Boolean>();
	
	public static boolean isPressed(Integer key)
	{
		if (key == null) { return false; }
		return keysState.get(key) != null && keysState.get(key);
	}
	
	public static void changeState(Integer key, boolean state)
	{
//		System.out.println(key + " " + state);
		keysState.put(key, state);
	}
	
	
	
	
	
	private static Map<String, Integer> controls;
	
	public static Integer getControl(String key)
	{
		if (key == null) { return null; }
		return controls.get(key);
	}
	
	public static void setControl(String key, Integer value)
	{
		controls.put(key, value);
	}
	
	public static boolean initControls()
	{
		if (controls == null)
		{
			controls = new HashMap<String, Integer>();
			
			controls.put("head0Left", KeyEvent.VK_Q);
			controls.put("head0Right", KeyEvent.VK_D);
			
			controls.put("head1Left", KeyEvent.VK_L);
			controls.put("head1Right", KeyEvent.VK_M);
			
//			System.out.println(KeyEvent.VK_Q + " " + KeyEvent.VK_D);
			
			return true;
		}
		return false;
	}
}
