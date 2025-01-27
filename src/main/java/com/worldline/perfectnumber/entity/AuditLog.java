package com.worldline.perfectnumber.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "perfect_numbers")
    private String perfectNumbers;
}
