package in.abmulani.aamadmiparty.fragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.abmulani.aamadmiparty.BaseApplication;
import in.abmulani.aamadmiparty.R;
import in.abmulani.aamadmiparty.activities.DetailedViewActivity;
import in.abmulani.aamadmiparty.utils.AppConstants;
import in.abmulani.aamadmiparty.utils.CommonMethods;
import in.abmulani.aamadmiparty.utils.Logger;

public class DetailedContentFragment extends Fragment {
    private final String TAG="DetailedContentFragment";

    public static final String SAVED_STATE_ACTION_BAR_HIDDEN = "saved_state_action_bar_hidden";
    private View parentView;
    @InjectView(R.id.fdv_title_txt)
    TextView titleTxt;
    @InjectView(R.id.fdv_created_on)
    TextView timestampTxt;
    @InjectView(R.id.fdv_main_content)
    TextView contentTxt;
    @InjectView(R.id.fdv_subheader)
    TextView subHeader;
    @InjectView(R.id.main_image_view)
    ImageView imageView;
    @InjectView(R.id.sliding_layout)
    SlidingUpPanelLayout layout;
    @InjectView(R.id.image_bk_layout)
    LinearLayout imageBkLayout;
    @InjectView(R.id.fdv_inner_scroll_view)
    ScrollView childScrollView;

    private ActionBar actionBar;

    public static final Fragment newInstance(String imgUrl, String title, String subHeader, String content, String time) {
        DetailedContentFragment f = new DetailedContentFragment();
        Bundle bdl = new Bundle();
        bdl.putString("imgUrl", imgUrl);
        bdl.putString("title", title);
        bdl.putString("subHeader", subHeader);
        bdl.putString("content", content);
        bdl.putString("time", time);
        f.setArguments(bdl);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fr_detail_content_view, container, false);
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
        Bundle bundle = getArguments();
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.place_holder).showImageForEmptyUri(R.drawable.place_holder)
                .showImageOnFail(R.drawable.ic_launcher).resetViewBeforeLoading(false).cacheInMemory(true).cacheOnDisc(true).build();
        titleTxt.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (BaseApplication.screenHeight * 0.04));
        titleTxt.setText(bundle.getString("title"));
        subHeader.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (BaseApplication.screenHeight * 0.03));
        subHeader.setText(bundle.getString("subHeader"));
        String dateTime = bundle.getString("time");
        timestampTxt.setText(CommonMethods.convertDateInfo(dateTime.substring(0, 10)));
        contentTxt.setText(bundle.getString("content"));
//        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams((int) (BaseApplication.screenWidth * 0.95), (int) (BaseApplication.screenWidth * 0.60));
//        imageView.setLayoutParams(params);
        ImageLoader.getInstance().displayImage(AppConstants.IMAGE_BASE_URL + bundle.getString("imgUrl"), imageView, options);

        actionBar=((DetailedViewActivity)getActivity()).getActionBar();
        layout.setShadowDrawable(getResources().getDrawable(R.drawable.above_shadow));
        layout.setEnableDragViewTouchEvents(true);
        layout.setAnchorPoint(0.4f);
        layout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Logger.i(TAG, "onPanelSlide, offset " + slideOffset);
                if (slideOffset < 0.01) {
                    if (actionBar.isShowing()) {
                        actionBar.hide();
                    }
                } else {
                    if (!actionBar.isShowing()) {
                        actionBar.show();
                    }
                }
                layout.invalidate();
                imageBkLayout.invalidate();
            }

            @Override
            public void onPanelExpanded(View panel) {
                Logger.i(TAG, "onPanelExpanded");
                layout.invalidate();
                imageBkLayout.invalidate();
            }

            @Override
            public void onPanelCollapsed(View panel) {
                Logger.i(TAG, "onPanelCollapsed");
                layout.invalidate();
                imageBkLayout.invalidate();
            }

            @Override
            public void onPanelAnchored(View panel) {
                Logger.i(TAG, "onPanelAnchored");
                layout.invalidate();
                imageBkLayout.invalidate();
            }
        });

        boolean actionBarHidden = savedInstanceState != null ?
                savedInstanceState.getBoolean(SAVED_STATE_ACTION_BAR_HIDDEN, false): false;
        if (actionBarHidden) {
            actionBar.hide();
        }

//        childScrollView.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.v(TAG, "CHILD TOUCH");
//
//                return false;
//            }
//        });
    }


}

//


