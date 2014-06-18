package cherry.sqlapp.service.sqltool.metadata;

import java.util.List;

import cherry.spring.common.lib.paginate.PageSet;
import cherry.sqlapp.db.BaseDto;
import cherry.sqlapp.db.gen.dto.SqlMetadata;

public class Result extends BaseDto {

	private static final long serialVersionUID = 1L;

	private PageSet pageSet;

	private List<SqlMetadata> metadataList;

	public PageSet getPageSet() {
		return pageSet;
	}

	public void setPageSet(PageSet pageSet) {
		this.pageSet = pageSet;
	}

	public List<SqlMetadata> getMetadataList() {
		return metadataList;
	}

	public void setMetadataList(List<SqlMetadata> metadataList) {
		this.metadataList = metadataList;
	}

}