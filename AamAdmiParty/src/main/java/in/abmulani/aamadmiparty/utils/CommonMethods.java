package in.abmulani.aamadmiparty.utils;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import in.abmulani.aamadmiparty.R;
import in.abmulani.aamadmiparty.utils.LITERALS.CATEGORY;

public class CommonMethods {

    public static ProgressDialog pd;

    public static void showToast(Activity applicationContext, String message) {
        // LayoutInflater inflater = applicationContext.getLayoutInflater();
        // View layout = inflater.inflate(R.layout.custom_toast_inflater, null);
        //
        // TextView text = (TextView) layout.findViewById(R.id.cti_txtview);
        // text.setText(message);
        // Toast toast = new Toast(applicationContext);
        // toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        // toast.setDuration(Toast.LENGTH_SHORT);
        // toast.setView(layout);
        // toast.setText(message);
        // toast.show();
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show();
    }

    public static boolean checkInternetConnection(Context mContext) {
        boolean retVal = false;
        try {
            ConnectivityManager conMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
                retVal = true;
            } else {
                retVal = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    public static void ShowThisMsg(Context context, String Header, String Msg) {
        final Builder dialog = new Builder(context);
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.setTitle(Header + ":");
        dialog.setMessage(Msg);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @SuppressWarnings("rawtypes")
    public static void ShowProgressDialog(Context context, String Msg, boolean cancleable, final AsyncTask task) {
        pd = new ProgressDialog(context);
        pd.setCancelable(cancleable);
        pd.setIndeterminate(true);
        pd.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface arg0) {
                task.cancel(true);
            }
        });
        pd.setMessage(Msg);
        pd.getWindow().addFlags(Window.FEATURE_NO_TITLE);
        pd.show();
    }

    public static void HideProgressBar() {
        if (pd.isShowing()) {
            pd.dismiss();
        }
    }

//    public static String getName(int type) {
//        switch (type) {
//            case LITERALS.TYPE_HISTORY:
//                return "HISTORY";
//            case LITERALS.TYPE_ARVIND_KEJRIWAL:
//                return "ARVIND KEJRIWAL";
//            case LITERALS.TYPE_CAMPAIGN_INNOVATION:
//                return "CAMPAIGN INNOVATION";
//            case LITERALS.TYPE_CELEBRATIES:
//                return "CELEBRATIES";
//            case LITERALS.TYPE_JOKES:
//                return "JOKES";
//            case LITERALS.TYPE_LEADERS:
//                return "LEADERS";
//            case LITERALS.TYPE_LOK_SABHA:
//                return "LOK SABHA 2014";
//            case LITERALS.TYPE_PATH_BERAKING:
//                return "PATH BERAKING";
//            case LITERALS.TYPE_POLICIES:
//                return "POLICIES";
//        }
//        return "ERROR";
//    }

    public static int getColor(CATEGORY type, Resources resources) {
        switch (type) {
            case History:
                return resources.getColor(R.color.history_theme_color);
            case ArvindKejriwal:
                return resources.getColor(R.color.arvind_theme_color);
            case CampaignInnovation:
                return resources.getColor(R.color.camp_innovation_theme_color);
            case AapCelebs:
                return resources.getColor(R.color.aap_celebs_theme_color);
            case Jokes:
                return resources.getColor(R.color.jokes_theme_color);
            case Leaders:
                return resources.getColor(R.color.leaders_theme_color);
            case LokSabha2014:
                return resources.getColor(R.color.ls_14_theme_color);
            case PathBreakingNews:
                return resources.getColor(R.color.path_b_news_theme_color);
            case Policies:
                return resources.getColor(R.color.policies_theme_color);
            case Videos:
                return resources.getColor(R.color.videos_theme_color);
        }
        return resources.getColor(R.color.tab_menu_color);
    }

    public static String convertDateInfo(String date) {
        SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        java.util.Date d1 = null;
        Calendar cal = Calendar.getInstance();
        try {
            d = dfDate.parse(date);
            Log.e("DAYS: ", d + "");
            d1 = dfDate.parse(dfDate.format(cal.getTime()));//Returns 15/10/2012
            Log.e("DAYS 1: ", d1 + "");
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        int diffInDays = (int) ((d1.getTime() - d.getTime()) / (1000 * 60 * 60 * 24));
        Log.e("DAYS DIFFERENCE: ", diffInDays + "");
        if (diffInDays == 0)
            return "Today";
        if (diffInDays == 1)
            return "Yesterday";
        if (diffInDays < 31)
            return diffInDays + " days ago";
        if (diffInDays < 60)
            return "1 month ago";
        if (diffInDays < 365)
            return (diffInDays / 30) + " months ago";
        if (diffInDays < 730)
            return "1 year ago";
        return (diffInDays / 30) + " year ago";
    }

}
