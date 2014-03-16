package in.abmulani.aamadmiparty.activities;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import in.abmulani.aamadmiparty.R;
import in.abmulani.aamadmiparty.datamodels.Result;
import in.abmulani.aamadmiparty.fragments.DetailedViewFragment;
import in.abmulani.aamadmiparty.utils.CachedData;
import in.abmulani.aamadmiparty.utils.CommonMethods;
import in.abmulani.aamadmiparty.utils.LITERALS;

public class DetailedViewActivity extends FragmentActivity {
	private MyPageAdapter pageAdapter;
	private int selectedType;
	private List<Result> fullListView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed_screen_layout);
		selectedType=getIntent().getExtras().getInt("selected");
		InitActionBar();
		fullListView=getSelectedResult(selectedType);
		int position = getIntent().getExtras().getInt("position");
		pageAdapter = new MyPageAdapter(getSupportFragmentManager());
		ViewPager pager = (ViewPager) findViewById(R.id.adsl_viewpager);
		pager.setAdapter(pageAdapter);
		pager.setCurrentItem(position, true);
	}

	private class MyPageAdapter extends FragmentPagerAdapter {

		public MyPageAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment pageFragmet = DetailedViewFragment.newInstance(position, selectedType);
			return pageFragmet;
		}

		@Override
		public int getCount() {
			return fullListView.size();
		}
	}

	private void InitActionBar() {
		ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(R.layout.custom_action_bar, null);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setCustomView(actionBarLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		TextView hdrText = (TextView) actionBarLayout.findViewById(R.id.cab_title);
		hdrText.setText(CommonMethods.getName(selectedType));
		actionBarLayout.findViewById(R.id.cab_back).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
		});
		RelativeLayout backLayout = (RelativeLayout) actionBarLayout.findViewById(R.id.cab_actionBarLayout);
		backLayout.setBackgroundDrawable(new ColorDrawable(CommonMethods.getColor(selectedType,getResources())));
	}
	
	private List<Result> getSelectedResult(int selectedType) {
		switch (selectedType) {
		case LITERALS.TYPE_HISTORY:
			return CachedData.HistoryList;
		case LITERALS.TYPE_ARVIND_KEJRIWAL:
			return CachedData.ArvindList;
		case LITERALS.TYPE_CAMPAIGN_INNOVATION:
			return CachedData.CampaignInovationList;
		case LITERALS.TYPE_CELEBRATIES:
			return CachedData.CelebratiesList;
		case LITERALS.TYPE_JOKES:
			return CachedData.JokesList;
		case LITERALS.TYPE_LEADERS:
			return CachedData.LeadersList;
		case LITERALS.TYPE_LOK_SABHA:
			return CachedData.LS_14List;
		case LITERALS.TYPE_PATH_BERAKING:
			return CachedData.PathBreakingList;
		case LITERALS.TYPE_POLICIES:
			return CachedData.PoliciesList;
		}
		return null;
	}
	
}
