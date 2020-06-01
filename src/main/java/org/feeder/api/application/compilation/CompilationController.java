package org.feeder.api.application.compilation;

import static org.feeder.api.application.compilation.CompilationController.COMPILATION_PATH;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.compilation.sevice.CompilationService;
import org.feeder.api.application.compilation.vo.CompilationRequestVO;
import org.feeder.api.application.compilation.vo.CompilationResponseVO;
import org.feeder.api.core.util.UUIDUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMPILATION_PATH)
public class CompilationController {

  protected static final String COMPILATION_PATH = "/compilation";

  private static final String ID_PATH = "/{id}";

  private final CompilationService service;

  @PostMapping
  public ResponseEntity<CompilationResponseVO> create(
      @Valid @RequestBody final CompilationRequestVO vo) {
    UUID id = UUIDUtils.optimizedUUID();
    return ResponseEntity.status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.create(vo, id));
  }

  @GetMapping(ID_PATH)
  public ResponseEntity<CompilationResponseVO> get(@PathVariable final UUID id) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.get(id));
  }

  @GetMapping
  public ResponseEntity<Page<CompilationResponseVO>> getPage(
      @RequestParam(name = "q", required = false) String predicate,
      @PageableDefault final Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.getAllBySpec(predicate, pageable));
  }

  @PutMapping(ID_PATH)
  public ResponseEntity<CompilationResponseVO> update(@PathVariable final UUID id,
      @Valid @RequestBody final CompilationRequestVO vo) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.update(vo, id));
  }

  @DeleteMapping(ID_PATH)
  public ResponseEntity<CompilationResponseVO> delete(@PathVariable final UUID id) {
    service.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
        .build();
  }
}
