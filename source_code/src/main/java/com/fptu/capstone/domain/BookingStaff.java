package com.fptu.capstone.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A BookingStaff.
 */
@Entity
@Table(name = "booking_staff")
public class BookingStaff implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "treatment_id")
    private Long treatmentId;

    @Column(name = "status")
    private Long status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public BookingStaff bookingId(Long bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getTreatmentId() {
        return treatmentId;
    }

    public BookingStaff treatmentId(Long treatmentId) {
        this.treatmentId = treatmentId;
        return this;
    }

    public void setTreatmentId(Long treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Long getStatus() {
        return status;
    }

    public BookingStaff status(Long status) {
        this.status = status;
        return this;
    }

    public void setStatus(Long status) {
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
        BookingStaff bookingStaff = (BookingStaff) o;
        if (bookingStaff.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bookingStaff.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BookingStaff{" +
            "id=" + getId() +
            ", bookingId=" + getBookingId() +
            ", treatmentId=" + getTreatmentId() +
            ", status=" + getStatus() +
            "}";
    }
}
