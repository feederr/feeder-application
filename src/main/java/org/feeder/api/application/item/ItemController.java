package org.feeder.api.application.item;

import static org.feeder.api.application.item.ItemController.ITEM_PATH;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.item.service.ItemService;
import org.feeder.api.application.item.vo.ItemRequestVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping(ITEM_PATH)
public class ItemController {

  protected static final String ITEM_PATH = "/item";

  private static final String ID_PATH = "/{id}";

  private final ItemService service;

  @PostMapping
  public ResponseEntity<?> createItem(@Valid @RequestBody final ItemRequestVO vo) {
    UUID id = UUID.randomUUID();
    return ResponseEntity.status(CREATED)
        .contentType(APPLICATION_JSON)
        .body(service.create(vo, id));
  }

  @GetMapping(ID_PATH)
  public ResponseEntity<?> getItem(@PathVariable final UUID id) {
    return ResponseEntity.status(OK)
        .contentType(APPLICATION_JSON)
        .body(service.get(id));
  }

  @GetMapping
  public ResponseEntity<?> getItems(@PageableDefault final Pageable pageable) {
    return ResponseEntity.status(OK)
        .contentType(APPLICATION_JSON)
        .body(service.getAll(pageable));
  }

  @PutMapping(ID_PATH)
  public ResponseEntity<?> updateItem(
      @PathVariable final UUID id,
      @Valid @RequestBody final ItemRequestVO vo) {
    return ResponseEntity.status(OK)
        .contentType(APPLICATION_JSON)
        .body(service.update(vo, id));
  }

  @DeleteMapping(ID_PATH)
  public ResponseEntity<?> deleteItem(@PathVariable final UUID id) {
    service.delete(id);
    return ResponseEntity.status(NO_CONTENT)
        .build();
  }
}
