package app.retake.repositories;

import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    Animal findByPassportSerialNumber(String serialNumber);

    @Query("SELECT new app.retake.domain.dto.AnimalsJSONExportDTO(p.ownerName, a.name, a.age, p.serialNumber, p.registrationDate)" +
            "FROM Animal a, Passport p WHERE a.passport = p " +
            "AND p.ownerPhoneNumber = :number " +
            "ORDER BY a.age, p.serialNumber")
    List<AnimalsJSONExportDTO> allAnimalsByOwnerPhoneNumber(@Param("number") String number);
}
