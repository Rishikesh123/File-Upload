package com.FileUpload.repository;

import com.FileUpload.models.CDR;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CDRRepository extends JpaRepository<CDR, Long>, JpaSpecificationExecutor<CDR> {
    List<CDR> findAllByCallId(Long callId, Pageable pageable);
}
