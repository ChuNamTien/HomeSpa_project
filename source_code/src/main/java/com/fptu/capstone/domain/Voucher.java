package com.fptu.capstone.domain;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Voucher.
 */
@Entity
@Table(name = "voucher")
public class Voucher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "partner_id")
    private Long partnerId;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "discription")
    private String discription;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "price_above")
    private Float priceAbove;

    @Column(name = "max_discount")
    private Float maxDiscount;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "last_modified_by")
    private Instant lastModifiedBy;

    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "voucher_booking",
               joinColumns = @JoinColumn(name = "voucher_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "id"))
    private Set<Booking> bookings = new HashSet<>();
    
    @OneToMany(mappedBy = "voucher")
    private Set<Serv> services = new HashSet<>();
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "customer_voucher",
               joinColumns = @JoinColumn(name = "voucher_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"))
    private Set<Customer> customers = new HashSet<>();

    @ManyToOne
    private Partner partner;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public Voucher partnerId(Long partnerId) {
        this.partnerId = partnerId;
        return this;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public String getName() {
        return name;
    }

    public Voucher name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public Voucher type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscription() {
        return discription;
    }

    public Voucher discription(String discription) {
        this.discription = discription;
        return this;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Float getDiscount() {
        return discount;
    }

    public Voucher discount(Float discount) {
        this.discount = discount;
        return this;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getPriceAbove() {
        return priceAbove;
    }

    public Voucher priceAbove(Float priceAbove) {
        this.priceAbove = priceAbove;
        return this;
    }

    public void setPriceAbove(Float priceAbove) {
        this.priceAbove = priceAbove;
    }

    public Float getMaxDiscount() {
        return maxDiscount;
    }

    public Voucher maxDiscount(Float maxDiscount) {
        this.maxDiscount = maxDiscount;
        return this;
    }

    public void setMaxDiscount(Float maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Voucher createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Voucher createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Voucher lastModifiedBy(Instant lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(Instant lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Voucher lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	public Set<Serv> getServices() {
		return services;
	}

	public void setServices(Set<Serv> services) {
		this.services = services;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Voucher voucher = (Voucher) o;
        if (voucher.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), voucher.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Voucher{" +
            "id=" + getId() +
            ", partnerId=" + getPartnerId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", discription='" + getDiscription() + "'" +
            ", discount=" + getDiscount() +
            ", priceAbove=" + getPriceAbove() +
            ", maxDiscount=" + getMaxDiscount() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
