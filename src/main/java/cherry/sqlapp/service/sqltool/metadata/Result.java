package cherry.sqlapp.service.sqltool.metadata;

import java.util.List;

import cherry.spring.common.lib.paginate.PageSet;
import cherry.sqlapp.db.BaseDto;
import cherry.sqlapp.db.gen.dto.SqltoolMetadata;

public class Result extends BaseDto {

	private static final long serialVersionUID = 1L;

	private PageSet pageSet;

	private List<SqltoolMetadata> metadataList;

	public PageSet getPageSet() {
		return pageSet;
	}

	public void setPageSet(PageSet pageSet) {
		this.pageSet = pageSet;
	}

	public List<SqltoolMetadata> getMetadataList() {
		return metadataList;
	}

	public void setMetadataList(List<SqltoolMetadata> metadataList) {
		this.metadataList = metadataList;
	}

}