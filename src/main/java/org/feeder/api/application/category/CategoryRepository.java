package org.feeder.api.application.category;

import java.util.UUID;
import org.feeder.api.application.category.entity.Category;
import org.feeder.api.core.search.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaSpecificationRepository<Category, UUID> {

}
