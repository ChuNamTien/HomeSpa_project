package com.fptu.capstone.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

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

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "start_time")
    private Boolean startTime;

    @Column(name = "end_time")
    private Boolean endTime;

    @ManyToMany
    @JoinTable(name = "staff_category",
               joinColumns = @JoinColumn(name = "staff_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "categories_id", referencedColumnName = "id"))
    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "staff_treatment",
               joinColumns = @JoinColumn(name = "staff_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "treatments_id", referencedColumnName = "id"))
    private Set<Treatment> treatments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("staff")
    private Partner partner;
    
    @OneToOne    @JoinColumn(unique = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getStartTime() {
		return startTime;
	}

	public Boolean getEndTime() {
		return endTime;
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

    public Boolean isStartTime() {
        return startTime;
    }

    public Staff startTime(Boolean startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(Boolean startTime) {
        this.startTime = startTime;
    }

    public Boolean isEndTime() {
        return endTime;
    }

    public Staff endTime(Boolean endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(Boolean endTime) {
        this.endTime = endTime;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Staff categories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Staff addCategory(Category category) {
        this.categories.add(category);
        category.getStaff().add(this);
        return this;
    }

    public Staff removeCategory(Category category) {
        this.categories.remove(category);
        category.getStaff().remove(this);
        return this;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Treatment> getTreatments() {
        return treatments;
    }

    public Staff treatments(Set<Treatment> treatments) {
        this.treatments = treatments;
        return this;
    }

    public Staff addTreatment(Treatment treatment) {
        this.treatments.add(treatment);
        treatment.getStaff().add(this);
        return this;
    }

    public Staff removeTreatment(Treatment treatment) {
        this.treatments.remove(treatment);
        treatment.getStaff().remove(this);
        return this;
    }

    public void setTreatments(Set<Treatment> treatments) {
        this.treatments = treatments;
    }

    public Partner getPartner() {
        return partner;
    }

    public Staff partner(Partner partner) {
        this.partner = partner;
        return this;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

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
            ", type='" + getType() + "'" +
            ", status='" + getStatus() + "'" +
            ", startTime='" + isStartTime() + "'" +
            ", endTime='" + isEndTime() + "'" +
            "}";
    }
}
