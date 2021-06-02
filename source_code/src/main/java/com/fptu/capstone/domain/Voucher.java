package com.fptu.capstone.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Voucher.
 */
@Entity
@Table(name = "voucher")
public class Voucher extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "discription")
    private String discription;

    @Column(name = "discount")
    private Float discount;

    @ManyToMany
    @JoinTable(name = "customer_voucher",
               joinColumns = @JoinColumn(name = "vouchers_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "customers_id", referencedColumnName = "id"))
    private Set<Customer> customers = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "voucher_serv",
               joinColumns = @JoinColumn(name = "vouchers_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "servs_id", referencedColumnName = "id"))
    private Set<Serv> servs = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "voucher_booking",
               joinColumns = @JoinColumn(name = "vouchers_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "bookings_id", referencedColumnName = "id"))
    private Set<Booking> bookings = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("vouchers")
    private Partner partner;

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

    public Set<Customer> getCustomers() {
        return customers;
    }

    public Voucher customers(Set<Customer> customers) {
        this.customers = customers;
        return this;
    }

    public Voucher addCustomer(Customer customer) {
        this.customers.add(customer);
        customer.getVouchers().add(this);
        return this;
    }

    public Voucher removeCustomer(Customer customer) {
        this.customers.remove(customer);
        customer.getVouchers().remove(this);
        return this;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Serv> getServs() {
        return servs;
    }

    public Voucher servs(Set<Serv> servs) {
        this.servs = servs;
        return this;
    }

    public Voucher addServ(Serv serv) {
        this.servs.add(serv);
        serv.getVouchers().add(this);
        return this;
    }

    public Voucher removeServ(Serv serv) {
        this.servs.remove(serv);
        serv.getVouchers().remove(this);
        return this;
    }

    public void setServs(Set<Serv> servs) {
        this.servs = servs;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public Voucher bookings(Set<Booking> bookings) {
        this.bookings = bookings;
        return this;
    }

    public Voucher addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.getVouchers().add(this);
        return this;
    }

    public Voucher removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.getVouchers().remove(this);
        return this;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Partner getPartner() {
        return partner;
    }

    public Voucher partner(Partner partner) {
        this.partner = partner;
        return this;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
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
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", discription='" + getDiscription() + "'" +
            ", discount=" + getDiscount() +
            "}";
    }
}
