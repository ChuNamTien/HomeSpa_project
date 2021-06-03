package com.fptu.capstone.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A BookingActivity.
 */
@Entity
@Table(name = "booking_activity")
public class BookingActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "staff_id")
    private Long staffId;

    @Column(name = "treatment_id")
    private Long treatmentId;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "finish_date")
    private Instant finishDate;

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

    public BookingActivity bookingId(Long bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public BookingActivity staffId(Long staffId) {
        this.staffId = staffId;
        return this;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getTreatmentId() {
        return treatmentId;
    }

    public BookingActivity treatmentId(Long treatmentId) {
        this.treatmentId = treatmentId;
        return this;
    }

    public void setTreatmentId(Long treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public BookingActivity startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getFinishDate() {
        return finishDate;
    }

    public BookingActivity finishDate(Instant finishDate) {
        this.finishDate = finishDate;
        return this;
    }

    public void setFinishDate(Instant finishDate) {
        this.finishDate = finishDate;
    }

    public Long getStatus() {
        return status;
    }

    public BookingActivity status(Long status) {
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
        BookingActivity bookingActivity = (BookingActivity) o;
        if (bookingActivity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bookingActivity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BookingActivity{" +
            "id=" + getId() +
            ", bookingId=" + getBookingId() +
            ", staffId=" + getStaffId() +
            ", treatmentId=" + getTreatmentId() +
            ", startDate='" + getStartDate() + "'" +
            ", finishDate='" + getFinishDate() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
