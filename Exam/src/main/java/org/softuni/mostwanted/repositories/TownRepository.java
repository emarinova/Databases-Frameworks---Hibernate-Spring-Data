package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.dto.TownJSONImportDto;
import org.softuni.mostwanted.domain.dto.TownRacersJSONExportDto;
import org.softuni.mostwanted.domain.models.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
    Town findByName(String name);

    @Query("SELECT new org.softuni.mostwanted.domain.dto.TownRacersJSONExportDto(t.name, COUNT(r))" +
            "FROM Town t, Racer r WHERE r.homeTown = t " +
            "GROUP BY t " +
            "ORDER BY COUNT(r) DESC, t.name")
    List<TownRacersJSONExportDto> townRacers();
}
