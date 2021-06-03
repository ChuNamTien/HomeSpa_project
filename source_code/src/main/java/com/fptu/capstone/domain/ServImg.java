package com.fptu.capstone.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ServImg.
 */
@Entity
@Table(name = "serv_img")
public class ServImg implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "status")
    private String status;

    @Column(name = "jhi_index")
    private Long index;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public ServImg serviceId(Long serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public ServImg imgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getStatus() {
        return status;
    }

    public ServImg status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIndex() {
        return index;
    }

    public ServImg index(Long index) {
        this.index = index;
        return this;
    }

    public void setIndex(Long index) {
        this.index = index;
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
        ServImg servImg = (ServImg) o;
        if (servImg.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), servImg.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ServImg{" +
            "id=" + getId() +
            ", serviceId=" + getServiceId() +
            ", imgUrl='" + getImgUrl() + "'" +
            ", status='" + getStatus() + "'" +
            ", index=" + getIndex() +
            "}";
    }
}
