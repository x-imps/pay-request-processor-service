package com.ximps.payment_service.repository;

import com.ximps.payment_service.entity.PayRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRequestRepository extends JpaRepository<PayRequest, String> {}
