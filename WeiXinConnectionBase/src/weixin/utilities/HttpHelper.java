package weixin.utilities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import weixin.common.MyX509TrustManager;

public class HttpHelper {
	/** 
	* 发送https请求 
	* @param requestUrl 请求地址 
	* @param requestMethod 请求方式（GET、POST） 
	* @param outputStr 提交的数据 
	* @return 返回微信服务器响应的信息 
	 * @throws Exception 
	*/ 
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) throws Exception { 
		// 创建SSLContext对象，并使用我们指定的信任管理器初始化 
		TrustManager[] tm = { new MyX509TrustManager() }; 
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE"); 
		sslContext.init(null, tm, new java.security.SecureRandom()); 
		// 从上述SSLContext对象中得到SSLSocketFactory对象 
		SSLSocketFactory ssf = sslContext.getSocketFactory(); 
		URL url = new URL(requestUrl); 
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection(); 
		conn.setSSLSocketFactory(ssf); 
		conn.setDoOutput(true); 
		conn.setDoInput(true); 
		conn.setUseCaches(false); 
		// 设置请求方式（GET/POST） 
		conn.setRequestMethod(requestMethod); 
		conn.setRequestProperty("content-type", "application/x-www-form-urlencoded"); 
		// 当outputStr不为null时向输出流写数据 
		if (null != outputStr) { 
			try(OutputStream outputStream = conn.getOutputStream()){
				// 注意编码格式 
				outputStream.write(outputStr.getBytes("UTF-8")); 
			}
		}
		// 从输入流读取返回内容 
		try(InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader); ){
			String str = null; 
			StringBuffer buffer = new StringBuffer(); 
			while ((str = bufferedReader.readLine()) != null) { 
				buffer.append(str); 
			}
			conn.disconnect(); 
			return buffer.toString(); 
		} 
	}

	public static String urlEncodeUTF8(String source) throws Exception{
	    String result = source;
        result = java.net.URLEncoder.encode(source,"utf-8");
	    return result;
	}
}
