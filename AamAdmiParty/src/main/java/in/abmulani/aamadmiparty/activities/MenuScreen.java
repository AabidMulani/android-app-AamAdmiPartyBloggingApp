package in.abmulani.aamadmiparty.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import in.abmulani.aamadmiparty.R;
import in.abmulani.aamadmiparty.utils.CachedData;
import in.abmulani.aamadmiparty.utils.LITERALS;

public class MenuScreen extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_screen);
		findViewById(R.id.ams_aap_celebs_layout).setOnClickListener(this);
		findViewById(R.id.ams_arvind_k_layout).setOnClickListener(this);
		findViewById(R.id.ams_campaign_inv_layout).setOnClickListener(this);
		findViewById(R.id.ams_history_layout).setOnClickListener(this);
		findViewById(R.id.ams_jokes_layout).setOnClickListener(this);
		findViewById(R.id.ams_leaders_layout).setOnClickListener(this);
		findViewById(R.id.ams_lok_sabha14_layout).setOnClickListener(this);
		findViewById(R.id.ams_path_bk_layout).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		CachedData.ClearCache();
		Intent intent = new Intent(MenuScreen.this, List_ArvindKejriwal.class);
		switch (view.getId()) {
		case R.id.ams_aap_celebs_layout:
			intent.putExtra("selected", LITERALS.TYPE_CELEBRATIES);
			break;
		case R.id.ams_arvind_k_layout:
			intent.putExtra("selected", LITERALS.TYPE_ARVIND_KEJRIWAL);
			break;
		case R.id.ams_campaign_inv_layout:
			intent.putExtra("selected", LITERALS.TYPE_CAMPAIGN_INNOVATION);
			break;
		case R.id.ams_history_layout:
			intent.putExtra("selected", LITERALS.TYPE_HISTORY);
			break;
		case R.id.ams_jokes_layout:
			intent.putExtra("selected", LITERALS.TYPE_JOKES);
			break;
		case R.id.ams_leaders_layout:
			intent.putExtra("selected", LITERALS.TYPE_LEADERS);
			break;
		case R.id.ams_lok_sabha14_layout:
			intent.putExtra("selected", LITERALS.TYPE_LOK_SABHA);
			break;
		case R.id.ams_path_bk_layout:
			intent.putExtra("selected", LITERALS.TYPE_PATH_BERAKING);
			break;
		default:
			break;
		}
		startActivity(intent);
	}

}
