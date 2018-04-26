package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.dto.RacerXMLExportDto;
import org.softuni.mostwanted.domain.models.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacerRepository extends JpaRepository<Racer,Integer> {
    Racer findByName(String name);
    List<Racer> findDistinctByCarsNotNull();

    @Query("SELECT new org.softuni.mostwanted.domain.dto.RacerXMLExportDto(r.name)" +
            "FROM Racer r, RaceEntry e WHERE e.racer = r " +
            "GROUP BY r " +
            "ORDER BY COUNT(e) DESC")
    List<RacerXMLExportDto> racersWithEntries();
}
