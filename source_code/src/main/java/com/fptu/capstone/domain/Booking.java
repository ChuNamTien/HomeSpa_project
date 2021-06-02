package com.fptu.capstone.domain;


import javax.persistence.*;


import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Booking.
 */
@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "partner_id")
    private Long partnerId;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "finish_time")
    private String finishTime;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "confirm_time")
    private String confirmTime;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "last_modified_by")
    private Instant lastModifiedBy;

    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;
    
//    @ManyToOne
//    private Customer customer;
    
    @OneToOne    
    @JoinColumn(unique = true)
    private Rating rating;

    @ManyToMany
    private Set<Voucher> vouchers = new HashSet<>();

//    public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Set<Voucher> getVouchers() {
		return vouchers;
	}

	public void setVouchers(Set<Voucher> vouchers) {
		this.vouchers = vouchers;
	}

	public Boolean getIsFinished() {
		return isFinished;
	}

	public Boolean getIsConfirmed() {
		return isConfirmed;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Booking customerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public Booking partnerId(Long partnerId) {
        this.partnerId = partnerId;
        return this;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Booking startTime(Instant startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public Booking finishTime(String finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public Boolean isIsFinished() {
        return isFinished;
    }

    public Booking isFinished(Boolean isFinished) {
        this.isFinished = isFinished;
        return this;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public Boolean isIsConfirmed() {
        return isConfirmed;
    }

    public Booking isConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
        return this;
    }

    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Booking paymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public Booking confirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
        return this;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Booking createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Booking createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Booking lastModifiedBy(Instant lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(Instant lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Booking lastModifiedDate(Instant lastModifiedDate) {
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
        Booking booking = (Booking) o;
        if (booking.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), booking.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Booking{" +
            "id=" + getId() +
            ", customerId=" + getCustomerId() +
            ", partnerId=" + getPartnerId() +
            ", startTime='" + getStartTime() + "'" +
            ", finishTime='" + getFinishTime() + "'" +
            ", isFinished='" + isIsFinished() + "'" +
            ", isConfirmed='" + isIsConfirmed() + "'" +
            ", paymentMethod='" + getPaymentMethod() + "'" +
            ", confirmTime='" + getConfirmTime() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
