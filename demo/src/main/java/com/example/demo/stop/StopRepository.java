package com.example.demo.stop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StopRepository extends JpaRepository <Stop, Long> {
    @Query("SELECT s FROM Stop s JOIN FETCH s.bus JOIN FETCH s.station")
    public List<Stop> findAllStopsEagerly();

    @Query("SELECT s FROM Stop s JOIN FETCH s.bus b " +
            "JOIN FETCH s.station s2 " +
            "WHERE b.id = :busId AND s2.id = :stationId AND s.timestamp " +
            "BETWEEN :firstDate AND :secondDate")
    public List<Stop> findStopsOnStationByIdBetween(@Param("busId") Long busId,
                                                    @Param("stationId") Long stationId,
                                                    @Param("firstDate")LocalDateTime firstDate,
                                                    @Param("secondDate") LocalDateTime secondDate);

    @Query("SELECT s FROM Stop s JOIN FETCH s.bus JOIN FETCH s.station " +
            "WHERE s.bus.id = :busId AND s.timestamp > :startDate")
    public List<Stop> findAllBusStopsByIdSince(@Param("busId") Long busId,
                                               @Param("startDate") LocalDateTime startDate);

    @Query("SELECT s FROM Stop s JOIN FETCH s.bus JOIN FETCH s.station " +
            "WHERE s.bus.id = :busId AND s.timestamp > :startDate " +
            "AND s.timestamp < :endDate")
    public List<Stop> findAllBusStopsByIdBetween(@Param("busId") Long busId,
                                                 @Param("startDate") LocalDateTime startDate,
                                                 @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(s1), MIN(s1.timestamp), MIN(b.id), s2.id " +
            "FROM Stop s1 JOIN s1.bus b JOIN s1.station s2 " +
            "WHERE b.id = :busId GROUP BY s2.id HAVING COUNT(s1.id) > 20")
    List <Object[]> findAllBusStations(@Param("busId") Long busId);
}