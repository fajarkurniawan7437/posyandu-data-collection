package com.posyandu.data.repository;

import com.posyandu.data.entity.Elderly;
import com.posyandu.data.entity.Toddler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ElderlyRepository extends JpaRepository<Elderly, String> {

    @Query(value = "SELECT * FROM m_elderly", nativeQuery = true)
    List<Elderly> findAllElderly();

    @Query(value = "SELECT * FROM m_elderly WHERE id = :id", nativeQuery = true)
    Optional<Elderly> findElderlyById(@Param("id") String id);

    @Modifying
    @Query(value = "DELETE FROM m_elderly WHERE id = :id", nativeQuery = true)
    void deleteElderlyById(@Param("id") String id);

}
