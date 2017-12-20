package Snake;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public abstract class KeyController
{	
	/**
	 * Côté lié à la touche
	 *
	 */
	public enum Side { LEFT, RIGHT };
	
	private static Map<Integer, Boolean> keysState = new HashMap<Integer, Boolean>();
	
	public static boolean isPressed(Integer key)
	{
		if (key == null) { return false; }
		return keysState.get(key) != null && keysState.get(key);
	}
	
	public static void changeState(Integer key, boolean state)
	{
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
	
	public static void setControl(int headId, Side side, Integer value)
	{
		setControl("head" + headId + ((side == Side.LEFT) ? "Left" : "Right"), value);
	}
	
	public static boolean initControls()
	{
		if (controls == null)
		{
			controls = new HashMap<String, Integer>();
			
			controls.put("head0Left", KeyEvent.VK_Q);
			controls.put("head0Right", KeyEvent.VK_D);
			
			controls.put("head1Left", KeyEvent.VK_K);
			controls.put("head1Right", KeyEvent.VK_M);
			
			controls.put("head2Left", KeyEvent.VK_NUMPAD4);
			controls.put("head2Right", KeyEvent.VK_NUMPAD6);
			
			controls.put("head3Left", KeyEvent.VK_C);
			controls.put("head3Right", KeyEvent.VK_B);
			
			controls.put("head4Left", KeyEvent.VK_F);
			controls.put("head4Right", KeyEvent.VK_H);
			
			controls.put("head5Left", KeyEvent.VK_A);
			controls.put("head5Right", KeyEvent.VK_E);
			
			controls.put("head6Left", KeyEvent.VK_R);
			controls.put("head6Right", KeyEvent.VK_Y);
			
			controls.put("head7Left", KeyEvent.VK_I);
			controls.put("head7Right", KeyEvent.VK_P);
			
			return true;
		}
		
		for (int key : keysState.keySet())
		{
			keysState.put(key, false);
		}
		return false;
	}
}
