package org.feeder.api.application.category;

import static org.feeder.api.application.category.CategoryController.CATEGORY_PATH;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.category.service.CategoryService;
import org.feeder.api.application.category.vo.CategoryRequestVO;
import org.feeder.api.application.category.vo.CategoryResponseVO;
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
@RequestMapping(CATEGORY_PATH)
public class CategoryController {

  protected static final String CATEGORY_PATH = "/category";

  private static final String ID_PATH = "/{id}";

  private final CategoryService service;

  @PostMapping
  public ResponseEntity<CategoryResponseVO> create(@Valid @RequestBody final CategoryRequestVO vo) {
    UUID id = UUIDUtils.optimizedUUID();
    return ResponseEntity.status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.create(vo, id));
  }

  @GetMapping(ID_PATH)
  public ResponseEntity<CategoryResponseVO> get(@PathVariable final UUID id) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.get(id));
  }

  @GetMapping
  public ResponseEntity<Page<CategoryResponseVO>> getPage(
      @RequestParam(name = "q", required = false) String predicate,
      @PageableDefault final Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.getAllBySpec(predicate, pageable));
  }

  @PutMapping(ID_PATH)
  public ResponseEntity<CategoryResponseVO> update(@PathVariable final UUID id,
      @Valid @RequestBody final CategoryRequestVO vo) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.update(vo, id));
  }

  @DeleteMapping(ID_PATH)
  public ResponseEntity<CategoryResponseVO> delete(@PathVariable final UUID id) {
    service.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
        .build();
  }
}

