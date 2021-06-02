package com.fptu.capstone.service.dto;

import javax.persistence.*;

@NamedNativeQuery(name  = "getAllStaffByPartnerId", 
				  query = " SELECT a.id, a.jhi_type, a.status, a.start_time, a.end_time, a.partner_id "
				  		+ " FROM staff AS a  LEFT JOIN partner AS b "
				  		+ " ON a.partner_id = b.id  WHERE partner_id = :partnerId", 
				  resultSetMapping = "constructor-map")
@SqlResultSetMapping(name = "constructor-map", 
	classes = @ConstructorResult(columns = {
			@ColumnResult(name = "id", 			type = Long.class),
			@ColumnResult(name = "jhi_type", 	type = String.class),
			@ColumnResult(name = "status", 		type = Boolean.class),
			@ColumnResult(name = "start_time", 	type = String.class),
			@ColumnResult(name = "end_time", 	type = Boolean.class),
			@ColumnResult(name = "partner_id", 	type = Long.class)
		}, targetClass = StaffDTO.class)
)

public class StaffDTO {

    private Long id;
    private String type;
    private String status;
    private Boolean startTime;
    private Boolean endTime;
    private Long partnerId;
    
	public StaffDTO(Long id, String type, String status, Boolean startTime, Boolean endTime, Long partnerId) {
		this.id = id;
		this.type = type;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.partnerId = partnerId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getStartTime() {
		return startTime;
	}
	public void setStartTime(Boolean startTime) {
		this.startTime = startTime;
	}
	public Boolean getEndTime() {
		return endTime;
	}
	public void setEndTime(Boolean endTime) {
		this.endTime = endTime;
	}
	public Long getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}
}
