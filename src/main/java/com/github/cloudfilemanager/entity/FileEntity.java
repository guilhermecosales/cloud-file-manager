package com.github.cloudfilemanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "files")
public class FileEntity {

    @Id
    @UuidGenerator
    private UUID fileId;

    @Column(unique = true, nullable = false, length = 100)
    private String fileName;

    @Column(nullable = false, length = 100)
    private String fileUrl;

    @Column(nullable = false, length = 100)
    private LocalDateTime uploadDate;

    @Column(nullable = false, length = 100)
    private double fileSize;

    @Column(nullable = false, length = 40)
    private String fileType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileEntity that = (FileEntity) o;
        return Objects.equals(fileId, that.fileId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fileId);
    }

}

