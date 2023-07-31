package com.posyandu.data.repository;

import com.posyandu.data.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, String> {

    @Query(value = "SELECT * FROM m_staff", nativeQuery = true)
    List<Staff> findAllStaff();

    @Query(value = "SELECT * FROM m_staff WHERE id = :id", nativeQuery = true)
    Optional<Staff> findStaffById(@Param("id") String id);

    @Query(value = "SELECT * FROM m_staff WHERE phone = :phone", nativeQuery = true)
    Optional<Staff> findStaffByPhone(@Param("phone") String phone);

    @Modifying
    @Query(value = "DELETE FROM m_staff WHERE id = :id", nativeQuery = true)
    void deleteStaffById(@Param("id") String id);

    @Query(value = "SELECT * FROM m_staff WHERE status = :status", nativeQuery = true)
    List<Staff> findStaffByStatus(@Param("status") boolean status);
    Optional<Staff> findByUserCredential_Email(String email);
}
