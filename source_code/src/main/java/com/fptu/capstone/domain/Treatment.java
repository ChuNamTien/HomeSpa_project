package com.fptu.capstone.domain;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Treatment.
 */
@Entity
@Table(name = "treatment")
public class Treatment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "duration")
    private Float duration;

    @Column(name = "price")
    private Float price;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "description")
    private String description;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "last_modified_by")
    private Instant lastModifiedBy;

    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;
    
    @ManyToMany
    @JsonIgnoreProperties("staff")
    private Set<Staff> staff = new HashSet<>();
    
    @ManyToOne
    @JsonIgnoreProperties("treatment")
    private Serv serv;
    
    

    public Set<Staff> getStaff() {
		return staff;
	}

	public void setStaff(Set<Staff> staff) {
		this.staff = staff;
	}

	
	
	public Serv getServ() {
		return serv;
	}

	public void setServ(Serv serv) {
		this.serv = serv;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public Treatment serviceId(Long serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public Treatment name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public Treatment status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getDuration() {
        return duration;
    }

    public Treatment duration(Float duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Float getPrice() {
        return price;
    }

    public Treatment price(Float price) {
        this.price = price;
        return this;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getDiscount() {
        return discount;
    }

    public Treatment discount(Float discount) {
        this.discount = discount;
        return this;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public Treatment description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Treatment createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Treatment createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Treatment lastModifiedBy(Instant lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(Instant lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Treatment lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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
        Treatment treatment = (Treatment) o;
        if (treatment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), treatment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Treatment{" +
            "id=" + getId() +
            ", serviceId=" + getServiceId() +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", duration=" + getDuration() +
            ", price=" + getPrice() +
            ", discount=" + getDiscount() +
            ", description='" + getDescription() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
