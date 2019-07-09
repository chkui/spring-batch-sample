package org.chenkui.spring.batch.sample.entity;

//数据实体
public class WeatherEntity {
	public enum Type {
		PRCP, SNOW, WESF, TMAX, TMIN, TAVG, SNWD, AWND, PGTM, WDF2, WDF5, WSF2, WSF5, TOBS, WESD, WT01, WT08, SN52,
		SX52, SN32, SX32, SX55, SN31, WSFG, WT06, SX33, WDFG, MDTX, MXPN, WT11, SX53, WSFI, DAPR, WT02, AWDR, EVAP,
		MNPN, SN51, SX51, SX31, DATN, MDTN, WDMV, WT04, DATX, SN53, SN55, SN33, MDPR, TSUN, THIC
	}

	private String siteId;
	private String month;
	private Type type;
	private Integer value;
	private String ext;

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@Override
	public String toString() {
		return "WeatherObserve [siteId=" + siteId + ", month=" + month + ", type=" + type + ", value=" + value + "]";
	}
}
