package org.feeder.api.application.compilation;

import java.util.UUID;
import org.feeder.api.application.compilation.entity.Compilation;
import org.feeder.api.core.search.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompilationRepository extends JpaSpecificationRepository<Compilation, UUID> {

}
