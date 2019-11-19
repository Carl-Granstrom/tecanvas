package com.ltu.ladok.repository;

import com.ltu.ladok.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    @Query(value = "SELECT * FROM examination WHERE course_instance_fk = :courseInstanceId", nativeQuery = true)
    List<Examination> findByCourseInstanceId(@Param("courseInstanceId") Long courseInstanceId);

    @Query(value = "SELECT * FROM examination WHERE course_instance_fk = :courseInstanceId AND provnummer = :provNummer", nativeQuery = true)
    Examination findByCourseInstanceIdAndProvNummer(@Param("courseInstanceId") Long courseInstanceId,
                                                    @Param("provNummer") String provNummer);
}
