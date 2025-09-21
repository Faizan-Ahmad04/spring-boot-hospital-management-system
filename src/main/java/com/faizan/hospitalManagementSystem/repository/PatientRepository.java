package com.faizan.hospitalManagementSystem.repository;

import com.faizan.hospitalManagementSystem.dto.BloodGroupCountResponseDto;
import com.faizan.hospitalManagementSystem.entity.Patient;
import com.faizan.hospitalManagementSystem.entity.type.BloodGroupType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByName(String name);

    List<Patient> findByBirthDateOrEmail(LocalDate birthDate, String email);

    List<Patient> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);

    List<Patient> findByNameContainingOrderByIdDesc(String query);

    @Query("SELECT p FROM Patient p where p.bloodGroup=?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup")BloodGroupType bloodGroup);

    @Query("SELECT p FROM Patient p where p.birthDate > :birthDate")
    List<Patient> findByBornAfter(@Param("birthDate") LocalDate birthDate);

    @Query("select new com.faizan.hospitalManagementSystem.dto.BloodGroupCountResponseDto(p.bloodGroup,"+
            " Count(p)) from Patient p group by p.bloodGroup")
    List<BloodGroupCountResponseDto> countEachBloodGroupType();

    @Query(value = "select * from patients", nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name where p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);

//    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN a.doctor")//Patient with appointment and doctor
    @Query("SELECT p FROM Patient  p LEFT JOIN FETCH p.appointments") //Patient with appointment
    List<Patient> findAllPatientWithAppointment();
}
