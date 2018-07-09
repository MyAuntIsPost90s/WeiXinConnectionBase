package weixin.model;

import java.util.Date;

public class WeiXinToken {
	private String access_token;
	private int expires_in;
	private String refresh_token;
	private String openid;
	private String scope;
	private String unionid;
	private Date being_date;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_tokenk) {
		this.access_token = access_tokenk;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public Date getBeing_date() {
		return being_date;
	}
	public void setBeing_date(Date being_date) {
		this.being_date = being_date;
	}
}
