package com.tree.family.user.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User_")
public class User_ implements Serializable {
    /**
     * default serial version UID
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "userId", nullable = false)
    private long userId;

    @Column(name = "uuid_", nullable = false)
    private String uuid_;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "emailAddress", nullable = false)
    private String emailAddress;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "createDate", nullable = false)
    private Date createDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

    @Column(name = "createdBy", nullable = false)
    private long createdBy;

    @Column(name = "isLocked")
    private boolean isLocked;

}
