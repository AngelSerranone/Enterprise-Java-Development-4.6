package com.ironhack.Lab4_6.repository;

import com.ironhack.Lab4_6.enums.Status;
import com.ironhack.Lab4_6.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    public List<Patient> findByName(String name);
    public Patient findByPatientId(Integer patientId);

    @Query("select p from Patient p left join p.admittedBy a where a.name=:name")
    public List<Patient> findByDoctorName(@Param("name") String name);

    public List<Patient> findByDateOfBirthBetween(LocalDate dateStart, LocalDate dateEnd);

    @Query("select p from Patient p left join p.admittedBy e where e.department=:department")
    public List<Patient> findByDoctorDepartment(@Param("department") String department);

    @Query("select p from Patient p left join p.admittedBy e where e.status=:status")
    public List<Patient> findByDoctorStatusOff(@Param("status") Status status);
}
