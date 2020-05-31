package org.feeder.api.application.channel;

import java.util.UUID;
import org.feeder.api.application.category.entity.Category;
import org.feeder.api.application.channel.entity.Channel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, UUID> {

  Page<Channel> findAllByCategoriesContaining(Category category, Pageable pageable);

  Page<Channel> findAllByDescriptionLike(String description, Pageable pageable);

  Page<Channel> findAllByTitleLike(String title, Pageable pageable);

  Page<Channel> findAllByAuthorLike(String author, Pageable pageable);
}
