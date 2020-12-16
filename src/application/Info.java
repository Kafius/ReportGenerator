package application;

public class Info {
	private String projectNumber; //done
	private String projectName; //done
	private String projectAddress; //done
	private String projectCity; //done
	private String projectProvince; //done
	private String projectPostalCode; //done
	private String technician; //done
	private String projectManager; //done
	private String clientName; //done
	private String clientPosition;
	private String companyName; //done
	private String companyAddress; //done
	private String companyCity; //done
	private String companyProvince; //done
	private String companyPostalCode; //done
	private String buildingName; //done
	private String specificLocation; //done
	private String siteWorkDate; //done
	private String reportDate; //done
	private String preAbatementStartDate; //done
	private String visualAbatementStart; //done
	private String visualAbatementEnd; //done
	private String postAbatementDate; //done
	private String siteEndDate; //done
	private String selRep; //done
	private String onSiteTime; //done
	private String clientAddress; //done
	private String clientCity; //done
	private String clientProvince; //done
	private String clientPostalCode; //done
	private String locationOfAirSamples;
	private String inspectionStartDate; //done
	private String samplingDate; //done

	private boolean projectNumberExist; //done
	private boolean projectNameExist; //done
	private boolean projectAddressExist; //done
	private boolean projectCityExist; //done
	private boolean projectProvinceExist; //done
	private boolean projectPostalCodeExist; //done
	private boolean technicianExist; //done
	private boolean projectManagerExist; //done
	private boolean clientNameExist; //done
	private boolean clientPositionExist;
	private boolean companyNameExist; //done
	private boolean companyAddressExist; //done
	private boolean companyCityExist; //done
	private boolean companyProvinceExist; //done
	private boolean companyPostalCodeExist; //done
	private boolean buildingNameExist; //done
	private boolean specificLocationExist; //done
	private boolean siteWorkDateExist; //done
	private boolean reportDateExist; //done
	private boolean preAbatementStartDateExist; //done
	private boolean visualAbatementStartExist; //done
	private boolean visualAbatementEndExist; //done
	private boolean postAbatementDateExist; //done
	private boolean siteEndDateExist; //done
	private boolean selRepExist; //done
	private boolean onSiteTimeExist; //done
	private boolean clientAddressExist; //done
	private boolean clientCityExist; //done
	private boolean clientProvinceExist; //done
	private boolean clientPostalCodeExist; //done
	private boolean locationOfAirSamplesExist;
	private boolean inspectionStartDateExist; //done
	private boolean samplingDateExist; //done


