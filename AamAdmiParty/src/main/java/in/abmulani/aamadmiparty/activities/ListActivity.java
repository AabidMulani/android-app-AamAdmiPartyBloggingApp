package in.abmulani.aamadmiparty.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.markupartist.android.widget.PullToRefreshListView;
import com.markupartist.android.widget.PullToRefreshListView.OnRefreshListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.abmulani.aamadmiparty.R;
import in.abmulani.aamadmiparty.datamodels.ResultContent;
import in.abmulani.aamadmiparty.datamodels.ResultJokes;
import in.abmulani.aamadmiparty.datamodels.ResultListContent;
import in.abmulani.aamadmiparty.datamodels.ResultListJokes;
import in.abmulani.aamadmiparty.datamodels.ResultListVideo;
import in.abmulani.aamadmiparty.datamodels.ResultVideo;
import in.abmulani.aamadmiparty.ormmodels.Aapcelebs;
import in.abmulani.aamadmiparty.ormmodels.Arvindkejriwal;
import in.abmulani.aamadmiparty.ormmodels.Campaigninnovation;
import in.abmulani.aamadmiparty.ormmodels.History;
import in.abmulani.aamadmiparty.ormmodels.Jokes;
import in.abmulani.aamadmiparty.ormmodels.Leaders;
import in.abmulani.aamadmiparty.ormmodels.Loksabha;
import in.abmulani.aamadmiparty.ormmodels.Pathbreakingnews;
import in.abmulani.aamadmiparty.ormmodels.Policies;
import in.abmulani.aamadmiparty.ormmodels.Videos;
import in.abmulani.aamadmiparty.utils.AppConstants;
import in.abmulani.aamadmiparty.utils.CommonMethods;
import in.abmulani.aamadmiparty.utils.LITERALS.CATEGORY;
import in.abmulani.aamadmiparty.utils.Logger;

/**
 * Created by AABID on 18/3/14.
 */
public class ListActivity extends Activity implements AdapterView.OnItemClickListener, OnRefreshListener {
    private final String TAG = "ListActivity";
    public final int PAGE_LIMIT = 25;

    private List<Videos> contentVideoList = new ArrayList<Videos>();
    private List<Jokes> contentJokesList = new ArrayList<Jokes>();
    private List<Aapcelebs> contentAppCelebsist = new ArrayList<Aapcelebs>();
    private List<Arvindkejriwal> contentArvindKejriwalList = new ArrayList<Arvindkejriwal>();
    private List<Campaigninnovation> contentCampaignInnovationList = new ArrayList<Campaigninnovation>();
    private List<Policies> contentPoliciesList = new ArrayList<Policies>();
    private List<Loksabha> contentLokSabha2014List = new ArrayList<Loksabha>();
    private List<Pathbreakingnews> contentPathBreakingNewsList = new ArrayList<Pathbreakingnews>();
    private List<History> contentHistoryList = new ArrayList<History>();
    private List<Leaders> contentLeadersList = new ArrayList<Leaders>();

    private List<?> contentAdaptorList;

