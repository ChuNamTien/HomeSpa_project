package com.fptu.capstone.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Staff.
 */
@Entity
@Table(name = "staff")
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "user_id", nullable = false)
//    private Long userId;
//
//    @Column(name = "partner_id", nullable = false)
//    private Long partnerId;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "start_time")
    private Float startTime;

    @Column(name = "end_time")
    private Float endTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;
    
    @ManyToMany(mappedBy = "staffs")
    private Set<Treatment> treatments = new HashSet<>();
    
    @OneToMany(mappedBy = "staff")
    private Set<BookingActivity> bookingActivities = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getUserId() {
//        return userId;
//    }
//
//    public Staff userId(Long userId) {
//        this.userId = userId;
//        return this;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public Long getPartnerId() {
//        return partnerId;
//    }
//
//    public Staff partnerId(Long partnerId) {
//        this.partnerId = partnerId;
//        return this;
//    }
//
//    public void setPartnerId(Long partnerId) {
//        this.partnerId = partnerId;
//    }

    public String getType() {
        return type;
    }

    public Staff type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public Staff status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getStartTime() {
        return startTime;
    }

    public Staff startTime(Float startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(Float startTime) {
        this.startTime = startTime;
    }

    public Float getEndTime() {
        return endTime;
    }

    public Staff endTime(Float endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(Float endTime) {
        this.endTime = endTime;
    }
    
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Set<Treatment> getTreatments() {
		return treatments;
	}

	public void setTreatments(Set<Treatment> treatments) {
		this.treatments = treatments;
	}

	public Set<BookingActivity> getBookingActivities() {
		return bookingActivities;
	}

	public void setBookingActivities(Set<BookingActivity> bookingActivities) {
		this.bookingActivities = bookingActivities;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Staff staff = (Staff) o;
        if (staff.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), staff.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Staff{" +
            "id=" + getId() +
//            ", userId=" + getUserId() +
//            ", partnerId=" + getPartnerId() +
            ", type='" + getType() + "'" +
            ", status='" + getStatus() + "'" +
            ", startTime=" + getStartTime() +
            ", endTime=" + getEndTime() +
            "}";
    }
}
