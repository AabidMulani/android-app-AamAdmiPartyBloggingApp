package in.abmulani.aamadmiparty.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.abmulani.aamadmiparty.R;
import in.abmulani.aamadmiparty.datamodels.MenuScreenModels;
import in.abmulani.aamadmiparty.utils.AppConstants;

/**
 * Created by AABID on 17/3/14.
 */
public class GridMenuScreen extends Fragment {

    View parentView;

    @InjectView(R.id.fgms_drag_grid_view)
    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fr_grid_menu_screen, container, false);
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
        gridView.setAdapter(new GridAdapter(getActivity()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent =new Intent(getActivity(), ListActivity.class);
//                intent.putExtra("selected",AppConstants.menuList.get(position).getCategory());
//                startActivity(intent);
            }
        });
    }


    private class GridAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public GridAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return AppConstants.menuList.size();
        }

        @Override
        public Object getItem(int i) {
            return AppConstants.menuList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = view;
            ImageView picture;
            TextView name;
            RelativeLayout layout;
            if (v == null) {
                v = inflater.inflate(R.layout.inflater_grid_layout, viewGroup, false);
                v.setTag(R.id.igl_image, v.findViewById(R.id.igl_image));
                v.setTag(R.id.igl_name, v.findViewById(R.id.igl_name));
                v.setTag(R.id.igl_layout, v.findViewById(R.id.igl_layout));
            }

            picture = (ImageView) v.getTag(R.id.igl_image);
            name = (TextView) v.getTag(R.id.igl_name);
            layout = (RelativeLayout) v.getTag(R.id.igl_layout);

            MenuScreenModels item = (MenuScreenModels) getItem(i);
            layout.setBackgroundDrawable(item.getColor());
            name.setText(item.getName());
            picture.setImageResource(item.getImage());
            return v;
        }
    }
}
