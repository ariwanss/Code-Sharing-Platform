package platform.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.model.Code;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long> {
    Optional<Code> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

    List<Code> findByTimeRestrictedEqualsAndViewRestrictedEquals(boolean isTimeRestricted, boolean isViewRestricted);

    //List<Code> findByIsRestrictedEquals(int isRestricted);

    /*@Query("SELECT c" +
            "FROM Code c" +
            "WHERE c.uuid == ?1" +
            "AND (c.isRestricted == 0 OR (c.time > 0 AND c.views > 0))")
    Optional<Code> findByUuidCustom(UUID uuid);*/
}
