package in.abmulani.aamadmiparty.datamodels;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ResultListVideo {

	@Expose
	private List<ResultVideo> result_Content = new ArrayList<ResultVideo>();

	public List<ResultVideo> getResultContent() {
		return result_Content;
	}

	public void setResultContent(List<ResultVideo> resultContent) {
		this.result_Content = resultContent;
	}

}
