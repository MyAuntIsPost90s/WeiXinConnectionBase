package weixin.utilities;

public class WebMapHelper {
	/**
	 * 获取当前Web根目录物理路径
	 * @return
	 */
	public static String getWebRoot(){
		String url = WebMapHelper.class.getResource("").getPath();  
        String path = url.substring(0, url.indexOf("WEB-INF"));
        return path.split("file:")[1];
	}
}
