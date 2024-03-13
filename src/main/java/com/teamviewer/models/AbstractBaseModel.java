package com.teamviewer.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractBaseModel implements Serializable {

    @Column(name = "created_date")
    protected Date createdDate;

    @Column(name = "created_by")
    protected String createdBy;

    @Column(name = "modified_date")
    protected Date modifiedDate;

    @Column(name = "modified_by")
    protected String modifiedBy;
}
