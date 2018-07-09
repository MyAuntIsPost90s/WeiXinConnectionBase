package weixin.utilities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class XMLHelper {
	
	public static Map<String, String> doXMLParse(String strxml) throws Exception { 
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
	    if(null == strxml || "".equals(strxml)) {
	        return null;
	    }

	    Map<String, String> m = new HashMap<>();
	    try(InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"))){
		    SAXBuilder builder = new SAXBuilder();
		    Document doc = builder.build(in);
		    Element root = doc.getRootElement();
		    List<Element> list = root.getChildren();
		    Iterator<Element> it = list.iterator();
		    while(it.hasNext()) {
		        Element e = (Element) it.next();
		        String k = e.getName();
		        String v = "";
		        List<Element> children = e.getChildren();
		        if(children.isEmpty()) {
		            v = e.getTextNormalize();
		        } else {
		            v = XMLHelper.getChildrenText(children);
		        }
		        m.put(k, v);
		    }
		    return m;
	    }
	}

	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List<Element> children) {
	    StringBuffer sb = new StringBuffer();
	    if(!children.isEmpty()) {
	        Iterator<?> it = children.iterator();
	        while(it.hasNext()) {
	            Element e = (Element) it.next();
	            String name = e.getName();
	            String value = e.getTextNormalize();
	            List<Element> list = e.getChildren();
	            sb.append("<" + name + ">");
	            if(!list.isEmpty()) {
	                sb.append(XMLHelper.getChildrenText(list));
	            }
	            sb.append(value);
	            sb.append("</" + name + ">");
	        }
	    }
	    return sb.toString();
	}
}
