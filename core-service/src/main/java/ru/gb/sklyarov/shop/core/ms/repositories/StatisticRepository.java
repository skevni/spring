package ru.gb.sklyarov.shop.core.ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.sklyarov.shop.core.ms.entities.Statistic;

import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    @Override
    List<Statistic> findAll();

    @Query(value = "SELECT min(id) as id, s.service_name, sum(s.duration) as duration FROM statistics s GROUP BY s.service_name", nativeQuery = true)
    List<Statistic> findAllStatistics();
}
