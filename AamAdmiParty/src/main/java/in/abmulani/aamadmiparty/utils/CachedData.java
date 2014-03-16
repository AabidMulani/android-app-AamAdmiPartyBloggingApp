package in.abmulani.aamadmiparty.utils;

import java.util.ArrayList;
import java.util.List;

import in.abmulani.aamadmiparty.datamodels.Result;

public class CachedData {

	public static List<Result> ArvindList=new ArrayList<Result>();
	public static List<Result> HistoryList=new ArrayList<Result>();
	public static List<Result> CampaignInovationList=new ArrayList<Result>();
	public static List<Result> CelebratiesList=new ArrayList<Result>();
	public static List<Result> JokesList=new ArrayList<Result>();
	public static List<Result> LeadersList=new ArrayList<Result>();
	public static List<Result> LS_14List=new ArrayList<Result>();
	public static List<Result> PathBreakingList=new ArrayList<Result>();
	public static List<Result> PoliciesList=new ArrayList<Result>();
	
	
	public static void ClearCache(){
		ArvindList.clear();
		HistoryList.clear();
		CampaignInovationList.clear();
		CelebratiesList.clear();
		JokesList.clear();
		LeadersList.clear();
		LS_14List.clear();
		PathBreakingList.clear();
		PoliciesList.clear();
	}
}
