package com.fptu.capstone.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
public class Category extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "status")
    private String status;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<Partner> partners = new HashSet<>();

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<Staff> staff = new HashSet<>();

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

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Category imgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getStatus() {
        return status;
    }

    public Category status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Partner> getPartners() {
        return partners;
    }

    public Category partners(Set<Partner> partners) {
        this.partners = partners;
        return this;
    }

    public Category addPartner(Partner partner) {
        this.partners.add(partner);
        partner.getCategories().add(this);
        return this;
    }

    public Category removePartner(Partner partner) {
        this.partners.remove(partner);
        partner.getCategories().remove(this);
        return this;
    }

    public void setPartners(Set<Partner> partners) {
        this.partners = partners;
    }

    public Set<Staff> getStaff() {
        return staff;
    }

    public Category staff(Set<Staff> staff) {
        this.staff = staff;
        return this;
    }

    public Category addStaff(Staff staff) {
        this.staff.add(staff);
        staff.getCategories().add(this);
        return this;
    }

    public Category removeStaff(Staff staff) {
        this.staff.remove(staff);
        staff.getCategories().remove(this);
        return this;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
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
        Category category = (Category) o;
        if (category.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", imgUrl='" + getImgUrl() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
