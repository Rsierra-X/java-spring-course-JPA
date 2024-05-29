package org.rsierra.repository;

import org.rsierra.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
    List<Vacancy> findByStatus(String status);

    List<Vacancy> findByFeaturedAndStatusOrderByIdDesc(int featured, String status);

    List<Vacancy> findBySalaryBetweenOrderBySalaryDesc(double s1, double s2);

    List<Vacancy> findByStatusIn(String[] status);
}
