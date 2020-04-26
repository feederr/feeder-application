package org.feeder.api.application.source;

import static org.feeder.api.application.source.SourceController.SOURCE_PATH;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.source.service.SourceService;
import org.feeder.api.application.source.vo.SourceRequestVO;
import org.feeder.api.application.source.vo.SourceResponseVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(SOURCE_PATH)
public class SourceController {

  protected static final String ID_PATH = "/{id}";

  protected static final String SOURCE_PATH = "/source";

  private final SourceService service;

  @PostMapping
  public ResponseEntity<SourceResponseVO> create(@Valid @RequestBody final SourceRequestVO vo) {
    UUID id = UUID.randomUUID();
    return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
        .body(service.create(vo, id));
  }

  @GetMapping(ID_PATH)
  public ResponseEntity<SourceResponseVO> get(@PathVariable final UUID id) {
    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
        .body(service.get(id));
  }

  @GetMapping
  public ResponseEntity<Page<SourceResponseVO>> getPage(@PageableDefault final Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
        .body(service.getAll(pageable));
  }

  @PutMapping(ID_PATH)
  public ResponseEntity<SourceResponseVO> update(@PathVariable final UUID id,
      @Valid @RequestBody final SourceRequestVO vo) {
    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
        .body(service.update(vo, id));
  }

  @DeleteMapping(ID_PATH)
  public ResponseEntity<SourceResponseVO> delete(@PathVariable final UUID id) {
    service.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
