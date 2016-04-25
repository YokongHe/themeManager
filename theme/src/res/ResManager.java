package res;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class ResManager {
	
	private static List<String> themeList;
	
	private static Map<String, AppBean> appMap;
	
	private final static String APP_SRC = "G:\\workspace\\themeProject\\appList.xml";
	
//	private final static String APP_SRC = "src/res/appList.xml";
	
	private final static String THEME_SRC = "G:\\workspace\\themeProject\\themeList.xml";
	
//	private final static String THEME_SRC = "src/res/themeList.xml";
	
	/** 
	 * 获取APP的Map
	 */
	public static Map<String, AppBean> getAppMap(boolean refresh){
		if(appMap == null || refresh){
			try{
				appMap = new HashMap<String, AppBean>();
				SAXReader saxReader = new SAXReader();
				InputStream inputStream = new FileInputStream(new File(APP_SRC));
				Document document = saxReader.read(inputStream);
				Element rootElement = document.getRootElement();
				Iterator<Element> modIt = rootElement.elements("app").iterator();
				while(modIt.hasNext()) {
					Element modEle = modIt.next();
					AppBean tempApp = new AppBean();
					String tempAppName = modEle.attributeValue("name");
					tempApp.setAppName(tempAppName);
					List<String> iconList = new LinkedList<String>();
					Iterator<Element> tempModIt = modEle.elements("icon").iterator();
					while(tempModIt.hasNext()){
						Element tempEle = tempModIt.next();
						String iconName = tempEle.getText();
						iconList.add(iconName);
					}
					tempApp.setIconList(iconList);
					appMap.put(tempAppName, tempApp);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return appMap;
	}
	
	/**
	 * 获取Theme列表
	 */
	public static List<String> getThemeList(boolean refresh){
		if(themeList == null || refresh){
			try {
				themeList = new LinkedList<String>();
				SAXReader saxReader = new SAXReader();
				InputStream inputStream = new FileInputStream(new File(THEME_SRC));
				Document document = saxReader.read(inputStream);
				Element rootElement = document.getRootElement();
				Iterator<Element> modIt = rootElement.elements("theme").iterator();
				while(modIt.hasNext()) {
					Element modEle = modIt.next();
					String themeName = modEle.getText();
					themeList.add(themeName);
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return themeList;
	}
	
	/**
	 * 新建主题
	 * @param themeName
	 */
	public static void insertTheme(String themeName){
		try {
			themeList = new LinkedList<String>();
			File xmlFile = new File(THEME_SRC);
			SAXReader saxReader = new SAXReader();
			InputStream inputStream = new FileInputStream(xmlFile);
			Document document = saxReader.read(inputStream);
			Element rootElement = document.getRootElement();
			Element theme = rootElement.addElement("theme");
			theme.setText(themeName);
			
			FileOutputStream fos = new FileOutputStream(xmlFile);
//			OutputFormat of = new OutputFormat("\t", true);
			OutputFormat of = OutputFormat.createPrettyPrint();
			of.setEncoding("UTF-8");
			of.setIndent(true);
			XMLWriter xw = new XMLWriter(fos, of);
			xw.write(document);
			xw.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<String> getFileNameByApp(String appName){
		if(appMap == null){
			getAppMap(true);
		}
		return appMap.get(appName).getIconList();
	}
	
	
	
	public static void main(String args[]){
//		System.out.println(getAppMap(false).toString());
//		System.out.println(getFileNameByApp("地图").toString());
		insertTheme("中国风");
		System.out.println(getThemeList(true).toString());
	}
	
	
	
}
