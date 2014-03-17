//package in.abmulani.aamadmiparty.activities;
//
//import android.app.ActionBar;
//import android.app.ActionBar.LayoutParams;
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.drawable.ColorDrawable;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import in.abmulani.aamadmiparty.R;
//import in.abmulani.aamadmiparty.datamodels.ResultContent;
//import in.abmulani.aamadmiparty.datamodels.ResultListContent;
//import in.abmulani.aamadmiparty.utils.CachedData;
//import in.abmulani.aamadmiparty.utils.CommonMethods;
//import in.abmulani.aamadmiparty.utils.LITERALS;
//import in.abmulani.aamadmiparty.utils.LITERALS.CATEGORY;
//import in.abmulani.aamadmiparty.utils.URL_LIST;
//
//public class ListViewActivity extends Activity implements OnItemClickListener {
//	private ListView mListView;
//	private DisplayImageOptions options;
//	private CATEGORY selectedType;
//	private MyCustomAdaptor myAdaptor;
//	private List<ResultContent> adaptorList = new ArrayList<ResultContent>();
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_listall);
//		selectedType = (CATEGORY)getIntent().getSerializableExtra("selected");
//		InitActionBar();
//		options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.image_place_holder).showImageForEmptyUri(R.drawable.image_place_holder)
//				.showImageOnFail(R.drawable.ic_launcher).resetViewBeforeLoading(false).cacheInMemory(true).cacheOnDisc(true).build();
//		mListView = (ListView) findViewById(R.id.al_listview);
//		mListView.setOnItemClickListener(this);
//		myAdaptor = new MyCustomAdaptor(ListViewActivity.this);
//		mListView.setAdapter(myAdaptor);
//		new getAllNotification().execute();
//	}
//
//	private void InitActionBar() {
//		ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(R.layout.custom_action_bar, null);
//		ActionBar actionBar = getActionBar();
//		actionBar.setDisplayShowHomeEnabled(false);
//		actionBar.setDisplayShowTitleEnabled(false);
//		actionBar.setDisplayShowCustomEnabled(true);
//		actionBar.setCustomView(actionBarLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//		TextView hdrText = (TextView) actionBarLayout.findViewById(R.id.cab_title);
//		hdrText.setText(CommonMethods.getName(selectedType));
//		actionBarLayout.findViewById(R.id.cab_back).setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				onBackPressed();
//			}
//		});
//		RelativeLayout backLayout = (RelativeLayout) actionBarLayout.findViewById(R.id.cab_actionBarLayout);
//		backLayout.setBackgroundDrawable(new ColorDrawable(CommonMethods.getColor(selectedType, getResources())));
//	}
//
//	class getAllNotification extends AsyncTask<Void, Void, Boolean> {
//		private String ERROR = null;
//		private String RESULT_STRING;
//		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//		boolean internet = false;
//
//		@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
//			CommonMethods.ShowProgressDialog(ListViewActivity.this, "Loading..", false, this);
//		}
//
//		@Override
//		protected Boolean doInBackground(Void... arg0) {
//			internet = CommonMethods.checkInternetConnection(ListViewActivity.this);
//			if (internet) {
//				try {
//					HttpClient httpclient = new DefaultHttpClient();
//					HttpPost httppost = new HttpPost(URL_LIST.getUrl(selectedType));
//					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//					HttpResponse response = httpclient.execute(httppost);
//					HttpEntity httpEntity = response.getEntity();
//					RESULT_STRING = EntityUtils.toString(httpEntity);
//					Log.d("Responce: ", RESULT_STRING);
//					return true;
//				} catch (Exception ex) {
//					ex.printStackTrace();
//					ERROR = ex.getMessage();
//				}
//			}
//			return false;
//		}
//
//		@Override
//		protected void onPostExecute(Boolean result) {
//			super.onPostExecute(result);
//			CommonMethods.HideProgressBar();
//			if (result) {
//				try {
//
//                            if(selectedType==CATEGORY.Jokes){
//
//                            }else if(selectedType==CATEGORY.Videos){
//
//                            }else {
//                                Gson gson = new GsonBuilder().create();
//                                ResultListContent retrievedList = gson.fromJson("{\"result\":" + RESULT_STRING + "}", ResultListContent.class);
//                                if (retrievedList.getResultContent().size() > 0) {
//                                    adaptorList.addAll(retrievedList.getResultContent());
//                                    myAdaptor.notifyDataSetChanged();
//                                    updateContentList(retrievedList.getResultContent(), selectedType);
//                            }
//							Log.e("LENGTH", "" + retrievedList.getResultContent().size());
//						} else {
//							CommonMethods.showToast(ListViewActivity.this, "NO DATA.");
//						}
//				} catch (Exception e) {
//					CommonMethods.showToast(ListViewActivity.this, "NO DATA.");
//					e.printStackTrace();
//				}
//			} else {
//				if (internet) {
//					CommonMethods.showToast(ListViewActivity.this, ERROR);
//				} else {
//					CommonMethods.showToast(ListViewActivity.this, "No Network Available..");
//				}
//			}
//		}
//	}
//
//	public class MyCustomAdaptor extends BaseAdapter {
//
//		private LayoutInflater inflater;
//
//		public MyCustomAdaptor(Activity context) {
//			this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		}
//
//		@Override
//		public int getCount() {
//			return adaptorList.size();
//		}
//
//		@Override
//		public Object getItem(int position) {
//			return position;
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return position;
//		}
//
//		class ViewHolder {
//			TextView TimeTxt, headerTxt;
//			ImageView image;
//		}
//
//		@Override
//		public View getView(final int position, View convertView, ViewGroup parent) {
//			View view = null;
//			if (convertView == null) {
//				view = inflater.inflate(R.layout.inflater_list_item, null);
//				ViewHolder viewHolder = new ViewHolder();
//				viewHolder.headerTxt = (TextView) view.findViewById(R.id.ili_title);
//				viewHolder.TimeTxt = (TextView) view.findViewById(R.id.ili_time);
//				viewHolder.image = (ImageView) view.findViewById(R.id.ili_image);
//				view.setTag(viewHolder);
//
//			} else {
//				view = convertView;
//			}
//			final ViewHolder holder = (ViewHolder) view.getTag();
//			holder.headerTxt.setText(adaptorList.get(position).getTitle());
//			String dateTime = adaptorList.get(position).getCreated_on();
//			holder.TimeTxt.setText(CommonMethods.convertDateInfo(dateTime.substring(0, 10)));
//			ImageLoader.getInstance().displayImage(URL_LIST.BASE_URL + adaptorList.get(position).getImage_url(), holder.image, options);
//			return view;
//		}
//	}
//
//	@Override
//	public void onItemClick(AdapterView<?> adaptor, View view, int position, long arg3) {
//		Intent intent = new Intent(ListViewActivity.this, DetailedViewActivity.class);
//		intent.putExtra("position", position);
//		intent.putExtra("selected", selectedType);
//		startActivity(intent);
//	}
//
//	public void updateContentList(List<ResultContent> resultContent, CATEGORY selectedType2) {
//		switch (selectedType2) {
//		case LITERALS.TYPE_HISTORY:
//			CachedData.HistoryList.addAll(resultContent);
//			break;
//		case LITERALS.TYPE_ARVIND_KEJRIWAL:
//			CachedData.ArvindList.addAll(resultContent);
//			break;
//		case LITERALS.TYPE_CAMPAIGN_INNOVATION:
//			CachedData.CampaignInovationList.addAll(resultContent);
//			break;
//		case LITERALS.TYPE_CELEBRATIES:
//			CachedData.CelebratiesList.addAll(resultContent);
//			break;
//		case LITERALS.TYPE_JOKES:
//			CachedData.JokesList.addAll(resultContent);
//			break;
//		case LITERALS.TYPE_LEADERS:
//			CachedData.LeadersList.addAll(resultContent);
//			break;
//		case LITERALS.TYPE_LOK_SABHA:
//			CachedData.LS_14List.addAll(resultContent);
//			break;
//		case LITERALS.TYPE_PATH_BERAKING:
//			CachedData.PathBreakingList.addAll(resultContent);
//			break;
//		case LITERALS.TYPE_POLICIES:
//			CachedData.PathBreakingList.addAll(resultContent);
//			break;
//		}
//	}
//
//}
