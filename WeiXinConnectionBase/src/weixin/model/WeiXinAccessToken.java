package weixin.model;

import java.util.Date;

public class WeiXinAccessToken {
	private String access_token;
	private long expires_in;
	private Date being_date;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public long getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}
	public Date getBeing_date() {
		return being_date;
	}
	public void setBeing_date(Date being_date) {
		this.being_date = being_date;
	}
}
