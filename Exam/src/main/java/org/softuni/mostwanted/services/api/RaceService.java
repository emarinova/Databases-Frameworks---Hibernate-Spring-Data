package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.RaceXMLImportDto;

import javax.persistence.criteria.CriteriaBuilder;

public interface RaceService {
    Integer save(RaceXMLImportDto dto);
}
