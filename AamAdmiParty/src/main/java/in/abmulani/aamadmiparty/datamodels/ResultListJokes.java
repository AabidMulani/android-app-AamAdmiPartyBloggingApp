package in.abmulani.aamadmiparty.datamodels;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ResultListJokes {

	@Expose
	private List<ResultJokes> resultContent = new ArrayList<ResultJokes>();

	public List<ResultJokes> getResultContent() {
		return resultContent;
	}

	public void setResultContent(List<ResultJokes> resultContent) {
		this.resultContent = resultContent;
	}

}
