package com.fptu.capstone.domain;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

//    @Column(name = "user_id", nullable = false)
//    private Long userId;

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
    private Float openTime;

    @Column(name = "close_time")
    private Float closeTime;

    @Column(name = "is_weekend_open")
    private Boolean isWeekendOpen;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "confirm_after")
    private Float confirmAfter;

    @Column(name = "bussiness_license_url")
    private String bussinessLicenseUrl;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToMany(mappedBy = "likedPartners")
    @JsonIgnore
    private Set<Customer> likedCustomers = new HashSet<>();
    
    @OneToMany(mappedBy="partner")
    @JsonIgnore
    private Set<Report> report = new HashSet<>();
    
    @OneToMany(mappedBy="partner")
    private Set<PartnerImg> partnerImgs = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
        name = "partner_category",
        joinColumns = {@JoinColumn(name = "partner_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    @JsonIgnoreProperties("partners")
    private Set<Category> categories = new HashSet<>();
    
    @OneToMany(mappedBy="partner")
    @JsonIgnore
    private Set<Voucher> vouchers = new HashSet<>();
    
    @OneToMany(mappedBy="partner")
    @JsonIgnore
    private Set<Booking> bookings = new HashSet<>();
    
    @OneToMany(mappedBy="partner")
    @JsonIgnore
    private Set<Serv> servs = new HashSet<>();
    
    @OneToMany(mappedBy="partner")
    @JsonIgnore
    private Set<Staff> staffs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getUserId() {
//        return userId;
//    }
//
//    public Partner userId(Long userId) {
//        this.userId = userId;
//        return this;
//    }

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

    
    
    public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
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

    public Float getOpenTime() {
        return openTime;
    }

    public Partner openTime(Float openTime) {
        this.openTime = openTime;
        return this;
    }

    public void setOpenTime(Float openTime) {
        this.openTime = openTime;
    }

    public Float getCloseTime() {
        return closeTime;
    }

    public Partner closeTime(Float closeTime) {
        this.closeTime = closeTime;
        return this;
    }

    public void setCloseTime(Float closeTime) {
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

    public Float getConfirmAfter() {
        return confirmAfter;
    }

    public Partner confirmAfter(Float confirmAfter) {
        this.confirmAfter = confirmAfter;
        return this;
    }

    public void setConfirmAfter(Float confirmAfter) {
        this.confirmAfter = confirmAfter;
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
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Customer> getLikedCustomers() {
		return likedCustomers;
	}

	public void setLikedCustomers(Set<Customer> likedCustomers) {
		this.likedCustomers = likedCustomers;
	}

	public Set<Report> getReport() {
		return report;
	}

	public void setReport(Set<Report> report) {
		this.report = report;
	}

	public Set<PartnerImg> getPartnerImgs() {
		return partnerImgs;
	}

	public void setPartnerImgs(Set<PartnerImg> partnerImgs) {
		this.partnerImgs = partnerImgs;
	}

	public Boolean getIsWeekendOpen() {
		return isWeekendOpen;
	}

	public Boolean getStatus() {
		return status;
	}

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
//            ", userId=" + getUserId() +
            ", name='" + getName() + "'" +
            ", partnerType='" + getPartnerType() + "'" +
            ", customerType='" + getCustomerType() + "'" +
            ", description='" + getDescription() + "'" +
            ", city='" + getCity() + "'" +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            ", longCoord=" + getLongCoord() +
            ", latCoord=" + getLatCoord() +
            ", openTime=" + getOpenTime() +
            ", closeTime=" + getCloseTime() +
            ", isWeekendOpen='" + isIsWeekendOpen() + "'" +
            ", status='" + isStatus() + "'" +
            ", confirmAfter=" + getConfirmAfter() +
            ", bussinessLicenseUrl='" + getBussinessLicenseUrl() + "'" +
            "}";
    }
}
