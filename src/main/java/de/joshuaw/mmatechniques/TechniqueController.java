package de.joshuaw.mmatechniques;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TechniqueController {

  private final TechniqueRepository repository;

  @GetMapping("/techniques")
  public ResponseEntity<List<Technique>> getTechniques() {
    final List<Technique> list = new ArrayList<>();
    repository.findAll().forEach(list::add);
    return ResponseEntity.ok(list);
  }

  @GetMapping("/techniques/{id}")
  public ResponseEntity<Technique> getTechnique(@PathVariable String id) {
    final Optional<Technique> technique = repository.findById(id);
    return technique.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
  }

  @PostMapping("/techniques")
  public ResponseEntity<String> addTechnique(@RequestBody Technique technique) {
    final String id = repository.save(technique).getId();
    return ResponseEntity.ok(id);
  }

  @PatchMapping("/techniques/{id}")
  public ResponseEntity<Technique> updateTechnique(@PathVariable String id, @RequestBody Technique techniqueUpdate) {
    final Optional<Technique> techniqueOptional = repository.findById(id);
    if(techniqueOptional.isPresent()) {
      final Technique technique = techniqueOptional.get();
      technique.setTitle(techniqueUpdate.getTitle());
      technique.setDescription(techniqueUpdate.getDescription());
      technique.setVideoLinks(techniqueUpdate.getVideoLinks());
      return ResponseEntity.ok(repository.save(technique));
    }
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/techniques/{id}")
  public ResponseEntity<Technique> removeTechnique(@PathVariable String id) {
    final Optional<Technique> technique = repository.findById(id);
    if(technique.isPresent()) {
      repository.delete(technique.get());
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.noContent().build();
  }
}
