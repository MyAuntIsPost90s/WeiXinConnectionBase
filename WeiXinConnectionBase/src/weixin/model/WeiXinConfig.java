package weixin.model;

import weixin.utilities.SpringUtil;

public class WeiXinConfig {
	private String appID = "";
	private String appKEY = "";
	private String mchID = "";
	private String mchKEY = "";
	private String getCodeUrl = "";
	private String getTokenUrl = "";
	private String getUserUrl = "";
	private String loginRedirectUrl = "";
	private String payRedirectUrl = "";
	private String wxPayUrl = "";
	private String accessTokenUrl = "";
	private String ticketUrl = "";

	public static WeiXinConfig get() {
		return SpringUtil.getBean(WeiXinConfig.class);
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getAppKEY() {
		return appKEY;
	}

	public void setAppKEY(String appKEY) {
		this.appKEY = appKEY;
	}

	public String getMchID() {
		return mchID;
	}

	public void setMchID(String mchID) {
		this.mchID = mchID;
	}

	public String getGetCodeUrl() {
		return getCodeUrl;
	}

	public void setGetCodeUrl(String getCodeUrl) {
		this.getCodeUrl = getCodeUrl;
	}

	public String getGetTokenUrl() {
		return getTokenUrl;
	}

	public void setGetTokenUrl(String getTokenUrl) {
		this.getTokenUrl = getTokenUrl;
	}

	public String getGetUserUrl() {
		return getUserUrl;
	}

	public void setGetUserUrl(String getUserUrl) {
		this.getUserUrl = getUserUrl;
	}

	public String getLoginRedirectUrl() {
		return loginRedirectUrl;
	}

	public void setLoginRedirectUrl(String loginRedirectUrl) {
		this.loginRedirectUrl = loginRedirectUrl;
	}

	public String getPayRedirectUrl() {
		return payRedirectUrl;
	}

	public void setPayRedirectUrl(String payRedirectUrl) {
		this.payRedirectUrl = payRedirectUrl;
	}

	public String getWxPayUrl() {
		return wxPayUrl;
	}

	public void setWxPayUrl(String wxPayUrl) {
		this.wxPayUrl = wxPayUrl;
	}

	public String getAccessTokenUrl() {
		return accessTokenUrl;
	}

	public void setAccessTokenUrl(String accessTokenUrl) {
		this.accessTokenUrl = accessTokenUrl;
	}

	public String getTicketUrl() {
		return ticketUrl;
	}

	public void setTicketUrl(String ticketUrl) {
		this.ticketUrl = ticketUrl;
	}

	public String getMchKEY() {
		return mchKEY;
	}

	public void setMchKEY(String mchKEY) {
		this.mchKEY = mchKEY;
	}
}
