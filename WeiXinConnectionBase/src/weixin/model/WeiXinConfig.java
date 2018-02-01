package weixin.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import weixin.utilities.WebMapHelper;

public class WeiXinConfig {
	private String appID="";
	private String appKEY="";
	private String mchID="";
	private String mchKEY="";
	private String getCodeUrl="";
	private String getTokenUrl="";
	private String getUserUrl="";
	private String loginRedirectUrl="";
	private String payRedirectUrl="";
	private String wxPayUrl="";
	private String accessTokenUrl="";
	private String ticketUrl="";
	
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
	
	public WeiXinConfig() throws Exception {
		String path = WebMapHelper.getWebRoot() + "WEB-INF/classes/weixinconnectionconfig.properties";
		Properties properties =new Properties();		
		try(InputStream in = new BufferedInputStream(new FileInputStream(path))){
			properties.load(in);
	
			Iterator<String> it=properties.stringPropertyNames().iterator();
			while(it.hasNext()){
				String key = it.next();
				if(key.equals("appID"))
					this.appID=properties.getProperty(key);
				if(key.equals("appKEY"))
					this.appKEY=properties.getProperty(key);
				if(key.equals("mchID"))
					this.mchID=properties.getProperty(key);
				if(key.equals("mchKEY"))
					this.mchKEY=properties.getProperty(key);
				if(key.equals("getCodeUrl"))
					this.getCodeUrl=properties.getProperty(key);
				if(key.equals("getTokenUrl"))
					this.getTokenUrl=properties.getProperty(key);
				if(key.equals("getUserUrl"))
					this.getUserUrl=properties.getProperty(key);
				if(key.equals("loginRedirectUrl"))
					this.loginRedirectUrl=properties.getProperty(key);
				if(key.equals("payRedirectUrl"))
					this.payRedirectUrl=properties.getProperty(key);
				if(key.equals("wxPayUrl"))
					this.wxPayUrl=properties.getProperty(key);
				if(key.equals("accessTokenUrl"))
					this.accessTokenUrl=properties.getProperty(key);
				if(key.equals("ticketUrl"))
					this.ticketUrl=properties.getProperty(key);
			}
		}
	}	
}
