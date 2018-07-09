package weixin.service;

import java.util.Formatter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import weixin.model.WeiXinConfig;
import weixin.utilities.HttpHelper;

public class WeiXinSharer {
	private WeiXinConfig config;

	private WeiXinSharer() {

	}

	public WeiXinSharer get() throws Exception {
		WeiXinSharer weiXinSharer = new WeiXinSharer();
		weiXinSharer.config = WeiXinConfig.get();
		return weiXinSharer;
	}

	public String getTiket() throws Exception {
		WeiXinTokenPool weiXinTokenPool = WeiXinTokenPool.get();
		String url = String.format(config.getTicketUrl(), weiXinTokenPool.getAccessToken(config));
		if (url == null) {
			weiXinTokenPool.addAccessToken(config);
		}
		String json = HttpHelper.httpsRequest(url, "GET", null);
		JSONObject jobj = JSON.parseObject(json);
		return jobj.getString("ticket");
	}

	/**
	 * 获取签名
	 * 
	 * @param hash
	 * @return
	 */
	public String byteToHex(final byte[] hash) {
		try (Formatter formatter = new Formatter()) {
			for (byte b : hash) {
				formatter.format("%02x", b);
			}
			String result = formatter.toString();
			formatter.close();
			return result;
		}
	}
}
