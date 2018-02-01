package weixin.bll;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import weixin.model.WeiXinAccessToken;
import weixin.model.WeiXinConfig;
import weixin.model.WeiXinToken;
import weixin.utilities.HttpHelper;

public class WeiXinBase {
	private static Map<String, WeiXinAccessToken> accesstokens=new HashMap<>();
	private static Map<String, WeiXinToken> tokens=new HashMap<>();
	
	public static Map<String, WeiXinAccessToken> getAccesstokens() {
		return accesstokens;
	}
	public static Map<String, WeiXinToken> getTokens() {
		return tokens;
	}
	
	public static WeiXinToken getWeiXinToken(String id) {
		//不存在时
		if(!tokens.containsKey(id)) {
			return null;
		}
		
		WeiXinToken token=tokens.get(id);
		//过期时
		if(token.getBeing_date().getTime()+token.getExpires_in()
				<new Date().getTime()) {
			tokens.remove(id);
			return null;
		}
		
		return token;
	}
	
	public static void addWeiXinToken(String id,WeiXinToken token) {
		if(tokens.containsKey(id))
			tokens.remove(id);
		token.setBeing_date(new Date());
		tokens.put(id, token);
	}
	
	/**
	 * 获取accessToken
	 * @param appid
	 * @return
	 * @throws Exception
	 */
	public static String getAccessToken(WeiXinConfig config) throws Exception{
		//不存在时添加
		if(!accesstokens.containsKey(config.getAppID())) {
			WeiXinAccessToken accessToken = getNewAccessToken(config);
			accesstokens.put(config.getAppID(), accessToken);
			return accessToken.getAccess_token();
		}
		
		WeiXinAccessToken accessToken=accesstokens.get(config.getAppID());
		//过期时添加
		if(accessToken.getBeing_date().getTime()+accessToken.getExpires_in()
				<new Date().getTime()) {
			accesstokens.remove(config.getAppID());
			WeiXinAccessToken temp = getNewAccessToken(config);
			accesstokens.put(config.getAppID(), temp);
			return temp.getAccess_token();
		}
		
		return accessToken.getAccess_token();
	}
	
	private static WeiXinAccessToken getNewAccessToken(WeiXinConfig config) throws Exception {
		String url=String.format(config.getAccessTokenUrl(), config.getAppID(),config.getAppKEY());
		String json = HttpHelper.httpsRequest(url, "GET", null);
		WeiXinAccessToken accessToken = JSON.parseObject(json,WeiXinAccessToken.class);
		accessToken.setBeing_date(new Date());
		return accessToken;
	}
}
