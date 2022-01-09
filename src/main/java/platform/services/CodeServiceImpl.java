package platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.model.Code;
import platform.repository.CodeRepository;
import platform.util.Utility;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CodeServiceImpl implements CodeService {

    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    @Autowired
    private CodeRepository codeRepository;

    @Override
    public void decrementTime(Code code){
        if (code.getTime() > 0) {
            code.setTime(code.getTime() - Duration.between(code.getDateCreated(), LocalDateTime.now()).toSeconds());
            if (code.getTime() < 0) {
                codeRepository.delete(code);
            } else {
                codeRepository.save(code);
            }
        } else {
            codeRepository.delete(code);
        }
    }

    @Override
    public void decrementView(Code code) {
        if (code.getViews() > 0) {
            code.setViews(code.getViews() - 1);
            codeRepository.save(code);
        } else {
            codeRepository.delete(code);
        }
    }

    @Override
    public Map<String, UUID> insertCode(Code code) {
        LocalDateTime now = LocalDateTime.now();
        code.setUuid(Utility.generateUuid());
        code.setTimeRestricted(code.getTime() > 0);
        code.setViewRestricted(code.getViews() > 0);
        return Map.of("id", codeRepository.save(code).getUuid());
    }

    @Override
    public Optional<Code> getCode(long id) {
        return codeRepository.findById(id);
    }

    @Override
    public Optional<Code> getCode(UUID uuid) {
        return codeRepository.findByUuid(uuid);
    }

    @Override
    public List<Code> getNLatestCodes(int n) {
        //List<Code> codes = new ArrayList<>();
        //codeRepository.findAll().forEach(codes::add);
        List<Code> codes = codeRepository
                .findByTimeRestrictedEqualsAndViewRestrictedEquals(false, false);
        codes.sort(Comparator.comparing(Code::getId).reversed());
        return codes.stream().limit(n).collect(Collectors.toList());
    }

    @Override
    public void updateCode(long id, Code code) {
        Code codeInDb = codeRepository.findById(id).get();
        codeInDb.setCode(code.getCode());
        codeInDb.setDate(LocalDateTime.now());
        codeRepository.save(codeInDb);
    }

    @Override
    public void updateCode(UUID uuid, Code code) {
        Code codeInDb = codeRepository.findByUuid(uuid).get();
        codeInDb.setCode(code.getCode());
        codeInDb.setDate(LocalDateTime.now());
        codeRepository.save(codeInDb);
    }

    @Override
    public void deleteCode(long id) {
        codeRepository.deleteById(id);
    }

    @Override
    public void deleteCode(UUID uuid) {
        codeRepository.deleteByUuid(uuid);
    }

    @Override
    public void deleteCode(Code code) {
        codeRepository.delete(code);
    }
}
