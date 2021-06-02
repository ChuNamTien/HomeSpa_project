package com.fptu.capstone.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

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

    @Column(name = "name")
    private String name;

    @Column(name = "customer_type")
    private String customerType;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "serv")
    private Set<ServImg> servImgs = new HashSet<>();
    @OneToMany(mappedBy = "serv")
    private Set<Treatment> treatments = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("servs")
    private Partner partner;

    @ManyToMany(mappedBy = "servs")
    @JsonIgnore
    private Set<Voucher> vouchers = new HashSet<>();

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

    public Set<ServImg> getServImgs() {
        return servImgs;
    }

    public Serv servImgs(Set<ServImg> servImgs) {
        this.servImgs = servImgs;
        return this;
    }

    public Serv addServImg(ServImg servImg) {
        this.servImgs.add(servImg);
        servImg.setServ(this);
        return this;
    }

    public Serv removeServImg(ServImg servImg) {
        this.servImgs.remove(servImg);
        servImg.setServ(null);
        return this;
    }

    public void setServImgs(Set<ServImg> servImgs) {
        this.servImgs = servImgs;
    }

    public Set<Treatment> getTreatments() {
        return treatments;
    }

    public Serv treatments(Set<Treatment> treatments) {
        this.treatments = treatments;
        return this;
    }

    public Serv addTreatment(Treatment treatment) {
        this.treatments.add(treatment);
        treatment.setServ(this);
        return this;
    }

    public Serv removeTreatment(Treatment treatment) {
        this.treatments.remove(treatment);
        treatment.setServ(null);
        return this;
    }

    public void setTreatments(Set<Treatment> treatments) {
        this.treatments = treatments;
    }

    public Partner getPartner() {
        return partner;
    }

    public Serv partner(Partner partner) {
        this.partner = partner;
        return this;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Set<Voucher> getVouchers() {
        return vouchers;
    }

    public Serv vouchers(Set<Voucher> vouchers) {
        this.vouchers = vouchers;
        return this;
    }

    public Serv addVoucher(Voucher voucher) {
        this.vouchers.add(voucher);
        voucher.getServs().add(this);
        return this;
    }

    public Serv removeVoucher(Voucher voucher) {
        this.vouchers.remove(voucher);
        voucher.getServs().remove(this);
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
            ", name='" + getName() + "'" +
            ", customerType='" + getCustomerType() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
