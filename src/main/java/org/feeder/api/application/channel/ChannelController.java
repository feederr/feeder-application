package org.feeder.api.application.channel;

import static org.feeder.api.application.channel.ChannelController.CHANNEL_PATH;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.channel.service.ChannelService;
import org.feeder.api.application.channel.vo.ChannelRequestVO;
import org.feeder.api.application.channel.vo.ChannelResponseVO;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CHANNEL_PATH)
@RequiredArgsConstructor
public class ChannelController {

  protected static final String CHANNEL_PATH = "/channel";

  private static final String ID_PATH = "/{id}";

  private final ChannelService service;

  @PostMapping
  public ResponseEntity<ChannelResponseVO> create(@Valid @RequestBody final ChannelRequestVO vo) {
    UUID id = UUIDUtils.optimizedUUID();
    return ResponseEntity.status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.create(vo, id));
  }

  @GetMapping(ID_PATH)
  public ResponseEntity<ChannelResponseVO> get(@PathVariable final UUID id) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.get(id));
  }

  @GetMapping
  public ResponseEntity<Page<ChannelResponseVO>> getPage(@PageableDefault final Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.getAll(pageable));
  }

  @GetMapping("/category" + ID_PATH)
  public ResponseEntity<Page<ChannelResponseVO>> getPageByCategory(
      @PathVariable final UUID id,
      @PageableDefault final Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.getAllByCategory(id, pageable));
  }

  @GetMapping("/description/{description}")
  public ResponseEntity<Page<ChannelResponseVO>> getPageByDescription(
      @PathVariable @NotBlank final String description,
      @PageableDefault final Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.getAllByDescription(description, pageable));
  }

  @GetMapping("/title/{title}")
  public ResponseEntity<Page<ChannelResponseVO>> getPageByTitle(
      @PathVariable @NotBlank final String title,
      @PageableDefault final Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.getAllByTitle(title, pageable));
  }

  @GetMapping("/author/{author}")
  public ResponseEntity<Page<ChannelResponseVO>> getPageByAuthor(
      @PathVariable @NotBlank final String author,
      @PageableDefault final Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.getAllByAuthor(author, pageable));
  }

  @DeleteMapping(ID_PATH)
  public ResponseEntity<ChannelResponseVO> delete(@PathVariable final UUID id) {
    service.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
        .build();
  }
}
