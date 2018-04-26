package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.models.RaceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceEntryRepository extends JpaRepository<RaceEntry, Integer> {
    RaceEntry findById(int id);
    List<RaceEntry> findDistinctByRacerName(String name);
}
