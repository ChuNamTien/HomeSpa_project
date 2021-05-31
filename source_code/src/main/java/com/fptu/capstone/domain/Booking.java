package com.fptu.capstone.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Booking.
 */
@Entity
@Table(name = "booking")
public class Booking extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "finish_time")
    private Instant finishTime;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    @Column(name = "payment_method")
    private String paymentMethod;

    @ManyToMany
    @JoinTable(name = "booking_treatment",
               joinColumns = @JoinColumn(name = "bookings_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "treatments_id", referencedColumnName = "id"))
    private Set<Treatment> treatments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("bookings")
    private Customer customer;

    @ManyToMany(mappedBy = "bookings")
    @JsonIgnore
    private Set<Voucher> vouchers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Treatment> getTreatments() {
        return treatments;
    }

    public Booking treatments(Set<Treatment> treatments) {
        this.treatments = treatments;
        return this;
    }

    public Booking addTreatment(Treatment treatment) {
        this.treatments.add(treatment);
        treatment.getBookings().add(this);
        return this;
    }

    public Booking removeTreatment(Treatment treatment) {
        this.treatments.remove(treatment);
        treatment.getBookings().remove(this);
        return this;
    }

    public void setTreatments(Set<Treatment> treatments) {
        this.treatments = treatments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Booking customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Voucher> getVouchers() {
        return vouchers;
    }

    public Booking vouchers(Set<Voucher> vouchers) {
        this.vouchers = vouchers;
        return this;
    }

    public Booking addVoucher(Voucher voucher) {
        this.vouchers.add(voucher);
        voucher.getBookings().add(this);
        return this;
    }

    public Booking removeVoucher(Voucher voucher) {
        this.vouchers.remove(voucher);
        voucher.getBookings().remove(this);
        return this;
    }

    public void setVouchers(Set<Voucher> vouchers) {
        this.vouchers = vouchers;
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
            ", startTime='" + getStartTime() + "'" +
            ", finishTime='" + getFinishTime() + "'" +
            ", isFinished='" + isIsFinished() + "'" +
            ", isConfirmed='" + isIsConfirmed() + "'" +
            ", paymentMethod='" + getPaymentMethod() + "'" +
            "}";
    }
}
