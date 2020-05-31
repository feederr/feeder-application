package org.feeder.api.application.channel.image;

import java.util.UUID;
import org.feeder.api.application.channel.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {

}
