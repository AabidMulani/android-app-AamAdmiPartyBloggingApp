package in.abmulani.aamadmiparty.utils;

import android.util.Log;


public class Logger {
	
	private static final boolean DoLogging=true;
	
	public static void e(String Tag,String msg){
		if(DoLogging)
			Log.e(Tag, msg);
	}
	
	public static void i(String Tag,String msg){
		if(DoLogging)
			Log.i(Tag, msg);
	}
	
	public static void d(String Tag,String msg){
		if(DoLogging)
			Log.d(Tag, msg);
	}
	
	public static void v(String Tag,String msg){
		if(DoLogging)
			Log.v(Tag, msg);
	}

}
