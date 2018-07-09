package weixin.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;

import weixin.model.WeiXinAccessToken;
import weixin.model.WeiXinConfig;
import weixin.model.WeiXinToken;
import weixin.utilities.HttpHelper;

public class WeiXinTokenPool {

	private static WeiXinTokenPool weiXinTokenPool = null;
	private static Map<String, WeiXinAccessToken> accessTokenMap = new HashMap<>();
	private static Map<String, WeiXinToken> tokenMap = new HashMap<>();

	public static synchronized WeiXinTokenPool get() {
		if (weiXinTokenPool == null) {
			weiXinTokenPool = new WeiXinTokenPool();
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					System.out.println(new Date() + "开始清理微信Token");
					synchronized (tokenMap) {
						Set<Entry<String, WeiXinToken>> tokenEntries = tokenMap.entrySet();
						for (Entry<String, WeiXinToken> entry : tokenEntries) {
							WeiXinToken weiXinToken = entry.getValue();
							if (weiXinToken.getBeing_date().getTime() + weiXinToken.getExpires_in() < new Date()
									.getTime()) {
								tokenMap.remove(weiXinToken);
							}
						}
					}
					synchronized (accessTokenMap) {
						Set<Entry<String, WeiXinAccessToken>> accessTokenEntries = accessTokenMap.entrySet();
						for (Entry<String, WeiXinAccessToken> entry : accessTokenEntries) {
							WeiXinAccessToken weiXinAccessToken = entry.getValue();
							if (weiXinAccessToken.getBeing_date().getTime()
									+ weiXinAccessToken.getExpires_in() < new Date().getTime()) {
								accessTokenMap.remove(weiXinAccessToken);
							}
						}
					}
					System.out.println(new Date() + "微信Token结束");
				}
			}, 0, 60 * 1000 * 60 * 2);
		}
		return weiXinTokenPool;
	}

	public WeiXinToken getWeiXinToken(String id) {
		WeiXinToken weiXinToken = null;
		synchronized (tokenMap) {
			// 不存在时
			if (!tokenMap.containsKey(id)) {
				return weiXinToken;
			}
			weiXinToken = tokenMap.get(id);
		}
		if (weiXinToken.getBeing_date().getTime() + weiXinToken.getExpires_in() < new Date().getTime()) {
			return null;
		}
		return weiXinToken;
	}

	public void addWeiXinToken(String id, WeiXinToken token) {
		synchronized (tokenMap) {
			token.setBeing_date(new Date());
			tokenMap.put(id, token);
		}
	}

	/**
	 * 获取accessToken
	 * 
	 * @param appid
	 * @return
	 * @throws Exception
	 */
	public String getAccessToken(WeiXinConfig config) throws Exception {
		WeiXinAccessToken accessToken = null;
		synchronized (accessTokenMap) {
			// 不存在时
			if (!accessTokenMap.containsKey(config.getAppID())) {
				return null;
			}
			accessToken = accessTokenMap.get(config.getAppID());
		}
		// 过期
		if (accessToken.getBeing_date().getTime() + accessToken.getExpires_in() < new Date().getTime()) {
			return null;
		}
		return accessToken.getAccess_token();
	}

	public void addAccessToken(WeiXinConfig config) throws Exception {
		WeiXinAccessToken weiXinAccessToken = getNewAccessToken(config);
		synchronized (accessTokenMap) {
			accessTokenMap.put(config.getAppID(), weiXinAccessToken);
		}
	}

	private WeiXinAccessToken getNewAccessToken(WeiXinConfig config) throws Exception {
		String url = String.format(config.getAccessTokenUrl(), config.getAppID(), config.getAppKEY());
		String json = HttpHelper.httpsRequest(url, "GET", null);
		WeiXinAccessToken accessToken = JSON.parseObject(json, WeiXinAccessToken.class);
		accessToken.setBeing_date(new Date());
		return accessToken;
	}
}
