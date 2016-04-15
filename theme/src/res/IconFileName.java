package res;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IconFileName {

	public static final Map<String, String> FILE_NAME_MAP = new HashMap<String, String>();
	
	public static final List<String> THEME_LIST = new LinkedList<String>();
	static {
		THEME_LIST.add("default");
		THEME_LIST.add("matel");
		
		FILE_NAME_MAP.put("didi", "com_didi");
		FILE_NAME_MAP.put("uber", "com_uber");
	}
	
}