    private BaseAdapter listAdaptor;
    private CATEGORY selectedCategory;
    private GetNewData asyncCall;
    @InjectView(R.id.al_listview)
    PullToRefreshListView listView;
    private DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listall);
        ButterKnife.inject(this);
        selectedCategory = (CATEGORY) getIntent().getSerializableExtra("selected");
        updateActionBar();
        listView.setOnRefreshListener(this);
        listView.setOnItemClickListener(this);
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.place_holder).showImageForEmptyUri(R.drawable.place_holder)
                .showImageOnFail(R.drawable.ic_launcher).resetViewBeforeLoading(false).cacheInMemory(true).cacheOnDisc(true).build();
        readFromDatabaseFirst();
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

    private void readFromDatabaseFirst() {
        Logger.d(TAG, "readFromDatabaseFirst");
        startAnimating();
        boolean noDataFound = false;
        switch (selectedCategory) {
            case Jokes:
                listAdaptor = new JokesAdaptor(ListActivity.this);
                contentJokesList = Jokes.listAll(Jokes.class);
                if (contentJokesList.size() == 0) {
                    noDataFound = true;
                }
                break;
            case Videos:
                listAdaptor = new VideosAdaptor(ListActivity.this);
                contentVideoList = Videos.listAll(Videos.class);
                if (contentVideoList.size() == 0) {
                    noDataFound = true;
                }
                break;
            default:
                listAdaptor = new ContentAdaptor(ListActivity.this);
                switch (selectedCategory) {
                    case Policies:
                        listAdaptor = new ContentAdaptor(ListActivity.this);
                        contentPoliciesList = Policies.listAll(Policies.class);
                        contentAdaptorList = contentPoliciesList;
                        if (contentPoliciesList.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                    case PathBreakingNews:
                        listAdaptor = new ContentAdaptor(ListActivity.this);
                        contentPathBreakingNewsList = Pathbreakingnews.listAll(Pathbreakingnews.class);
                        contentAdaptorList = contentPathBreakingNewsList;
                        if (contentPathBreakingNewsList.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                    case AapCelebs:
                        listAdaptor = new ContentAdaptor(ListActivity.this);
                        contentAppCelebsist = Aapcelebs.listAll(Aapcelebs.class);
                        contentAdaptorList = contentAppCelebsist;
                        if (contentAppCelebsist.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                    case ArvindKejriwal:
                        listAdaptor = new ContentAdaptor(ListActivity.this);
                        contentArvindKejriwalList = Arvindkejriwal.listAll(Arvindkejriwal.class);
                        contentAdaptorList = contentArvindKejriwalList;
                        if (contentArvindKejriwalList.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                    case CampaignInnovation:
                        listAdaptor = new ContentAdaptor(ListActivity.this);
                        contentCampaignInnovationList = Campaigninnovation.listAll(Campaigninnovation.class);
                        contentAdaptorList = contentCampaignInnovationList;
                        if (contentCampaignInnovationList.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                    case History:
                        listAdaptor = new ContentAdaptor(ListActivity.this);
                        contentHistoryList = History.listAll(History.class);
                        contentAdaptorList = contentHistoryList;
                        if (contentHistoryList.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                    case Leaders:
                        listAdaptor = new ContentAdaptor(ListActivity.this);
                        contentLeadersList = Leaders.listAll(Leaders.class);
                        contentAdaptorList = contentLeadersList;
                        if (contentLeadersList.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                    case LokSabha2014:
                        listAdaptor = new ContentAdaptor(ListActivity.this);
                        contentLokSabha2014List = Loksabha.listAll(Loksabha.class);
                        contentAdaptorList = contentLokSabha2014List;
                        if (contentLokSabha2014List.size() == 0) {
                            noDataFound = true;
                        }
                        break;
                }
        }
        listView.setAdapter(listAdaptor);
        if (noDataFound) {
            new GetNewData(0, selectedCategory, true).execute();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Logger.d(TAG, "onItemClick");
        position--;
        if(selectedCategory== CATEGORY.Videos){
            Logger.d("YouTube Video Url",contentVideoList.get(position).getVideo_url());
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(contentVideoList.get(position).getVideo_url())));
        }else {
            Intent intent = new Intent(ListActivity.this, DetailedViewActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("category", selectedCategory);
            startActivity(intent);
        }
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

    @Override
    protected void onDestroy() {
        Logger.d(TAG, "onDestroy");
        super.onDestroy();
        if (asyncCall != null) {
            if (asyncCall.getStatus() == AsyncTask.Status.PENDING || asyncCall.getStatus() == AsyncTask.Status.RUNNING) {
                asyncCall.cancel(true);
            }
        }
    }

    @Override
    public void onRefresh() {
        Logger.d(TAG, "onRefresh");
        int startCount = 0;
        try {
            switch (selectedCategory) {
                case Jokes: {
                    List<Jokes> value = Jokes.findWithQuery(Jokes.class, "SELECT * FROM Jokes ORDER BY id DESC LIMIT 0,1", null);
                    if (value.size()==0) {
                        Logger.d(TAG, "No values found while retrieving Highest ID for Jokes");
                    } else {
                        startCount = Integer.parseInt(value.get(0).getPost_id());
                        Logger.d(TAG, "Highest ID for Jokes : " + startCount);
                    }
                }
                break;
                case Videos: {
                    List<Videos> value = Videos.findWithQuery(Videos.class, "SELECT * FROM Videos ORDER BY id DESC LIMIT 0,1", null);
                    if (value.size()==0) {
                        Logger.d(TAG, "No values found while retrieving Highest ID for Videos");
                    } else {
                        startCount = Integer.parseInt(value.get(0).getPost_id());
                        Logger.d(TAG, "Highest ID for Videos : " + startCount);
                    }
                }
                break;
                default:
                    switch (selectedCategory) {
                        case Policies: {
                            List<Policies> value = Policies.findWithQuery(Policies.class, "SELECT * FROM Policies ORDER BY id DESC LIMIT 0,1", null);
                            if (value.size()==0) {
                                Logger.d(TAG, "No values found while retrieving Highest ID for Policies");
                            } else {
                                startCount = Integer.parseInt(value.get(0).getPost_id());
                                Logger.d(TAG, "Highest ID for Policies : " + startCount);
                            }
                        }
                        break;
                        case PathBreakingNews: {
                            List<Pathbreakingnews> value = Pathbreakingnews.findWithQuery(Pathbreakingnews.class, "SELECT * FROM Pathbreakingnews ORDER BY id DESC LIMIT 0,1", null);
                            if (value.size()==0) {
                                Logger.d(TAG, "No values found while retrieving Highest ID for PathBreakingNews");
                            } else {
                                startCount = Integer.parseInt(value.get(0).getPost_id());
                                Logger.d(TAG, "Highest ID for PathBreakingNews : " + startCount);
                            }
                        }
                        break;
                        case AapCelebs: {
                            List<Aapcelebs> value = Aapcelebs.findWithQuery(Aapcelebs.class, "SELECT * FROM Aapcelebs ORDER BY id DESC LIMIT 0,1", null);
                            if (value.size()==0) {
                                Logger.d(TAG, "No values found while retrieving Highest ID for AapCelebs");
                            } else {
                                startCount = Integer.parseInt(value.get(0).getPost_id());
                                Logger.d(TAG, "Highest ID for AapCelebs : " + startCount);
                            }
                        }
                        break;
                        case ArvindKejriwal://SELECT * FROM `ArvindKejriwal` ORDER BY `id` DESC LIMIT 0,1
                        {
                            List<Arvindkejriwal> value = Arvindkejriwal.findWithQuery(Arvindkejriwal.class, "SELECT * FROM Arvindkejriwal ORDER BY id DESC LIMIT 0,1", null);
                            if (value.size()==0) {
                                Logger.d(TAG, "No values found while retrieving Highest ID for ArvindKejriwal");
                            } else {
                                startCount = Integer.parseInt(value.get(0).getPost_id());
                                Logger.d(TAG, "Highest ID for ArvindKejriwal : " + startCount);
                            }
                        }
                        break;
                        case CampaignInnovation: {
                            List<Campaigninnovation> value = Campaigninnovation.findWithQuery(Campaigninnovation.class, "SELECT * FROM Campaigninnovation ORDER BY id DESC LIMIT 0,1", null);
                            if (value.size()==0) {
                                Logger.d(TAG, "No values found while retrieving Highest ID for CampaignInnovation");
                            } else {
                                startCount = Integer.parseInt(value.get(0).getPost_id());
                                Logger.d(TAG, "Highest ID for CampaignInnovation : " + startCount);
                            }
                        }
                        break;
                        case History: {
                            List<History> value = History.findWithQuery(History.class, "SELECT * FROM History ORDER BY id DESC LIMIT 0,1", null);
                            if (value.size()==0) {
                                Logger.d(TAG, "No values found while retrieving Highest ID for History");
                            } else {
                                startCount = Integer.parseInt(value.get(0).getPost_id());
                                Logger.d(TAG, "Highest ID for History : " + startCount);
                            }
                        }
                        break;
                        case Leaders: {
                            List<Leaders> value = Leaders.findWithQuery(Leaders.class, "SELECT * FROM Leaders ORDER BY id DESC LIMIT 0,1", null);
                            if (value.size()==0) {
                                Logger.d(TAG, "No values found while retrieving Highest ID for Leaders");
                            } else {
                                startCount = Integer.parseInt(value.get(0).getPost_id());
                                Logger.d(TAG, "Highest ID for Leaders : " + startCount);
                            }
                        }
                        break;
                        case LokSabha2014: {
                            List<Loksabha> value = Loksabha.findWithQuery(Loksabha.class, "SELECT * FROM Loksabha ORDER BY id DESC LIMIT 0,1", null);
                            if (value.size()==0) {
                                Logger.d(TAG, "No values found while retrieving Highest ID for LokSabha2014");
                            } else {
                                startCount = Integer.parseInt(value.get(0).getPost_id());
                                Logger.d(TAG, "Highest ID for LokSabha2014 : " + startCount);
                            }
                        }
                        break;
                    }
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            startCount = 0;
        }
        new GetNewData(startCount, selectedCategory, false).execute();
    }

    class GetNewData extends AsyncTask<Void, Void, Boolean> {
        private ProgressDialog progressDialog;
        private String errorString = null;
        private String resultString;
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        private boolean internet = false;
        private int startCount;
        private CATEGORY category;
        private boolean blocking;

        public GetNewData(int startCount, CATEGORY category, boolean blocking) {
            Logger.d(TAG, "GetNewData");
            this.startCount = startCount;
            this.category = category;
            asyncCall = this;
            this.blocking = blocking;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (blocking) {
                progressDialog = new ProgressDialog(ListActivity.this);
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage("Loading initial data..");
                progressDialog.setIndeterminate(true);
                progressDialog.show();
            } else {
                listView.prepareForRefresh();
            }
        }

        @Override
        protected Boolean doInBackground(Void... arg0) {
            internet = CommonMethods.checkInternetConnection(ListActivity.this);
            if (internet) {
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(AppConstants.REFRESH_LIST_URL);
                    //TODO add params
                    nameValuePairs.add(new BasicNameValuePair("category", category.name()));
                    nameValuePairs.add(new BasicNameValuePair("limit", String.valueOf(PAGE_LIMIT)));
                    nameValuePairs.add(new BasicNameValuePair("id", String.valueOf(startCount)));
                    Logger.d("REQUEST: ", nameValuePairs.toString());
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity httpEntity = response.getEntity();
                    resultString = EntityUtils.toString(httpEntity);
                    Logger.d("RESPONSE: ", resultString);
                    return true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    errorString = ex.getMessage();
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (blocking) {
                progressDialog.dismiss();
            } else {
                listView.onRefreshComplete();
            }
            if (result) {
                try {
                    if(resultString.trim().equals("NONE")){
                        //TODO show proper msg here
                        Toast.makeText(ListActivity.this,"List is Up To Date.",Toast.LENGTH_LONG).show();
                    }else {
                        parseAndStoreInDB("{\"result_Content\":" + resultString + "}", category);
                    }
                } catch (Exception e) {
                    CommonMethods.showToast(ListActivity.this, "NO DATA.");
                    e.printStackTrace();
                }
            } else {
                if (internet) {
                    CommonMethods.showToast(ListActivity.this, errorString);
                } else {
                    CommonMethods.showToast(ListActivity.this, "No Network Available..");
                }
            }
        }
    }

    private void parseAndStoreInDB(String resultString, CATEGORY selectedCategory) {
        Logger.d(TAG, "parseAndStoreInDB");
        String TAG = "SAVING";
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Logger.d(TAG, selectedCategory.name());
        switch (selectedCategory) {
            case Jokes: {
                ResultListJokes retrievedList = gson.fromJson(resultString, ResultListJokes.class);
                Logger.d("Length", retrievedList.getResultContent().size() + "");
                if (retrievedList.getResultContent().size() > 0) {
                    for (ResultJokes result : retrievedList.getResultContent()) {
                        Logger.d(TAG, "Jokes:postId" + result.getPost_id());
                        Jokes jokes = new Jokes(ListActivity.this, result);
                        jokes.save();
                        contentJokesList.add(jokes);
                    }
                }
            }
            break;
            case Videos: {
                ResultListVideo retrievedList = gson.fromJson(resultString, ResultListVideo.class);
                Logger.d("Length", retrievedList.getResultContent().size() + "");
                if (retrievedList.getResultContent().size() > 0) {
                    for (ResultVideo result : retrievedList.getResultContent()) {
                        Logger.d(TAG, "Videos:postId" + result.getPost_id());
                        Videos videos = new Videos(ListActivity.this, result);
                        videos.save();
                        contentVideoList.add(videos);
                    }
                }
            }
            break;
            default:
                ResultListContent retrievedList = gson.fromJson(resultString, ResultListContent.class);
                Logger.d("Length", retrievedList.getResultContent().size() + "");
                switch (selectedCategory) {
                    case Policies:
                        if (retrievedList.getResultContent().size() > 0) {
                            for (ResultContent result : retrievedList.getResultContent()) {
                                Logger.d(TAG, "Policies:postId" + result.getPost_id());
                                Policies mPolicies = new Policies(ListActivity.this, result);
                                mPolicies.save();
                                contentPoliciesList.add(mPolicies);
                            }
                        }
                        break;
                    case PathBreakingNews:
                        if (retrievedList.getResultContent().size() > 0) {
                            for (ResultContent result : retrievedList.getResultContent()) {
                                Logger.d(TAG, "Pathbreakingnews:postId" + result.getPost_id());
                                Pathbreakingnews mPathBreakingNews = new Pathbreakingnews(ListActivity.this, result);
                                mPathBreakingNews.save();
                                contentPathBreakingNewsList.add(mPathBreakingNews);
                            }
                        }
                        break;
                    case AapCelebs:
                        if (retrievedList.getResultContent().size() > 0) {
                            for (ResultContent result : retrievedList.getResultContent()) {
                                Logger.d(TAG, "Aapcelebs:postId" + result.getPost_id());
                                Aapcelebs mAapCelebs = new Aapcelebs(ListActivity.this, result);
                                mAapCelebs.save();
                                contentAppCelebsist.add(mAapCelebs);
                            }
                        }
                        break;
                    case ArvindKejriwal:
                        if (retrievedList.getResultContent().size() > 0) {
                            for (ResultContent result : retrievedList.getResultContent()) {
                                Logger.d(TAG, "Arvindkejriwal:postId" + result.getPost_id());
                                Arvindkejriwal mArvindKejriwal = new Arvindkejriwal(ListActivity.this, result);
                                mArvindKejriwal.save();
                                contentArvindKejriwalList.add(mArvindKejriwal);
                            }
                        }
                        break;
                    case CampaignInnovation:
                        if (retrievedList.getResultContent().size() > 0) {
                            for (ResultContent result : retrievedList.getResultContent()) {
                                Logger.d(TAG, "Campaigninnovation:postId" + result.getPost_id());
                                Campaigninnovation mCampaignInnovation = new Campaigninnovation(ListActivity.this, result);
                                mCampaignInnovation.save();
                                contentCampaignInnovationList.add(mCampaignInnovation);
                            }
                        }
                        break;
                    case History:
                        if (retrievedList.getResultContent().size() > 0) {
                            for (ResultContent result : retrievedList.getResultContent()) {
                                Logger.d(TAG, "History:postId" + result.getPost_id());
                                History mHistory = new History(ListActivity.this, result);
                                mHistory.save();
                                contentHistoryList.add(mHistory);
                            }
                        }
                        break;
                    case Leaders:
                        if (retrievedList.getResultContent().size() > 0) {
                            for (ResultContent result : retrievedList.getResultContent()) {
                                Logger.d(TAG, "Leaders:postId" + result.getPost_id());
                                Leaders mLeaders = new Leaders(ListActivity.this, result);
                                mLeaders.save();
                                contentLeadersList.add(mLeaders);
                            }
                        }
                        break;
                    case LokSabha2014:
                        if (retrievedList.getResultContent().size() > 0) {
                            for (ResultContent result : retrievedList.getResultContent()) {
                                Logger.d(TAG, "Loksabha:postId" + result.getPost_id());
                                Loksabha mLokSabha2014 = new Loksabha(ListActivity.this, result);
                                mLokSabha2014.save();
                                contentLokSabha2014List.add(mLokSabha2014);
                            }
                        }
                        break;
                }
        }
        listView.setAdapter(listAdaptor);
        stopAnimating();
    }

    private void startAnimating() {
        Logger.d(TAG, "");
        //TODO start smooth progress bar
    }

    private void stopAnimating() {
        Logger.d(TAG, "");
        //TODO stop smooth progress bar
    }

    public class ContentAdaptor extends BaseAdapter {

        private LayoutInflater inflater;

        public ContentAdaptor(Activity context) {
            Logger.d(TAG, "ContentAdaptor");
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return contentAdaptorList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            TextView timeTxt, headerTxt, subHeaderTxt;
            ImageView image;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                view = inflater.inflate(R.layout.inflater_list_item, null);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.headerTxt = (TextView) view.findViewById(R.id.ili_title);
                viewHolder.subHeaderTxt = (TextView) view.findViewById(R.id.ili_subtitle);
                viewHolder.timeTxt = (TextView) view.findViewById(R.id.ili_time);
                viewHolder.image = (ImageView) view.findViewById(R.id.ili_image);
                view.setTag(viewHolder);
            } else {
                view = convertView;
            }
            final ViewHolder holder = (ViewHolder) view.getTag();

            switch (selectedCategory) {
                case Policies: {
                    Policies valObj = (Policies) contentAdaptorList.get(position);
                    holder.headerTxt.setText(valObj.getTitle());
                    String dateTime = valObj.getCreated_on();
                    holder.subHeaderTxt.setText(valObj.getSubheading());
                    holder.timeTxt.setText(CommonMethods.convertDateInfo(dateTime.substring(0, 10)));
                    ImageLoader.getInstance().displayImage(AppConstants.IMAGE_BASE_URL + valObj.getImage_url(), holder.image, options);
                }
                break;
                case PathBreakingNews: {
                    Pathbreakingnews valObj = (Pathbreakingnews) contentAdaptorList.get(position);
                    holder.headerTxt.setText(valObj.getTitle());
                    String dateTime = valObj.getCreated_on();
                    holder.subHeaderTxt.setText(valObj.getSubheading());
                    holder.timeTxt.setText(CommonMethods.convertDateInfo(dateTime.substring(0, 10)));
                    ImageLoader.getInstance().displayImage(AppConstants.IMAGE_BASE_URL + valObj.getImage_url(), holder.image, options);
                }
                break;
                case AapCelebs: {
                    Aapcelebs valObj = (Aapcelebs) contentAdaptorList.get(position);
                    holder.headerTxt.setText(valObj.getTitle());
                    String dateTime = valObj.getCreated_on();
                    holder.subHeaderTxt.setText(valObj.getSubheading());
                    holder.timeTxt.setText(CommonMethods.convertDateInfo(dateTime.substring(0, 10)));
                    ImageLoader.getInstance().displayImage(AppConstants.IMAGE_BASE_URL + valObj.getImage_url(), holder.image, options);
                }
                break;
                case ArvindKejriwal: {
                    Arvindkejriwal valObj = (Arvindkejriwal) contentAdaptorList.get(position);
                    holder.headerTxt.setText(valObj.getTitle());
                    String dateTime = valObj.getCreated_on();
                    holder.subHeaderTxt.setText(valObj.getSubheading());
                    holder.timeTxt.setText(CommonMethods.convertDateInfo(dateTime.substring(0, 10)));
                    ImageLoader.getInstance().displayImage(AppConstants.IMAGE_BASE_URL + valObj.getImage_url(), holder.image, options);
                }
                break;
                case CampaignInnovation: {
                    Campaigninnovation valObj = (Campaigninnovation) contentAdaptorList.get(position);
                    holder.headerTxt.setText(valObj.getTitle());
                    String dateTime = valObj.getCreated_on();
                    holder.subHeaderTxt.setText(valObj.getSubheading());
                    holder.timeTxt.setText(CommonMethods.convertDateInfo(dateTime.substring(0, 10)));
                    ImageLoader.getInstance().displayImage(AppConstants.IMAGE_BASE_URL + valObj.getImage_url(), holder.image, options);
                }
                break;
                case History: {
                    History valObj = (History) contentAdaptorList.get(position);
                    holder.headerTxt.setText(valObj.getTitle());
                    String dateTime = valObj.getCreated_on();
                    holder.subHeaderTxt.setText(valObj.getSubheading());
                    holder.timeTxt.setText(CommonMethods.convertDateInfo(dateTime.substring(0, 10)));
                    ImageLoader.getInstance().displayImage(AppConstants.IMAGE_BASE_URL + valObj.getImage_url(), holder.image, options);
                }
                break;
                case Leaders: {
                    Leaders valObj = (Leaders) contentAdaptorList.get(position);
                    holder.headerTxt.setText(valObj.getTitle());
                    String dateTime = valObj.getCreated_on();
                    holder.subHeaderTxt.setText(valObj.getSubheading());
                    holder.timeTxt.setText(CommonMethods.convertDateInfo(dateTime.substring(0, 10)));
                    ImageLoader.getInstance().displayImage(AppConstants.IMAGE_BASE_URL + valObj.getImage_url(), holder.image, options);
                }
                break;
                case LokSabha2014:
                    Loksabha valObj = (Loksabha) contentAdaptorList.get(position);
                    holder.headerTxt.setText(valObj.getTitle());
                    String dateTime = valObj.getCreated_on();
                    holder.subHeaderTxt.setText(valObj.getSubheading());
                    holder.timeTxt.setText(CommonMethods.convertDateInfo(dateTime.substring(0, 10)));
                    ImageLoader.getInstance().displayImage(AppConstants.IMAGE_BASE_URL + valObj.getImage_url(), holder.image, options);

                    break;
            }
            return view;
        }
    }

    public class JokesAdaptor extends BaseAdapter {

        private LayoutInflater inflater;

        public JokesAdaptor(Activity context) {
            Logger.d(TAG, "JokesAdaptor");
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return contentJokesList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            TextView timeTxt, headerTxt;
            ImageView image;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                view = inflater.inflate(R.layout.inflater_jokes_item, null);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.headerTxt = (TextView) view.findViewById(R.id.ili_title);
                viewHolder.timeTxt = (TextView) view.findViewById(R.id.ili_time);
                viewHolder.image = (ImageView) view.findViewById(R.id.ili_image);
                view.setTag(viewHolder);
            } else {
                view = convertView;
            }
            final ViewHolder holder = (ViewHolder) view.getTag();
            holder.headerTxt.setText(contentJokesList.get(position).getTitle());
            String dateTime = contentJokesList.get(position).getCreated_on();
            holder.timeTxt.setText(CommonMethods.convertDateInfo(dateTime.substring(0, 10)));
            ImageLoader.getInstance().displayImage(AppConstants.IMAGE_BASE_URL + contentJokesList.get(position).getImage_url(), holder.image, options);
            return view;
        }
    }

    public class VideosAdaptor extends BaseAdapter {

        private LayoutInflater inflater;

        public VideosAdaptor(Activity context) {
            Logger.d(TAG, "VideosAdaptor");
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return contentVideoList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            TextView timeTxt, headerTxt;
            ImageView image;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                view = inflater.inflate(R.layout.inflater_video_item, null);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.headerTxt = (TextView) view.findViewById(R.id.ili_title);
                viewHolder.timeTxt = (TextView) view.findViewById(R.id.ili_time);
                viewHolder.image = (ImageView) view.findViewById(R.id.ili_image);
                view.setTag(viewHolder);
            } else {
                view = convertView;
            }
            final ViewHolder holder = (ViewHolder) view.getTag();
            holder.headerTxt.setText(contentVideoList.get(position).getTitle());
            String dateTime = contentVideoList.get(position).getCreated_on();
            holder.timeTxt.setText(CommonMethods.convertDateInfo(dateTime.substring(0, 10)));
            ImageLoader.getInstance().displayImage(contentVideoList.get(position).getImage_url(), holder.image, options);
            return view;
        }
    }

}
