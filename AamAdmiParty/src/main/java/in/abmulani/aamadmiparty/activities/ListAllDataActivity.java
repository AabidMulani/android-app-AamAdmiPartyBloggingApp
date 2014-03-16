package in.abmulani.aamadmiparty.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import in.abmulani.aamadmiparty.BaseApplication;
import in.abmulani.aamadmiparty.R;

public class ListAllDataActivity extends Activity {

	private ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.temp);
		image = (ImageView) findViewById(R.id.imageView1);
		image.setLayoutParams(new LayoutParams(BaseApplication.screenWidth, (int) (BaseApplication.screenWidth * 0.60)));

		DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher).showImageForEmptyUri(R.drawable.splash_screen)
				.showImageOnFail(R.drawable.splash_screen_cover).resetViewBeforeLoading(true).delayBeforeLoading(300).cacheInMemory(true).cacheOnDisc(true).build();

		ImageLoader.getInstance().displayImage("http://www.dibaa.esy.es/images/ak10.jpg", image, options);

	}
}
