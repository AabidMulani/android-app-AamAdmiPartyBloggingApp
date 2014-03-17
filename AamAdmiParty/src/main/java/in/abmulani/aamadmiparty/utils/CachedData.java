package in.abmulani.aamadmiparty.utils;

import java.util.ArrayList;
import java.util.List;

import in.abmulani.aamadmiparty.datamodels.ResultContent;
import in.abmulani.aamadmiparty.datamodels.ResultJokes;
import in.abmulani.aamadmiparty.datamodels.ResultVideo;

public class CachedData {

	public static List<ResultContent> arvindList=new ArrayList<ResultContent>();
	public static List<ResultContent> historyList=new ArrayList<ResultContent>();
	public static List<ResultContent> campaignInovationList=new ArrayList<ResultContent>();
	public static List<ResultContent> celebratiesList=new ArrayList<ResultContent>();
	public static List<ResultJokes> jokesList=new ArrayList<ResultJokes>();
	public static List<ResultContent> leadersList=new ArrayList<ResultContent>();
	public static List<ResultContent> ls_14List=new ArrayList<ResultContent>();
	public static List<ResultContent> pathBreakingList=new ArrayList<ResultContent>();
	public static List<ResultContent> policiesList=new ArrayList<ResultContent>();
    public static List<ResultVideo> videosList=new ArrayList<ResultVideo>();
	
	public static void ClearCache(){
		arvindList.clear();
		historyList.clear();
		campaignInovationList.clear();
		celebratiesList.clear();
		jokesList.clear();
		leadersList.clear();
		ls_14List.clear();
		pathBreakingList.clear();
		policiesList.clear();
	}
}
