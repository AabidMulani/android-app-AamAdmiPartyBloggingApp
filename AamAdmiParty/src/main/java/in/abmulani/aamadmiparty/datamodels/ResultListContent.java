package in.abmulani.aamadmiparty.datamodels;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ResultListContent {

	@Expose
	private List<ResultContent> result_Content = new ArrayList<ResultContent>();

	public List<ResultContent> getResultContent() {
		return result_Content;
	}

	public void setResultContent(List<ResultContent> resultContent) {
		this.result_Content = resultContent;
	}

}
