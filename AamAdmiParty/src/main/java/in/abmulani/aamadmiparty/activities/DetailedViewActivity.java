package in.abmulani.aamadmiparty.activities;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.MenuItem;

import java.util.List;

import in.abmulani.aamadmiparty.R;
import in.abmulani.aamadmiparty.fragments.DetailedContentFragment;
import in.abmulani.aamadmiparty.fragments.DetailedJokeFragment;
import in.abmulani.aamadmiparty.ormmodels.Aapcelebs;
import in.abmulani.aamadmiparty.ormmodels.Arvindkejriwal;
import in.abmulani.aamadmiparty.ormmodels.Campaigninnovation;
import in.abmulani.aamadmiparty.ormmodels.History;
import in.abmulani.aamadmiparty.ormmodels.Jokes;
import in.abmulani.aamadmiparty.ormmodels.Leaders;
import in.abmulani.aamadmiparty.ormmodels.Loksabha;
import in.abmulani.aamadmiparty.ormmodels.Pathbreakingnews;
import in.abmulani.aamadmiparty.ormmodels.Policies;
import in.abmulani.aamadmiparty.utils.CommonMethods;
import in.abmulani.aamadmiparty.utils.LITERALS.CATEGORY;
import in.abmulani.aamadmiparty.utils.Logger;
import in.abmulani.aamadmiparty.widgets.CustomViewPager;
import uk.co.senab.photoview.PhotoViewAttacher;

