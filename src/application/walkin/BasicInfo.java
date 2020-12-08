package application.walkin;

public class BasicInfo {
	private String reportDate;
	private String companyName;
	private String companyAddress;
	private String companyCity;
	private String companyProvince;
	private String companyPostalCode;
	private String clientName;
	private String projectNumber;
	private String projectAddress;
	private String projectCity;
	private String projectProvince;
	private String samplingDate;
	
	public BasicInfo() {
		this.setReportDate("");
		this.setCompanyName("");
		this.setCompanyAddress("");
		this.setCompanyCity("");
		this.setCompanyProvince("");
		this.setCompanyPostalCode("");
		this.setClientName("");
		this.setProjectNumber("");
		this.setProjectAddress("");
		this.setProjectCity("");
		this.setProjectProvince("");
		this.setSamplingDate("");
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCity() {
		return companyCity;
	}

	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyProvince() {
		return companyProvince;
	}

	public void setCompanyProvince(String companyProvince) {
		this.companyProvince = companyProvince;
	}

	public String getCompanyPostalCode() {
		return companyPostalCode;
	}

	public void setCompanyPostalCode(String companyPostalCode) {
		this.companyPostalCode = companyPostalCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public String getProjectCity() {
		return projectCity;
	}

	public void setProjectCity(String projectCity) {
		this.projectCity = projectCity;
	}

	public String getProjectProvince() {
		return projectProvince;
	}

	public void setProjectProvince(String projectProvince) {
		this.projectProvince = projectProvince;
	}

	public String getSamplingDate() {
		return samplingDate;
	}

	public void setSamplingDate(String samplingDate) {
		this.samplingDate = samplingDate;
	}
	
	public void copyAddresses() {
		this.projectAddress = this.companyAddress;
		this.projectCity = this.companyCity;
		this.projectProvince = this.companyProvince;
	}
	
}
