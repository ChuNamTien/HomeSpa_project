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
public class Serv extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "category_id")
//    private Long categoryId;
    
//    @Column(name = "partner_id")
//    private Long partnerId;

    @Column(name = "name")
    private String name;

    @Column(name = "customer_type")
    private String customerType;

    @Column(name = "description")
    private String description;
    
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name="partner_id")
    private Partner partner;
    
    @OneToMany(mappedBy="serv")
    @JsonIgnore
    private Set<Treatment> treatments = new HashSet<>();
    
    @OneToMany(mappedBy="serv")
    @JsonIgnore
    private Set<Booking> bookings = new HashSet<>();
    
    @ManyToMany(mappedBy = "servs")
    @JsonIgnoreProperties("servs")
    private Set<Voucher> vouchers = new HashSet<>();

    @OneToMany(mappedBy="serv")
    private Set<Booking> servImgs = new HashSet<>();
    
//    public Long getPartnerId() {
//		return partnerId;
//	}
//
//	public void setPartnerId(Long partnerId) {
//		this.partnerId = partnerId;
//	}

	public Set<Voucher> getVouchers() {
		return vouchers;
	}

	public void setVouchers(Set<Voucher> vouchers) {
		this.vouchers = vouchers;
	}

	public Set<Booking> getServImgs() {
		return servImgs;
	}

	public void setServImgs(Set<Booking> servImgs) {
		this.servImgs = servImgs;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

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

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getCategoryId() {
//        return categoryId;
//    }
//
//    public Serv categoryId(Long categoryId) {
//        this.categoryId = categoryId;
//        return this;
//    }
//
//    public void setCategoryId(Long categoryId) {
//        this.categoryId = categoryId;
//    }

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
//            ", categoryId=" + getCategoryId() +
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
