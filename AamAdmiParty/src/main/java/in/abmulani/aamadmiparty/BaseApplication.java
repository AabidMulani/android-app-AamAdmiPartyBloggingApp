package in.abmulani.aamadmiparty;

import android.graphics.Bitmap.CompressFormat;

import com.bugsense.trace.BugSenseHandler;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orm.SugarApp;

public class BaseApplication extends SugarApp {

	public static int screenWidth;
	public static int screenHeight;
	
	@Override
	public void onCreate() {
		super.onCreate();
		BugSenseHandler.initAndStartSession(getApplicationContext(), "e4e955ca");
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).discCacheExtraOptions(500, 400, CompressFormat.JPEG, 75, null)
				.denyCacheImageMultipleSizesInMemory().memoryCache(new LruMemoryCache(4 * 1024 * 1024)).memoryCacheSize(4 * 1024 * 1024).memoryCacheSizePercentage(25) // default
				.discCacheSize(50 * 1024 * 1024).discCacheFileCount(100).writeDebugLogs().build();
		ImageLoader.getInstance().init(config);
	}
	
}
