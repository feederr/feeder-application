package org.feeder.api.application.source;

import java.util.UUID;
import org.feeder.api.application.source.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, UUID> {

}
