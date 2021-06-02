package com.fptu.capstone.domain;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Serv.
 */
@Entity
@Table(name = "serv")
public class Serv implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "customer_type")
    private String customerType;

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
    
    @OneToMany(mappedBy = "serv")
    private Set<ServImg> servImgs = new HashSet<>();
    
    @OneToMany(mappedBy = "serv")
    private Set<Treatment> treatments = new HashSet<>();
    
    @ManyToOne
    @JsonIgnoreProperties("servs")
    private Voucher vouchers;

    @ManyToOne
    @JsonIgnoreProperties("servs")
    private Partner partners;
    
    

    public Set<ServImg> getServImgs() {
		return servImgs;
	}

	public void setServImgs(Set<ServImg> servImgs) {
		this.servImgs = servImgs;
	}

	public Set<Treatment> getTreatments() {
		return treatments;
	}

	public void setTreatments(Set<Treatment> treatments) {
		this.treatments = treatments;
	}

	public Voucher getVouchers() {
		return vouchers;
	}

	public void setVouchers(Voucher vouchers) {
		this.vouchers = vouchers;
	}

	public Partner getPartners() {
		return partners;
	}

	public void setPartners(Partner partners) {
		this.partners = partners;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Serv categoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public Serv name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerType() {
        return customerType;
    }

    public Serv customerType(String customerType) {
        this.customerType = customerType;
        return this;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getDescription() {
        return description;
    }

    public Serv description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Serv createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Serv createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Serv lastModifiedBy(Instant lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(Instant lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Serv lastModifiedDate(Instant lastModifiedDate) {
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
        Serv serv = (Serv) o;
        if (serv.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), serv.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Serv{" +
            "id=" + getId() +
            ", categoryId=" + getCategoryId() +
            ", name='" + getName() + "'" +
            ", customerType='" + getCustomerType() + "'" +
            ", description='" + getDescription() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
