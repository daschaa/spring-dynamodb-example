package de.joshuaw.mmatechniques;

import java.util.Optional;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface TechniqueRepository extends
    CrudRepository<Technique, String> {

  Optional<Technique> findById(String id);
}
