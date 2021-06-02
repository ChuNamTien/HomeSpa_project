package com.fptu.capstone.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "code")
    private String code;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "message")
    private String message;

    @Column(name = "status")
    private String status;
    
    @OneToOne    @JoinColumn(unique = true)
    private Booking booking;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public Transaction bookingId(Long bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getCode() {
        return code;
    }

    public Transaction code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getAmount() {
        return amount;
    }

    public Transaction amount(Float amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Transaction ipAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMessage() {
        return message;
    }

    public Transaction message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public Transaction status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
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
        Transaction transaction = (Transaction) o;
        if (transaction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transaction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + getId() +
            ", bookingId=" + getBookingId() +
            ", code='" + getCode() + "'" +
            ", amount=" + getAmount() +
            ", ipAddress='" + getIpAddress() + "'" +
            ", message='" + getMessage() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
