package com.technicalyorker.ecomm.model;

public class Summary {
	private String version;
	private Long dbRecs;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Long getDbRecs() {
		return dbRecs;
	}

	public void setDbRecs(Long dbRecs) {
		this.dbRecs = dbRecs;
	}

	@Override
	public String toString() {
		return "Summary [version=" + version + ", dbRecs=" + dbRecs + "]";
	}
}
