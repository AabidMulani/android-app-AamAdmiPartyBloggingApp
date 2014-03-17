package in.abmulani.aamadmiparty.datamodels;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ResultListVideo {

	@Expose
	private List<ResultVideo> resultContent = new ArrayList<ResultVideo>();

	public List<ResultVideo> getResultContent() {
		return resultContent;
	}

	public void setResultContent(List<ResultVideo> resultContent) {
		this.resultContent = resultContent;
	}

}