	public Info(boolean projectNumberExist,
			boolean projectNameExist,
			boolean projectAddressExist,
			boolean projectCityExist,
			boolean projectProvinceExist,
			boolean projectPostalCodeExist,
			boolean technicianExist,
			boolean projectManagerExist,
			boolean clientNameExist,
			boolean clientPositionExist,
			boolean companyNameExist,
			boolean companyAddressExist,
			boolean companyCityExist,
			boolean companyProvinceExist,
			boolean companyPostalCodeExist,
			boolean buildingNameExist,
			boolean specificLocationExist,
			boolean siteWorkDateExist,
			boolean reportDateExist,
			boolean preAbatementStartDateExist,
			boolean visualAbatementStartExist,
			boolean visualAbatementEndExist,
			boolean postAbatementDateExist,
			boolean siteEndDateExist,
			boolean selRepExist,
			boolean onSiteTimeExist,
			boolean clientAddressExist,
			boolean clientCityExist,
			boolean clientProvinceExist,
			boolean clientPostalCodeExist,
			boolean locationOfAirSamplesExist,
			boolean inspectionStartDateExist,
			boolean samplingDateExist ) {

		this.projectNumberExist = projectNumberExist;
		this.projectNameExist = projectNameExist;
		this.projectAddressExist = projectAddressExist;
		this.projectCityExist = projectCityExist;
		this.projectProvinceExist = projectProvinceExist;
		this.projectPostalCodeExist = projectPostalCodeExist;
		this.technicianExist = technicianExist;
		this.projectManagerExist = projectManagerExist;
		this.clientNameExist = clientNameExist;
		this.clientPositionExist = clientPositionExist;
		this.companyNameExist = companyNameExist;
		this.companyAddressExist = companyAddressExist;
		this.companyCityExist = companyCityExist;
		this.companyProvinceExist = companyProvinceExist;
		this.companyPostalCodeExist = companyPostalCodeExist;
		this.buildingNameExist = buildingNameExist;
		this.specificLocationExist = specificLocationExist;
		this.siteWorkDateExist = siteWorkDateExist;
		this.reportDateExist = reportDateExist;
		this.preAbatementStartDateExist = preAbatementStartDateExist;
		this.visualAbatementStartExist = visualAbatementStartExist;
		this.visualAbatementEndExist = visualAbatementEndExist;
		this.postAbatementDateExist = postAbatementDateExist;
		this.siteEndDateExist = siteEndDateExist;
		this.selRepExist = selRepExist;
		this.onSiteTimeExist = onSiteTimeExist;
		this.clientAddressExist = clientAddressExist;
		this.clientCityExist = clientCityExist;
		this.clientProvinceExist = clientProvinceExist;
		this.clientPostalCodeExist = clientPostalCodeExist;
		this.locationOfAirSamplesExist = locationOfAirSamplesExist;
		this.inspectionStartDateExist = inspectionStartDateExist;
		this.samplingDateExist = samplingDateExist;
	}
	public Info() {

		this.projectNumberExist=false; //done
		this.projectNameExist=false; //done
		this.projectAddressExist=false; //done
		this.projectCityExist=false; //done
		this.projectProvinceExist=false; //done
		this.projectPostalCodeExist=false; //done
		this.technicianExist=false; //done
		this.projectManagerExist=false; //done
		this.clientNameExist=false; //done
		this.clientPositionExist=false;
		this.companyNameExist=false; //done
		this.companyAddressExist=false; //done
		this.companyCityExist=false; //done
		this.companyProvinceExist=false; //done
		this.companyPostalCodeExist=false; //done
		this.buildingNameExist=false; //done
		this.specificLocationExist=false; //done
		this.siteWorkDateExist=false; //done
		this.reportDateExist=false; //done
		this.preAbatementStartDateExist=false; //done
		this.visualAbatementStartExist=false; //done
		this.visualAbatementEndExist=false; //done
		this.postAbatementDateExist=false; //done
		this.siteEndDateExist=false; //done
		this.selRepExist=false; //done
		this.onSiteTimeExist=false; //done
		this.clientAddressExist=false; //done
		this.clientCityExist=false; //done
		this.clientProvinceExist=false; //done
		this.clientPostalCodeExist=false; //done
		this.locationOfAirSamplesExist=false;
		this.inspectionStartDateExist=false; //done
		this.samplingDateExist=false; //done

		this.projectNumber="";
		this.projectName="";
		this.projectAddress="";
		this.projectCity="";
		this.projectProvince="";
		this.projectPostalCode="";
		this.technician="";
		this.projectManager="";
		this.clientName="";
		this.clientPosition="";
		this.companyName="";
		this.companyAddress="";
		this.companyCity="";
		this.companyProvince="";
		this.companyPostalCode="";
		this.buildingName="";
		this.specificLocation="";
		this.siteWorkDate="";
		this.reportDate="";
		this.preAbatementStartDate="";
		this.visualAbatementStart="";
		this.visualAbatementEnd="";
		this.postAbatementDate="";
		this.siteEndDate="";
		this.selRep="";
		this.onSiteTime="";
		this.clientAddress="";
		this.clientCity="";
		this.clientProvince="";
		this.clientPostalCode="";
		this.locationOfAirSamples="";
		this.inspectionStartDate="";
		this.samplingDate="";
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public String getProjectPostalCode() {
		return projectPostalCode;
	}

	public void setProjectPostalCode(String projectPostalCode) {
		this.projectPostalCode = projectPostalCode;
	}

	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientPosition() {
		return clientPosition;
	}

	public void setClientPosition(String clientPosition) {
		this.clientPosition = clientPosition;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyCity() {
		return companyCity;
	}

	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
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

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getSpecificLocation() {
		return specificLocation;
	}

	public void setSpecificLocation(String specificLocation) {
		this.specificLocation = specificLocation;
	}

	public String getSiteWorkDate() {
		return siteWorkDate;
	}

	public void setSiteWorkDate(String siteWorkDate) {
		this.siteWorkDate = siteWorkDate;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getPreAbatementStartDate() {
		return preAbatementStartDate;
	}

	public void setPreAbatementStartDate(String preAbatementStartDate) {
		this.preAbatementStartDate = preAbatementStartDate;
	}

	public String getVisualAbatementStart() {
		return visualAbatementStart;
	}

	public void setVisualAbatementStart(String visualAbatementStart) {
		this.visualAbatementStart = visualAbatementStart;
	}

	public String getVisualAbatementEnd() {
		return visualAbatementEnd;
	}

	public void setVisualAbatementEnd(String visualAbatementEnd) {
		this.visualAbatementEnd = visualAbatementEnd;
	}

	public String getPostAbatementDate() {
		return postAbatementDate;
	}

	public void setPostAbatementDate(String postAbatementDate) {
		this.postAbatementDate = postAbatementDate;
	}

	public boolean isProjectNumberExist() {
		return projectNumberExist;
	}

	public void setProjectNumberExist(boolean projectNumberExist) {
		this.projectNumberExist = projectNumberExist;
	}

	public boolean isProjectNameExist() {
		return projectNameExist;
	}

	public void setProjectNameExist(boolean projectNameExist) {
		this.projectNameExist = projectNameExist;
	}

	public boolean isProjectAddressExist() {
		return projectAddressExist;
	}

	public void setProjectAddressExist(boolean projectAddressExist) {
		this.projectAddressExist = projectAddressExist;
	}

	public boolean isProjectCityExist() {
		return projectCityExist;
	}

	public void setProjectCityExist(boolean projectCityExist) {
		this.projectCityExist = projectCityExist;
	}

	public boolean isProjectProvinceExist() {
		return projectProvinceExist;
	}

	public void setProjectProvinceExist(boolean projectProvinceExist) {
		this.projectProvinceExist = projectProvinceExist;
	}

	public boolean isProjectPostalCodeExist() {
		return projectPostalCodeExist;
	}

	public void setProjectPostalCodeExist(boolean projectPostalCodeExist) {
		this.projectPostalCodeExist = projectPostalCodeExist;
	}

	public boolean isTechnicianExist() {
		return technicianExist;
	}

	public void setTechnicianExist(boolean technicianExist) {
		this.technicianExist = technicianExist;
	}

	public boolean isProjectManagerExist() {
		return projectManagerExist;
	}

	public void setProjectManagerExist(boolean projectManagerExist) {
		this.projectManagerExist = projectManagerExist;
	}

	public boolean isClientNameExist() {
		return clientNameExist;
	}

	public void setClientNameExist(boolean clientNameExist) {
		this.clientNameExist = clientNameExist;
	}

	public boolean isClientPositionExist() {
		return clientPositionExist;
	}

	public void setClientPositionExist(boolean clientPositionExist) {
		this.clientPositionExist = clientPositionExist;
	}

	public boolean isCompanyNameExist() {
		return companyNameExist;
	}

	public void setCompanyNameExist(boolean companyNameExist) {
		this.companyNameExist = companyNameExist;
	}

	public boolean isCompanyAddressExist() {
		return companyAddressExist;
	}

	public void setCompanyAddressExist(boolean companyAddressExist) {
		this.companyAddressExist = companyAddressExist;
	}

	public boolean isCompanyCityExist() {
		return companyCityExist;
	}

	public void setCompanyCityExist(boolean companyCityExist) {
		this.companyCityExist = companyCityExist;
	}

	public boolean isCompanyProvinceExist() {
		return companyProvinceExist;
	}

	public void setCompanyProvinceExist(boolean companyProvinceExist) {
		this.companyProvinceExist = companyProvinceExist;
	}

	public boolean isCompanyPostalCodeExist() {
		return companyPostalCodeExist;
	}

	public void setCompanyPostalCodeExist(boolean companyPostalCodeExist) {
		this.companyPostalCodeExist = companyPostalCodeExist;
	}

	public boolean isBuildingNameExist() {
		return buildingNameExist;
	}

	public void setBuildingNameExist(boolean buildingNameExist) {
		this.buildingNameExist = buildingNameExist;
	}

	public boolean isSpecificLocationExist() {
		return specificLocationExist;
	}

	public void setSpecificLocationExist(boolean specificLocationExist) {
		this.specificLocationExist = specificLocationExist;
	}

	public boolean isSiteWorkDateExist() {
		return siteWorkDateExist;
	}

	public void setSiteWorkDateExist(boolean siteWorkDateExist) {
		this.siteWorkDateExist = siteWorkDateExist;
	}

	public boolean isReportDateExist() {
		return reportDateExist;
	}

	public void setReportDateExist(boolean reportDateExist) {
		this.reportDateExist = reportDateExist;
	}

	public boolean isPreAbatementStartDateExist() {
		return preAbatementStartDateExist;
	}

	public void setPreAbatementStartDateExist(boolean preAbatementStartDateExist) {
		this.preAbatementStartDateExist = preAbatementStartDateExist;
	}

	public boolean isVisualAbatementStartExist() {
		return visualAbatementStartExist;
	}

	public void setVisualAbatementStartExist(boolean visualAbatementStartExist) {
		this.visualAbatementStartExist = visualAbatementStartExist;
	}

	public boolean isVisualAbatementEndExist() {
		return visualAbatementEndExist;
	}

	public void setVisualAbatementEndExist(boolean visualAbatementEndExist) {
		this.visualAbatementEndExist = visualAbatementEndExist;
	}

	public boolean isPostAbatementDateExist() {
		return postAbatementDateExist;
	}

	public void setPostAbatementDateExist(boolean postAbatementDateExist) {
		this.postAbatementDateExist = postAbatementDateExist;
	}

	public boolean isSiteEndDateExist() {
		return siteEndDateExist;
	}

	public void setSiteEndDateExist(boolean siteEndDateExist) {
		this.siteEndDateExist = siteEndDateExist;
	}

	public boolean isSelRepExist() {
		return selRepExist;
	}

	public void setSelRepExist(boolean selRepExist) {
		this.selRepExist = selRepExist;
	}

	public boolean isOnSiteTimeExist() {
		return onSiteTimeExist;
	}

	public void setOnSiteTimeExist(boolean onSiteTimeExist) {
		this.onSiteTimeExist = onSiteTimeExist;
	}

	public boolean isClientAddressExist() {
		return clientAddressExist;
	}

	public void setClientAddressExist(boolean clientAddressExist) {
		this.clientAddressExist = clientAddressExist;
	}

	public boolean isClientCityExist() {
		return clientCityExist;
	}

	public void setClientCityExist(boolean clientCityExist) {
		this.clientCityExist = clientCityExist;
	}

	public boolean isClientProvinceExist() {
		return clientProvinceExist;
	}

	public void setClientProvinceExist(boolean clientProvinceExist) {
		this.clientProvinceExist = clientProvinceExist;
	}

	public boolean isClientPostalCodeExist() {
		return clientPostalCodeExist;
	}

	public void setClientPostalCodeExist(boolean clientPostalCodeExist) {
		this.clientPostalCodeExist = clientPostalCodeExist;
	}

	public boolean isLocationOfAirSamplesExist() {
		return locationOfAirSamplesExist;
	}

	public void setLocationOfAirSamplesExist(boolean locationOfAirSamplesExist) {
		this.locationOfAirSamplesExist = locationOfAirSamplesExist;
	}

	public boolean isInspectionStartDateExist() {
		return inspectionStartDateExist;
	}

	public void setInspectionStartDateExist(boolean inspectionStartDateExist) {
		this.inspectionStartDateExist = inspectionStartDateExist;
	}

	public boolean isSamplingDateExist() {
		return samplingDateExist;
	}

	public void setSamplingDateExist(boolean samplingDateExist) {
		this.samplingDateExist = samplingDateExist;
	}

	public String getSiteEndDate() {
		return siteEndDate;
	}

	public void setSiteEndDate(String siteEndDate) {
		this.siteEndDate = siteEndDate;
	}

	public String getSelRep() {
		return selRep;
	}

	public void setSelRep(String selRep) {
		this.selRep = selRep;
	}


	public String getOnSiteTime() {
		return onSiteTime;
	}

	public void setOnSiteTime(String onSiteTime) {
		this.onSiteTime = onSiteTime;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientCity() {
		return clientCity;
	}

	public void setClientCity(String clientCity) {
		this.clientCity = clientCity;
	}

	public String getClientProvince() {
		return clientProvince;
	}

	public void setClientProvince(String clientProvince) {
		this.clientProvince = clientProvince;
	}

	public String getClientPostalCode() {
		return clientPostalCode;
	}

	public void setClientPostalCode(String clientPostalCode) {
		this.clientPostalCode = clientPostalCode;
	}

	public String getLocationOfAirSamples() {
		return locationOfAirSamples;
	}

	public void setLocationOfAirSamples(String locationOfAirSamples) {
		this.locationOfAirSamples = locationOfAirSamples;
	}

	public String getInspectionStartDate() {
		return inspectionStartDate;
	}

	public void setInspectionStartDate(String inspectionStartDate) {
		this.inspectionStartDate = inspectionStartDate;
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
