package Donggukthon.santa.repository;

import Donggukthon.santa.domain.DesignatedPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignatedPersonRepository extends JpaRepository<DesignatedPerson, Long> {
}
