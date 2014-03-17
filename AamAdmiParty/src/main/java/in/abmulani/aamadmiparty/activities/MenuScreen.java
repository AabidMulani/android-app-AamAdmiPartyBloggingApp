package in.abmulani.aamadmiparty.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import in.abmulani.aamadmiparty.R;
import in.abmulani.aamadmiparty.datamodels.MenuScreenModels;
import in.abmulani.aamadmiparty.fragments.GridMenuScreen;
import in.abmulani.aamadmiparty.utils.AppConstants;
import in.abmulani.aamadmiparty.utils.LITERALS.CATEGORY;

public class MenuScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuscreen);
        Log.e("REACHED", "HERE");
        AppConstants.menuList.clear();
        AppConstants.menuList.add(new MenuScreenModels("History", R.drawable.ic_launcher, CATEGORY.History, getResources().getDrawable(R.drawable.history_menu_tab_selector)));
        AppConstants.menuList.add(new MenuScreenModels("AapCelebs", R.drawable.ic_launcher, CATEGORY.AapCelebs, getResources().getDrawable(R.drawable.app_celebs_menu_tab_selector)));
        AppConstants.menuList.add(new MenuScreenModels("ArvindKejriwal", R.drawable.ic_launcher, CATEGORY.ArvindKejriwal, getResources().getDrawable(R.drawable.arvind_menu_tab_selector)));
        AppConstants.menuList.add(new MenuScreenModels("CampaignInnovation", R.drawable.ic_launcher, CATEGORY.CampaignInnovation,getResources().getDrawable(R.drawable.campaign_menu_tab_selector)));
        AppConstants.menuList.add(new MenuScreenModels("Jokes", R.drawable.ic_launcher, CATEGORY.Jokes, getResources().getDrawable(R.drawable.jokes_menu_tab_selector)));
        AppConstants.menuList.add(new MenuScreenModels("Leaders", R.drawable.ic_launcher, CATEGORY.Leaders, getResources().getDrawable(R.drawable.leaders_menu_tab_selector)));
        AppConstants.menuList.add(new MenuScreenModels("LokSabha2014", R.drawable.ic_launcher, CATEGORY.LokSabha2014, getResources().getDrawable(R.drawable.ls14_menu_tab_selector)));
        AppConstants.menuList.add(new MenuScreenModels("PathBreakingNews", R.drawable.ic_launcher, CATEGORY.PathBreakingNews,getResources().getDrawable(R.drawable.path_brk_menu_tab_selector)));
        AppConstants.menuList.add(new MenuScreenModels("Policies", R.drawable.ic_launcher, CATEGORY.Policies, getResources().getDrawable(R.drawable.policies_menu_tab_selector)));
        AppConstants.menuList.add(new MenuScreenModels("Videos", R.drawable.ic_launcher, CATEGORY.Videos,getResources().getDrawable(R.drawable.videos_menu_tab_selector)));
        FragmentManager mFragmentMgr = getFragmentManager();
        GridMenuScreen fragment = new GridMenuScreen();
        FragmentTransaction mFragmentTransc = mFragmentMgr.beginTransaction();
        mFragmentTransc.add(R.id.am_placeholder, fragment);
        mFragmentTransc.commit();

    }
//	@Override
//	public void onClick(View view) {
//		CachedData.ClearCache();
//		Intent intent = new Intent();
//		switch (view.getId()) {
//		case R.id.ams_aap_celebs_layout:
//			intent.putExtra("selected", CATEGORY.AapCelebs);
//			break;
//		case R.id.ams_arvind_k_layout:
//			intent.putExtra("selected", CATEGORY.ArvindKejriwal);
//			break;
//		case R.id.ams_campaign_inv_layout:
//			intent.putExtra("selected",CATEGORY.CampaignInnovation);
//			break;
//		case R.id.ams_history_layout:
//			intent.putExtra("selected", CATEGORY.History);
//			break;
//		case R.id.ams_jokes_layout:
//			intent.putExtra("selected", CATEGORY.Jokes);
//			break;
//		case R.id.ams_leaders_layout:
//			intent.putExtra("selected", CATEGORY.Leaders);
//			break;
//		case R.id.ams_lok_sabha14_layout:
//			intent.putExtra("selected", CATEGORY.LokSabha2014);
//			break;
//		case R.id.ams_path_bk_layout:
//			intent.putExtra("selected", CATEGORY.PathBreakingNews);
//			break;
//		default:
//			break;
//		}
//		startActivity(intent);
//	}

}
