package in.abmulani.aamadmiparty.datamodels;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ResultListJokes {

	@Expose
	private List<ResultJokes> result_Content = new ArrayList<ResultJokes>();

	public List<ResultJokes> getResultContent() {
		return result_Content;
	}

	public void setResultContent(List<ResultJokes> resultContent) {
		this.result_Content = resultContent;
	}

}
