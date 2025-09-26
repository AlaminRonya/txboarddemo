package com.sdlcpro.txdemo.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.GenerationType.UUID;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = UUID)
    private UUID id;

    @CreatedDate
    @Column(name = "CREATED_AT", updatable = false, nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE_AT", insertable = false)
    private Instant lastModifiedDateAt;

    @CreatedBy
    @Column(name = "CREATED_BY", nullable = false, updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY", insertable = false)
    private String lastModifiedBy;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (createdBy == null){
            createdBy = "user";
        }
        onPrePersist();
    }

    @PreUpdate
    public void preUpdate() {
        lastModifiedDateAt = Instant.now();
        lastModifiedBy = "user";
    }

    protected void onPrePersist() {}

}