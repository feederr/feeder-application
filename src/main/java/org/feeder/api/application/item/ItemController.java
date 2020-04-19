package org.feeder.api.application.item;

import static org.feeder.api.application.item.ItemController.ITEM_PATH;

import lombok.RequiredArgsConstructor;
import org.feeder.api.application.item.entity.Item;
import org.feeder.api.application.item.service.ItemService;
import org.feeder.api.application.item.vo.ItemRequestVO;
import org.feeder.api.application.item.vo.ItemResponseVO;
import org.feeder.api.core.controller.BaseCrudController;
import org.feeder.api.core.service.BaseCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ITEM_PATH)
public class ItemController extends BaseCrudController<Item, ItemRequestVO, ItemResponseVO> {

  protected static final String ITEM_PATH = "/item";

  private final ItemService service;

  @Override
  protected BaseCrudService<Item, ItemRequestVO, ItemResponseVO> getService() {
    return service;
  }
}
