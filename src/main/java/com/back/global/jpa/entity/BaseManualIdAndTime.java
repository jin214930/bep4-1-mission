package com.back.global.jpa.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class BaseManualIdAndTime extends BaseEntity {
    @Id
    private Long id;

    @CreatedDate
    private LocalDateTime createdDate;

    @CreatedDate
    private LocalDateTime modifiedDate;

    public BaseManualIdAndTime(Long id) {
        this.id = id;
    }
}
