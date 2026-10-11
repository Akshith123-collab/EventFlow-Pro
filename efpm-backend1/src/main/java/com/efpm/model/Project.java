package com.efpm.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "projects")
@Data @NoArgsConstructor @AllArgsConstructor
public class Project {
    @Id
    private String id;
    private String fundingAgency;
    private String duration;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String principalInvestigator;
    private String coPrincipalInvestigator;
    private String academicYear;
    private Double amountSanctioned;
    private String agreementFilePath;
    private String jrfDetails;
    private String summary;
    private List<ProjectStatus> statusList = new ArrayList<>();
	public String getFundingAgency() {
		return fundingAgency;
	}
	public void setFundingAgency(String fundingAgency) {
		this.fundingAgency = fundingAgency;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrincipalInvestigator() {
		return principalInvestigator;
	}
	public void setPrincipalInvestigator(String principalInvestigator) {
		this.principalInvestigator = principalInvestigator;
	}
	public String getCoPrincipalInvestigator() {
		return coPrincipalInvestigator;
	}
	public void setCoPrincipalInvestigator(String coPrincipalInvestigator) {
		this.coPrincipalInvestigator = coPrincipalInvestigator;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public Double getAmountSanctioned() {
		return amountSanctioned;
	}
	public void setAmountSanctioned(Double amountSanctioned) {
		this.amountSanctioned = amountSanctioned;
	}
	public String getAgreementFilePath() {
		return agreementFilePath;
	}
	public void setAgreementFilePath(String agreementFilePath) {
		this.agreementFilePath = agreementFilePath;
	}
	public String getJrfDetails() {
		return jrfDetails;
	}
	public void setJrfDetails(String jrfDetails) {
		this.jrfDetails = jrfDetails;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public List<ProjectStatus> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<ProjectStatus> statusList) {
		this.statusList = statusList;
	}
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}

    // lombok generates getters/setters; explicit methods are not required
}
