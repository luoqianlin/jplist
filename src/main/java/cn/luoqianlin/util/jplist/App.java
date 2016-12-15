package cn.luoqianlin.util.jplist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
	/**
	 * @param args
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException {
		Object object=PlistConvert.toObject(App.class.getResourceAsStream("SceneModeModel.plist"));
		System.out.println(object.toString());
		
	}
	


}
