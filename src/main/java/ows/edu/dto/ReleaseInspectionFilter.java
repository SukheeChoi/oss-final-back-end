package ows.edu.dto;

import lombok.Data;

@Data
public class ReleaseInspectionFilter {
	private String[] emptyGroup;
	private int pageSize;
	private int pageNo;
}
