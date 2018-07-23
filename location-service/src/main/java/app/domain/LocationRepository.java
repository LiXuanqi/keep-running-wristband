package app.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

// JpaRepository<Entity, id>
// should be deleted and replace with RestRepository.
public interface LocationRepository extends JpaRepository<Location, Long> {
    Page<Location> findByRunnerMovementType(@Param("movementType") Location.RunnerMovementType movementType, Pageable pageable);
}

// pagination, how to implement by sql.