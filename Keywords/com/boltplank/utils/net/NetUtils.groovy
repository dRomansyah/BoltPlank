package com.boltplank.utils.net
import com.kms.katalon.core.annotation.Keyword


class NetUtils {

	@Keyword
	def checkURL(String url, int maxretry){
		def urlOk=false;
		int retrycount=0;
		HttpURLConnection huc = ((new URL(url).openConnection()) as HttpURLConnection)
		huc.setConnectTimeout(10000)
		huc.setRequestMethod('HEAD')
		int respCode;
		while(urlOk==false && retrycount<=maxretry){
			try{

				huc.connect()
				respCode = huc.getResponseCode()
				if (respCode >= 400) {
					retrycount+=1;
					println ("respCode: "+respCode)
				}else{
					urlOk=true
					break
				}
			}catch(Exception g){
				//				g.printStackTrace()
				println ("DUAR")
				println (g.getMessage())
				retrycount+=1;
				urlOk=false;
			}
		}
		if(!urlOk){
			throw new Exception("Max retry reached");
		}
	}
}