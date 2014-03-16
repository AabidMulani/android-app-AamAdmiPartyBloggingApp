package in.abmulani.aamadmiparty.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import in.abmulani.aamadmiparty.BaseApplication;
import in.abmulani.aamadmiparty.R;

public class SplashScreen extends Activity{

	private RelativeLayout shadow;
	private Animation fadeIn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		shadow=(RelativeLayout) findViewById(R.id.ass_shadow_layout);
		shadow.setVisibility(View.INVISIBLE);
		fadeIn = new AlphaAnimation(0, 1);
        Log.e("Logging","Start");
		fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
		fadeIn.setStartOffset(1000);
		fadeIn.setDuration(2000);
		fadeIn.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				shadow.setVisibility(View.VISIBLE);
				BaseApplication.screenWidth=getWindowWidth();
				BaseApplication.screenHeight=getWindowHeight();
				new Handler().postDelayed(nextScreen, 1000);
			}
		});
		fadeIn.setFillEnabled(true);
		shadow.startAnimation(fadeIn);
	}
	
	Runnable nextScreen=new Runnable() {
		
		@Override
		public void run() {
			startActivity(new Intent(SplashScreen.this,MenuScreen.class));
			finish();
		}
	};
	
	/** Gets Width width */
	private int getWindowWidth() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels;
	}
	/** Gets Width height */
	private int getWindowHeight() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.heightPixels;
	}
	
	
}
