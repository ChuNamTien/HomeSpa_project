package com.fptu.capstone.domain;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

//    @Column(name = "customer_id")
//    private Long customerId;
//
//    @Column(name = "partner_id")
//    private Long partnerId;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "finish_time")
    private Instant finishTime;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    @Column(name = "duration")
    private Float duration;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "confirm_time")
    private Instant confirmTime;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "partner_id")
    @JsonIgnore
    private Partner partner;
    
    @ManyToOne
    @JoinColumn(name = "serv_id")
    @JsonIgnore
    private Serv serv;
    
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    @JsonIgnore
    private Voucher voucher;

    @OneToMany(mappedBy = "booking")
    private Set<BookingActivity> bookingActivities = new HashSet<>();
    
    @OneToOne
    @JoinColumn(name = "id")
    private Rating rating ;
    
    @OneToOne
    @JoinColumn(name = "id")
    private Transaction transaction ;
    
    public Set<BookingActivity> getBookingActivities() {
		return bookingActivities;
	}

	public void setBookingActivities(Set<BookingActivity> bookingActivities) {
		this.bookingActivities = bookingActivities;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getCustomerId() {
//        return customerId;
//    }
//
//    public Booking customerId(Long customerId) {
//        this.customerId = customerId;
//        return this;
//    }
//
//    public void setCustomerId(Long customerId) {
//        this.customerId = customerId;
//    }
//
//    public Long getPartnerId() {
//        return partnerId;
//    }
//
//	public Booking partnerId(Long partnerId) {
//        this.partnerId = partnerId;
//        return this;
//    }

    public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
//
//	public void setPartnerId(Long partnerId) {
//        this.partnerId = partnerId;
//    }

    public Instant getStartTime() {
        return startTime;
    }

    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Serv getServ() {
		return serv;
	}

	public void setServ(Serv serv) {
		this.serv = serv;
	}

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public Boolean getIsFinished() {
		return isFinished;
	}

	public Boolean getIsConfirmed() {
		return isConfirmed;
	}

	public Booking startTime(Instant startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getFinishTime() {
        return finishTime;
    }

    public Booking finishTime(Instant finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    public void setFinishTime(Instant finishTime) {
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

    public Float getDuration() {
        return duration;
    }

    public Booking duration(Float duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
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

    public Instant getConfirmTime() {
        return confirmTime;
    }

    public Booking confirmTime(Instant confirmTime) {
        this.confirmTime = confirmTime;
        return this;
    }

    public void setConfirmTime(Instant confirmTime) {
        this.confirmTime = confirmTime;
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
//            ", customerId=" + getCustomerId() +
//            ", partnerId=" + getPartnerId() +
            ", startTime='" + getStartTime() + "'" +
            ", finishTime='" + getFinishTime() + "'" +
            ", isFinished='" + isIsFinished() + "'" +
            ", isConfirmed='" + isIsConfirmed() + "'" +
            ", duration=" + getDuration() +
            ", paymentMethod='" + getPaymentMethod() + "'" +
            ", confirmTime='" + getConfirmTime() + "'" +
            "}";
    }
}
