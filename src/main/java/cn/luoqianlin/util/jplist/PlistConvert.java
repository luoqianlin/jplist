package cn.luoqianlin.util.jplist;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/** 
* @author lql E-mail: 595308107@qq.com
* @version 0 创建时间：2016年12月15日 下午3:24:49 
* 类说明 
*/
public class PlistConvert {
	
	public static Object toObject(InputStream plist) throws DocumentException{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(plist);
		Element plistElement = doc.getRootElement();
		return parseElement(plistElement);
	}
	
	private static List<Object> parseArray(Element e){
		ArrayList<Object> list=new ArrayList<>();
		Iterator<Element> iter = e.elementIterator();
		while(iter.hasNext()){
			list.add(parseElement(iter.next()));
		}
		return list;
	}
	
	private static Object parseElement(Element e) {
		String name = e.getName();
		if (name.equals("dict")) {
			return parseDict(e);
		} else if (name.equals("array") || name.equals("plist")) {
			return parseArray(e);
		} else if (name.equals("string")) {
			return parseString(e);
		} else if (name.equals("integer") || name.equals("real")) {
			return parseInteger(e);
		} else if (name.equals("key")) {
			return parseKey(e);
		} else {
			throw new IllegalArgumentException(String.format("Unkown node is %s", name));
		}
	}

	private static Map<Object,Object>parseDict(Element e){
		Map<Object,Object>dict=new HashMap<>();
		Iterator<Element> kve = e.elementIterator();
		while(kve.hasNext()){
			Element ke = kve.next();
			Element ve=kve.next();
//			System.out.println(ke.getName()+" -> "+ve.getName());
			dict.put(parseElement(ke), parseElement(ve));
		}
		return dict;
	}
	
	private static Long parseInteger(Element e){
		return Long.parseLong(e.getStringValue());
	}
	
	private static String parseKey(Element e){
		return e.getStringValue();
	}
	
	private static String parseString(Element e){
		return e.getStringValue();
	}
}
