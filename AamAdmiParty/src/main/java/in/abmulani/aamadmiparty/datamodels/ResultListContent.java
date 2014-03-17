package in.abmulani.aamadmiparty.datamodels;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ResultListContent {

	@Expose
	private List<ResultContent> resultContent = new ArrayList<ResultContent>();

	public List<ResultContent> getResultContent() {
		return resultContent;
	}

	public void setResultContent(List<ResultContent> resultContent) {
		this.resultContent = resultContent;
	}

}
