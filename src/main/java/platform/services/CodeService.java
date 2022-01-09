package platform.services;

import platform.model.Code;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface CodeService {
    Map<String, UUID> insertCode(Code code);

    List<Code> getNLatestCodes(int n);

    Optional<Code> getCode(long id);

    Optional<Code> getCode(UUID uuid);

    void updateCode(long id, Code code);

    void updateCode(UUID uuid, Code code);

    void deleteCode(long id);

    void deleteCode(UUID uuid);

    void deleteCode(Code code);

    void decrementTime(Code code);

    void decrementView(Code code);
}
