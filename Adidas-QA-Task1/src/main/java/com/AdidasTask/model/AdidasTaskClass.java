package com.AdidasTask.model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdidasTaskClass {
	
	private String DesktopImageURL;
	private String TabletImageURL;
	private String MobileImageURL;
	
	
	public String getDesktopImageURL() {
		return DesktopImageURL;
	}
	public void setDesktopImageURL(String desktopImageURL) {
		this.DesktopImageURL = desktopImageURL;
	}
	public String getTabletImageURL() {
		return TabletImageURL;
	}
	public void setTabletImageURL(String tabletImageURL) {
		this.TabletImageURL = tabletImageURL;
	}
	public String getMobileImageURL() {
		return MobileImageURL;
	}
	public void setMobileImageURL(String mobileImageURL) {
		this.MobileImageURL = mobileImageURL;
	}
	
	
	public int HttpUrlConnect(String Url) throws IOException {

		URL url = new URL(Url);
		HttpURLConnection httpURLConnect = (HttpURLConnection)url.openConnection();
		httpURLConnect.setConnectTimeout(3000);
		httpURLConnect.connect();
		int response  = httpURLConnect.getResponseCode();
	return response;	
	//(httpURLConnect.getResponseCode()== HttpURLConnection.HTTP_NOT_FOUND)
}
	

}
