package in.abmulani.aamadmiparty.datamodels;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ResultList {

	@Expose
	private List<Result> result = new ArrayList<Result>();

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

}
