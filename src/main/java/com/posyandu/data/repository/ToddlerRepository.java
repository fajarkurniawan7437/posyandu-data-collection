package com.posyandu.data.repository;

import com.posyandu.data.entity.Toddler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ToddlerRepository extends JpaRepository<Toddler, String> {

    @Modifying
    @Query(value = "INSERT INTO m_toddler (id, name, age, phone, address, weight, height, head_circumference, counseling, immunization, staff_id, status, create_at, update_at, created_by, updated_by) " +
            "VALUES (:id, :name, :age, :phone, :address, :weight, :height, :headCircumference, :counseling, :immunization, :staffId, :status, :createdAt, :updatedAt, :createdBy, :updatedBy)", nativeQuery = true)
    void saveToddler(@Param("id") String id,
                     @Param("name") String name,
                     @Param("age") Integer age,
                     @Param("phone") String phone,
                     @Param("address") String address,
                     @Param("weight") String weight,
                     @Param("height") String height,
                     @Param("headCircumference") String headCircumference,
                     @Param("counseling") boolean counseling,
                     @Param("immunization") boolean immunization,
                     @Param("staffId") String staffId,
                     @Param("status") boolean status,
                     @Param("createdAt") LocalDateTime createdAt,
                     @Param("updatedAt") LocalDateTime updatedAt,
                     @Param("createdBy") String createdBy,
                     @Param("updatedBy") String updatedBy);
    @Query(value = "SELECT * FROM m_toddler", nativeQuery = true)
    List<Toddler> findAllToddler();

    @Query(value = "SELECT * FROM m_toddler WHERE id = :id", nativeQuery = true)
    Optional<Toddler> findToddlerById(@Param("id") String id);

    @Modifying
    @Query(value = "DELETE FROM m_toddler WHERE id = :id", nativeQuery = true)
    void deleteToddlerById(@Param("id") String id);

    @Query(value = "SELECT * FROM m_toddler WHERE counseling = :counseling AND immunization = :immunization", nativeQuery = true)
    List<Toddler> findToddlerByCounselingAndImmunization(@Param("counseling") boolean counseling, @Param("immunization") boolean immunization);
}
