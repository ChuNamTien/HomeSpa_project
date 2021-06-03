package com.fptu.capstone.domain;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A PartnerImg.
 */
@Entity
@Table(name = "partner_img")
public class PartnerImg implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "partner_id", nullable = false)
//    private Long partnerId;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @Column(name = "is_hiddent")
    private Boolean isHiddent;

    @Column(name = "jhi_index")
    private Long index;
    
    @ManyToOne
    @JoinColumn(name = "partner_id")
    @JsonIgnore
    private Partner partner;
    
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getPartnerId() {
//        return partnerId;
//    }
//
//    public PartnerImg partnerId(Long partnerId) {
//        this.partnerId = partnerId;
//        return this;
//    }
//
//    public void setPartnerId(Long partnerId) {
//        this.partnerId = partnerId;
//    }

    public String getImgUrl() {
        return imgUrl;
    }

    public PartnerImg imgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Boolean isIsHiddent() {
        return isHiddent;
    }

    public PartnerImg isHiddent(Boolean isHiddent) {
        this.isHiddent = isHiddent;
        return this;
    }

    public void setIsHiddent(Boolean isHiddent) {
        this.isHiddent = isHiddent;
    }

    public Long getIndex() {
        return index;
    }

    public PartnerImg index(Long index) {
        this.index = index;
        return this;
    }

    public void setIndex(Long index) {
        this.index = index;
    }
    
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Boolean getIsHiddent() {
		return isHiddent;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PartnerImg partnerImg = (PartnerImg) o;
        if (partnerImg.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), partnerImg.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PartnerImg{" +
            "id=" + getId() +
//            ", partnerId=" + getPartnerId() +
            ", imgUrl='" + getImgUrl() + "'" +
            ", isHiddent='" + isIsHiddent() + "'" +
            ", index=" + getIndex() +
            "}";
    }
}
