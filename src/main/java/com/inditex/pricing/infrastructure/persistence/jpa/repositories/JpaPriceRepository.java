package com.inditex.pricing.infrastructure.persistence.jpa.repositories;

import com.inditex.pricing.infrastructure.persistence.jpa.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE " +
            "p.productId = :productId AND " +
            "p.brandId = :brandId AND " +
            ":date BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC")
    List<PriceEntity> findPrices(
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            @Param("date") LocalDateTime date);


    @Query("SELECT p FROM PriceEntity p WHERE " +
            "p.productId = :productId AND " +
            "p.brandId = :brandId AND " +
            ":date BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC " +
            "FETCH FIRST 1 ROW ONLY")
    Optional<PriceEntity> findPriorityPrice(
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            @Param("date") LocalDateTime date);
}
