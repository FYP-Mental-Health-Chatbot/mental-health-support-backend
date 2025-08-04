package com.example.mental_health_companion.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "app_configs")
@Data
public class AppConfig {

    @Id
    private String configKey;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String configValue;

    @Column(nullable = false)
    private String dataType;
}
