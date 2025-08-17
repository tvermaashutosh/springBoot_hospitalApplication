package com.example.hospital.repository;

import com.example.hospital.dto.type.Blood;
import com.example.hospital.entity.PatientEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {
    @Query(value = "SELECT * FROM patient_tbl", nativeQuery = true)
    Page<PatientEntity> findAllByPage(Pageable page);

    @Query("SELECT p FROM PatientEntity p WHERE p.blood=?1")
    List<PatientEntity> findByBlood(Blood blood);

    @Query("SELECT p FROM PatientEntity p WHERE p.age>=:ageParam")
    List<PatientEntity> findByAgeGreaterThanOrEqualTo(@Param("ageParam") Integer age);

    @Transactional
    @Modifying
    @Query("UPDATE PatientEntity p SET p.name=:nameParam WHERE p.user.id=:userIdParam")
    Integer updateNameWithId(@Param("nameParam") String name, @Param("userIdParam") Integer userId);

    @Query("SELECT p.blood,COUNT(p) FROM PatientEntity p GROUP BY p.blood")
    List<Object[]> countPatientWithEachBloodGroup();

    /*
    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments")
    List<Patient> findAllPatientWithAppointment();
    */
}
