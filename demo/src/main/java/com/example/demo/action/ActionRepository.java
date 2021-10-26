package com.example.demo.action;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {
    @Query("SELECT a FROM Action a JOIN FETCH a.bus b JOIN FETCH a.user u")
    public List<Action> findActionsEagerly();

    @Query("SELECT a FROM Action a JOIN FETCH a.bus b WHERE b.number = ?1")
    public List<Action> findBusActions(Long busNumber);

    @Query("SELECT a1 FROM Action a1 JOIN FETCH a1.bus b WHERE a1.timestamp = " +
            "(SELECT MAX(a2.timestamp) FROM Action a2 WHERE a1.bus = a2.bus) AND b.number = ?1"
    )
    public List<Action> findLastBusAction(Long busNumber);

    @Query("SELECT b.id, COUNT(a.id) FROM Action a JOIN a.bus b " +
            "WHERE a.timestamp > :beginDate AND a.timestamp < :endDate GROUP BY b.id")
    public List<Object[]> countPassengersBetween(@Param("beginDate") LocalDateTime beginDate,
                                                 @Param("endDate") LocalDateTime endDate);
}



