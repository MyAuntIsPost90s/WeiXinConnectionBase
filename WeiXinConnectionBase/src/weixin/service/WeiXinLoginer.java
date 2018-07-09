package weixin.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

import weixin.model.WeiXinConfig;
import weixin.model.WeiXinToken;
import weixin.model.WeiXinUser;

@SuppressWarnings("deprecation")
public class WeiXinLoginer {

	private WeiXinConfig config = null;
	private WeiXinTokenPool weiXinTokenPool = null;

	private WeiXinLoginer() {
	}

	public WeiXinLoginer get() {
		WeiXinLoginer weiXinLoginer = new WeiXinLoginer();
		weiXinLoginer.config = WeiXinConfig.get();
		weiXinLoginer.weiXinTokenPool = WeiXinTokenPool.get();
		return weiXinLoginer;
	}

	public String getCodeUrl() {
		return String.format(config.getGetCodeUrl(), config.getAppID(), config.getLoginRedirectUrl(), "snsapi_userinfo",
				Math.random());
	}

	/**
	 * 获取微信token
	 * 
	 * @param code
	 * @return
	 */
	public WeiXinToken getToken(String code) {
		try {
			WeiXinToken token = weiXinTokenPool.getWeiXinToken(code);
			if (token == null) {
				String url = String.format(config.getGetTokenUrl(), config.getAppID(), config.getAppKEY(), code);
				@SuppressWarnings({ "resource" })
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(url);
				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				String json = EntityUtils.toString(httpEntity, "utf-8");
				token = JSON.parseObject(json, WeiXinToken.class);
				weiXinTokenPool.addWeiXinToken(code, token);
			}
			return token;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取用户信息
	 * 
	 * @param token
	 * @param openid
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public WeiXinUser getUser(String token, String openid) throws Exception {
		String url = String.format(config.getGetUserUrl(), token, openid);
		@SuppressWarnings({ "resource" })
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();
		String json = EntityUtils.toString(httpEntity, "utf-8");
		return JSON.parseObject(json, WeiXinUser.class);
	}
}
