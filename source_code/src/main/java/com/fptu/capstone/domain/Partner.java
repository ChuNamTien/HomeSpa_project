package com.fptu.capstone.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Partner.
 */
@Entity
@Table(name = "partner")
public class Partner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "partner_type")
    private String partnerType;

    @Column(name = "customer_type")
    private String customerType;

    @Column(name = "description")
    private String description;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "long_coord")
    private Float longCoord;

    @Column(name = "lat_coord")
    private Float latCoord;

    @Column(name = "open_time")
    private Instant openTime;

    @Column(name = "close_time")
    private Instant closeTime;

    @Column(name = "is_weekend_open")
    private Boolean isWeekendOpen;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "time_confirm")
    private String timeConfirm;

    @Column(name = "bussiness_license_url")
    private String bussinessLicenseUrl;

    @OneToMany(mappedBy = "partner")
    private Set<PartnerImg> partnerImgs = new HashSet<>();
    @OneToMany(mappedBy = "partner")
    private Set<Staff> staff = new HashSet<>();
    @OneToMany(mappedBy = "partner")
    private Set<Serv> servs = new HashSet<>();
    @OneToMany(mappedBy = "partner")
    private Set<Voucher> vouchers = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "partner_category",
               joinColumns = @JoinColumn(name = "partners_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "categories_id", referencedColumnName = "id"))
    private Set<Category> categories = new HashSet<>();

    @OneToOne    @JoinColumn(unique = true)
    private User user;
    
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getIsWeekendOpen() {
		return isWeekendOpen;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Partner name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartnerType() {
        return partnerType;
    }

    public Partner partnerType(String partnerType) {
        this.partnerType = partnerType;
        return this;
    }

    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
    }

    public String getCustomerType() {
        return customerType;
    }

    public Partner customerType(String customerType) {
        this.customerType = customerType;
        return this;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getDescription() {
        return description;
    }

    public Partner description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public Partner city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public Partner address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public Partner phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Float getLongCoord() {
        return longCoord;
    }

    public Partner longCoord(Float longCoord) {
        this.longCoord = longCoord;
        return this;
    }

    public void setLongCoord(Float longCoord) {
        this.longCoord = longCoord;
    }

    public Float getLatCoord() {
        return latCoord;
    }

    public Partner latCoord(Float latCoord) {
        this.latCoord = latCoord;
        return this;
    }

    public void setLatCoord(Float latCoord) {
        this.latCoord = latCoord;
    }

    public Instant getOpenTime() {
        return openTime;
    }

    public Partner openTime(Instant openTime) {
        this.openTime = openTime;
        return this;
    }

    public void setOpenTime(Instant openTime) {
        this.openTime = openTime;
    }

    public Instant getCloseTime() {
        return closeTime;
    }

    public Partner closeTime(Instant closeTime) {
        this.closeTime = closeTime;
        return this;
    }

    public void setCloseTime(Instant closeTime) {
        this.closeTime = closeTime;
    }

    public Boolean isIsWeekendOpen() {
        return isWeekendOpen;
    }

    public Partner isWeekendOpen(Boolean isWeekendOpen) {
        this.isWeekendOpen = isWeekendOpen;
        return this;
    }

    public void setIsWeekendOpen(Boolean isWeekendOpen) {
        this.isWeekendOpen = isWeekendOpen;
    }

    public Boolean isStatus() {
        return status;
    }

    public Partner status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTimeConfirm() {
        return timeConfirm;
    }

    public Partner timeConfirm(String timeConfirm) {
        this.timeConfirm = timeConfirm;
        return this;
    }

    public void setTimeConfirm(String timeConfirm) {
        this.timeConfirm = timeConfirm;
    }

    public String getBussinessLicenseUrl() {
        return bussinessLicenseUrl;
    }

    public Partner bussinessLicenseUrl(String bussinessLicenseUrl) {
        this.bussinessLicenseUrl = bussinessLicenseUrl;
        return this;
    }

    public void setBussinessLicenseUrl(String bussinessLicenseUrl) {
        this.bussinessLicenseUrl = bussinessLicenseUrl;
    }

    public Set<PartnerImg> getPartnerImgs() {
        return partnerImgs;
    }

    public Partner partnerImgs(Set<PartnerImg> partnerImgs) {
        this.partnerImgs = partnerImgs;
        return this;
    }

    public Partner addPartnerImg(PartnerImg partnerImg) {
        this.partnerImgs.add(partnerImg);
        partnerImg.setPartner(this);
        return this;
    }

    public Partner removePartnerImg(PartnerImg partnerImg) {
        this.partnerImgs.remove(partnerImg);
        partnerImg.setPartner(null);
        return this;
    }

    public void setPartnerImgs(Set<PartnerImg> partnerImgs) {
        this.partnerImgs = partnerImgs;
    }

    public Set<Staff> getStaff() {
        return staff;
    }

    public Partner staff(Set<Staff> staff) {
        this.staff = staff;
        return this;
    }

    public Partner addStaff(Staff staff) {
        this.staff.add(staff);
        staff.setPartner(this);
        return this;
    }

    public Partner removeStaff(Staff staff) {
        this.staff.remove(staff);
        staff.setPartner(null);
        return this;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }

    public Set<Serv> getServs() {
        return servs;
    }

    public Partner servs(Set<Serv> servs) {
        this.servs = servs;
        return this;
    }

    public Partner addServ(Serv serv) {
        this.servs.add(serv);
        serv.setPartner(this);
        return this;
    }

    public Partner removeServ(Serv serv) {
        this.servs.remove(serv);
        serv.setPartner(null);
        return this;
    }

    public void setServs(Set<Serv> servs) {
        this.servs = servs;
    }

    public Set<Voucher> getVouchers() {
        return vouchers;
    }

    public Partner vouchers(Set<Voucher> vouchers) {
        this.vouchers = vouchers;
        return this;
    }

    public Partner addVoucher(Voucher voucher) {
        this.vouchers.add(voucher);
        voucher.setPartner(this);
        return this;
    }

    public Partner removeVoucher(Voucher voucher) {
        this.vouchers.remove(voucher);
        voucher.setPartner(null);
        return this;
    }

    public void setVouchers(Set<Voucher> vouchers) {
        this.vouchers = vouchers;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Partner categories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Partner addCategory(Category category) {
        this.categories.add(category);
        category.getPartners().add(this);
        return this;
    }

    public Partner removeCategory(Category category) {
        this.categories.remove(category);
        category.getPartners().remove(this);
        return this;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
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
        Partner partner = (Partner) o;
        if (partner.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), partner.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Partner{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", partnerType='" + getPartnerType() + "'" +
            ", customerType='" + getCustomerType() + "'" +
            ", description='" + getDescription() + "'" +
            ", city='" + getCity() + "'" +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            ", longCoord=" + getLongCoord() +
            ", latCoord=" + getLatCoord() +
            ", openTime='" + getOpenTime() + "'" +
            ", closeTime='" + getCloseTime() + "'" +
            ", isWeekendOpen='" + isIsWeekendOpen() + "'" +
            ", status='" + isStatus() + "'" +
            ", timeConfirm='" + getTimeConfirm() + "'" +
            ", bussinessLicenseUrl='" + getBussinessLicenseUrl() + "'" +
            "}";
    }
}
