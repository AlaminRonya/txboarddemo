package com.sdlcpro.txdemo.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "NETWORK_INFOS")
public class NetworkInfo extends BaseEntity {
    @Column(name = "ip_address", nullable = false)
    private String ipAddress;
}
