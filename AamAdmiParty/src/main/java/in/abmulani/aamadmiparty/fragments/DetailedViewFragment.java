package in.abmulani.aamadmiparty.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import in.abmulani.aamadmiparty.BaseApplication;
import in.abmulani.aamadmiparty.R;
import in.abmulani.aamadmiparty.datamodels.Result;
import in.abmulani.aamadmiparty.utils.CachedData;
import in.abmulani.aamadmiparty.utils.CommonMethods;
import in.abmulani.aamadmiparty.utils.LITERALS;
import in.abmulani.aamadmiparty.utils.URL_LIST;

public class DetailedViewFragment extends Fragment {

	public static final Fragment newInstance(int position, int type) {
		DetailedViewFragment f = new DetailedViewFragment();
		Bundle bdl = new Bundle(1);
		bdl.putInt("position", position);
		bdl.putInt("type", type);
		f.setArguments(bdl);
		return f;
	}

	private View parentView;
	private Result fullResult;
	private TextView titleTxt, timestampTxt, contentTxt;
	private ImageView imageView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fr_detailed_view, container, false);
		return parentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		titleTxt = (TextView) parentView.findViewById(R.id.fdv_title_txt);
		timestampTxt = (TextView) parentView.findViewById(R.id.fdv_created_on);
		contentTxt = (TextView) parentView.findViewById(R.id.fdv_main_content);

		imageView = (ImageView) parentView.findViewById(R.id.fdv_imageView);
		Bundle bund = getArguments();
		fullResult = getSelectedResult(bund.getInt("type"), bund.getInt("position"));
		DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.image_place_holder).showImageForEmptyUri(R.drawable.image_place_holder)
				.showImageOnFail(R.drawable.ic_launcher).resetViewBeforeLoading(false).cacheInMemory(true).cacheOnDisc(true).build();
		titleTxt.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (BaseApplication.screenHeight * 0.05));
		titleTxt.setText(fullResult.getTitle());
		String dateTime = fullResult.getCreated_on();
		timestampTxt.setText(CommonMethods.convertDateInfo(dateTime.substring(0, 10)));
		contentTxt.setText(fullResult.getContent());
		imageView.setLayoutParams(new RelativeLayout.LayoutParams((int) (BaseApplication.screenWidth*0.95), (int) (BaseApplication.screenWidth * 0.60)));
		ImageLoader.getInstance().displayImage(URL_LIST.BASE_URL + fullResult.getImage_url(), imageView, options);
		}

	private Result getSelectedResult(int selectedType, int position) {
		switch (selectedType) {
		case LITERALS.TYPE_HISTORY:
			return CachedData.HistoryList.get(position);
		case LITERALS.TYPE_ARVIND_KEJRIWAL:
			return CachedData.ArvindList.get(position);
		case LITERALS.TYPE_CAMPAIGN_INNOVATION:
			return CachedData.CampaignInovationList.get(position);
		case LITERALS.TYPE_CELEBRATIES:
			return CachedData.CelebratiesList.get(position);
		case LITERALS.TYPE_JOKES:
			return CachedData.JokesList.get(position);
		case LITERALS.TYPE_LEADERS:
			return CachedData.LeadersList.get(position);
		case LITERALS.TYPE_LOK_SABHA:
			return CachedData.LS_14List.get(position);
		case LITERALS.TYPE_PATH_BERAKING:
			return CachedData.PathBreakingList.get(position);
		case LITERALS.TYPE_POLICIES:
			return CachedData.PoliciesList.get(position);
		}
		return null;
	}

}
