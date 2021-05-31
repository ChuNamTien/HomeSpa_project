package com.fptu.capstone.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Treatment.
 */
@Entity
@Table(name = "treatment")
public class Treatment extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne
    @JsonIgnoreProperties("treatments")
    private Serv serv;

    @ManyToMany(mappedBy = "treatments")
    @JsonIgnore
    private Set<Staff> staff = new HashSet<>();

    @ManyToMany(mappedBy = "treatments")
    @JsonIgnore
    private Set<Booking> bookings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Serv getServ() {
        return serv;
    }

    public Treatment serv(Serv serv) {
        this.serv = serv;
        return this;
    }

    public void setServ(Serv serv) {
        this.serv = serv;
    }

    public Set<Staff> getStaff() {
        return staff;
    }

    public Treatment staff(Set<Staff> staff) {
        this.staff = staff;
        return this;
    }

    public Treatment addStaff(Staff staff) {
        this.staff.add(staff);
        staff.getTreatments().add(this);
        return this;
    }

    public Treatment removeStaff(Staff staff) {
        this.staff.remove(staff);
        staff.getTreatments().remove(this);
        return this;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public Treatment bookings(Set<Booking> bookings) {
        this.bookings = bookings;
        return this;
    }

    public Treatment addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.getTreatments().add(this);
        return this;
    }

    public Treatment removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.getTreatments().remove(this);
        return this;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
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
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", duration=" + getDuration() +
            ", price=" + getPrice() +
            ", discount=" + getDiscount() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
