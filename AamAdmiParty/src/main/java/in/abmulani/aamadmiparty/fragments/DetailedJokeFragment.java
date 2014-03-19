package in.abmulani.aamadmiparty.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.abmulani.aamadmiparty.R;
import in.abmulani.aamadmiparty.activities.DetailedViewActivity;
import in.abmulani.aamadmiparty.utils.AppConstants;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by AABID on 17/3/14.
 */
public class DetailedJokeFragment extends android.support.v4.app.Fragment {

    View parentView;

    @InjectView(R.id.fjl_text)
    TextView textView;

    @InjectView(R.id.fjl_photo_view)
    PhotoView imageView;

    public static final android.support.v4.app.Fragment newInstance(String imgUrl, String title, String time) {
        DetailedJokeFragment f = new DetailedJokeFragment();
        Bundle bdl = new Bundle();
        bdl.putString("imgUrl", imgUrl);
        bdl.putString("title", title);
        bdl.putString("time", time);
        f.setArguments(bdl);
        return f;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fr_jokes_layout, container, false);
        ButterKnife.inject(this, parentView);
        return parentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle=getArguments();
        imageView.setViewPagerListener(((DetailedViewActivity)getActivity()).pagerListener);
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.place_holder).showImageForEmptyUri(R.drawable.place_holder)
                .showImageOnFail(R.drawable.ic_launcher).resetViewBeforeLoading(false).cacheInMemory(true).cacheOnDisc(true).build();

        ImageLoader.getInstance().displayImage(AppConstants.IMAGE_BASE_URL + bundle.getString("imgUrl"), imageView, options);
        textView.setText(bundle.getString("title"));
    }

}
