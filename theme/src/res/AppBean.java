package res;

import java.util.List;

public class AppBean {

	private String appName;
	
	private List<String> iconList;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public List<String> getIconList() {
		return iconList;
	}

	public void setIconList(List<String> iconList) {
		this.iconList = iconList;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "appName: " + appName + "\n iconList: " + iconList.toString() + "\n";
	}
	
}