public class DetailedViewActivity extends FragmentActivity {
    private final String TAG = "DetailedViewActivity";
    private CATEGORY selectedCategory;
    private List<?> contentAdaptorList;
    private CustomViewPager pager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_screen_layout);
        selectedCategory = (CATEGORY) getIntent().getExtras().getSerializable("category");
        int position = getIntent().getExtras().getInt("position");
        updateActionBar();
        readFromDatabaseFirst();
        MyPageAdapter pageAdapter = new MyPageAdapter(getSupportFragmentManager());
        pager = (CustomViewPager) findViewById(R.id.adsl_viewpager);
        pager.setAdapter(pageAdapter);
        pager.setCurrentItem(position, true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.d(TAG, "onOptionsItemSelected");
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateActionBar() {
        Logger.d(TAG, "updateActionBar");
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(CommonMethods.getColor(selectedCategory, getResources())));
            if (Build.VERSION.SDK_INT >= 16)
                actionBar.setDisplayHomeAsUpEnabled(true);
            if (Build.VERSION.SDK_INT >= 14)
                actionBar.setIcon(new ColorDrawable(0));
            actionBar.setTitle(selectedCategory.name());
        }
    }

    public PhotoViewAttacher.ViewPagerListener pagerListener=new PhotoViewAttacher.ViewPagerListener() {
        @Override
        public void setViewPagerEnable(boolean isEnabled) {
            pager.setPagingEnabled(isEnabled);
            Log.e("EVENT:", "" + isEnabled);
        }
    };


    private class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment pageFragmet=null;
            if (selectedCategory == CATEGORY.Jokes) {
//                pageFragmet
                Jokes valObj = (Jokes) contentAdaptorList.get(position);
                pageFragmet = DetailedJokeFragment.newInstance(valObj.getImage_url(), valObj.getTitle(), valObj.getCreated_on());
            } else {
                switch (selectedCategory) {
                    case Policies: {
                        Policies valObj = (Policies) contentAdaptorList.get(position);
                        pageFragmet = DetailedContentFragment.newInstance(valObj.getImage_url(), valObj.getTitle(), valObj.getSubheading(), valObj.getContent(), valObj.getCreated_on());
                    }
                    break;
                    case PathBreakingNews: {
                        Pathbreakingnews valObj = (Pathbreakingnews) contentAdaptorList.get(position);
                        pageFragmet = DetailedContentFragment.newInstance(valObj.getImage_url(), valObj.getTitle(), valObj.getSubheading(), valObj.getContent(), valObj.getCreated_on());
                    }
                    break;
                    case AapCelebs: {
                        Aapcelebs valObj = (Aapcelebs) contentAdaptorList.get(position);
                        pageFragmet = DetailedContentFragment.newInstance(valObj.getImage_url(), valObj.getTitle(), valObj.getSubheading(), valObj.getContent(), valObj.getCreated_on());
                    }
                    break;
                    case ArvindKejriwal: {
                        Arvindkejriwal valObj = (Arvindkejriwal) contentAdaptorList.get(position);
                        pageFragmet = DetailedContentFragment.newInstance(valObj.getImage_url(), valObj.getTitle(), valObj.getSubheading(), valObj.getContent(), valObj.getCreated_on());
                    }
                    break;
                    case CampaignInnovation: {
                        Campaigninnovation valObj = (Campaigninnovation) contentAdaptorList.get(position);
                        pageFragmet = DetailedContentFragment.newInstance(valObj.getImage_url(), valObj.getTitle(), valObj.getSubheading(), valObj.getContent(), valObj.getCreated_on());
                    }
                    break;
                    case History: {
                        History valObj = (History) contentAdaptorList.get(position);
                        pageFragmet = DetailedContentFragment.newInstance(valObj.getImage_url(), valObj.getTitle(), valObj.getSubheading(), valObj.getContent(), valObj.getCreated_on());
                    }
                    break;
                    case Leaders: {
                        Leaders valObj = (Leaders) contentAdaptorList.get(position);
                        pageFragmet = DetailedContentFragment.newInstance(valObj.getImage_url(), valObj.getTitle(), valObj.getSubheading(), valObj.getContent(), valObj.getCreated_on());
                    }
                    break;
                    case LokSabha2014:
                        Loksabha valObj = (Loksabha) contentAdaptorList.get(position);
                        pageFragmet = DetailedContentFragment.newInstance(valObj.getImage_url(), valObj.getTitle(), valObj.getSubheading(), valObj.getContent(), valObj.getCreated_on());
                        break;
                }
            }
            return pageFragmet;
        }

        @Override
        public int getCount() {
            return contentAdaptorList.size();
        }
    }

    private void readFromDatabaseFirst() {
        Logger.d(TAG, "readFromDatabaseFirst");
        boolean noDataFound = false;
        switch (selectedCategory) {
            case Jokes:
                List<Jokes> contentJokesList = Jokes.listAll(Jokes.class);
                contentAdaptorList=contentJokesList;
                if (contentJokesList.size() == 0) {
                    noDataFound = true;
                }
                break;
            default:
                switch (selectedCategory) {
                    case Policies:
                        List<Policies> contentPoliciesList = Policies.listAll(Policies.class);
                        contentAdaptorList = contentPoliciesList;
                        if (contentPoliciesList.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                    case PathBreakingNews:
                        List<Pathbreakingnews> contentPathBreakingNewsList = Pathbreakingnews.listAll(Pathbreakingnews.class);
                        contentAdaptorList = contentPathBreakingNewsList;
                        if (contentPathBreakingNewsList.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                    case AapCelebs:
                        List<Aapcelebs> contentAppCelebsist = Aapcelebs.listAll(Aapcelebs.class);
                        contentAdaptorList = contentAppCelebsist;
                        if (contentAppCelebsist.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                    case ArvindKejriwal:
                        List<Arvindkejriwal> contentArvindKejriwalList = Arvindkejriwal.listAll(Arvindkejriwal.class);
                        contentAdaptorList = contentArvindKejriwalList;
                        if (contentArvindKejriwalList.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                    case CampaignInnovation:
                        List<Campaigninnovation> contentCampaignInnovationList = Campaigninnovation.listAll(Campaigninnovation.class);
                        contentAdaptorList = contentCampaignInnovationList;
                        if (contentCampaignInnovationList.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                    case History:
                        List<History> contentHistoryList = History.listAll(History.class);
                        contentAdaptorList = contentHistoryList;
                        if (contentHistoryList.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                    case Leaders:
                        List<Leaders> contentLeadersList = Leaders.listAll(Leaders.class);
                        contentAdaptorList = contentLeadersList;
                        if (contentLeadersList.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                    case LokSabha2014:
                        List<Loksabha> contentLokSabha2014List = Loksabha.listAll(Loksabha.class);
                        contentAdaptorList = contentLokSabha2014List;
                        if (contentLokSabha2014List.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                }
        }
    }

}
