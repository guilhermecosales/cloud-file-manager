package com.github.cloudfilemanager.repository;

import com.github.cloudfilemanager.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, UUID> {

    @Query("select f from FileEntity f where upper(f.fileName) like upper(concat('%', ?1, '%'))")
    List<FileEntity> findByFileName(String fileName);

    Optional<FileEntity> findByFileNameIgnoreCase(String fileName);

}
