package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.services.CodeService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/code")
public class ApiController {

    @Autowired
    private CodeService codeService;

    @PostMapping("/new")
    public Map<String, UUID> postCode(@RequestBody Code code) {
        return codeService.insertCode(code);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Code> getCodeById(@PathVariable long id) {
        Optional<Code> codeOptional = codeService.getCode(id);
        if (codeOptional.isPresent()) {
            Code code = codeOptional.get();
            if (code.isTimeRestricted()) {
                codeService.decrementTime(code);
            }
            if (code.isViewRestricted()) {
                codeService.decrementView(code);
            }
        }
        return codeService.getCode(id)
                .map(code -> ResponseEntity.ok()
                        .header("Content-Type", "application/json")
                        .body(code))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }*/

    @GetMapping("/{uuid}")
    public ResponseEntity<Code> getCodeByUuid(@PathVariable UUID uuid) {
        Optional<Code> codeOptional = codeService.getCode(uuid);
        if (codeOptional.isPresent()) {
            Code code = codeOptional.get();
            if (code.isTimeRestricted()) {
                codeService.decrementTime(code);
            }
            if (code.isViewRestricted()) {
                codeService.decrementView(code);
            }
        }
        return codeService.getCode(uuid)
                .map(code -> ResponseEntity.ok()
                        .header("Content-Type", "application/json")
                        .body(code))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/latest")
    public ResponseEntity<List<Code>> get10LatestCodes() {
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(codeService.getNLatestCodes(10));
    }
}
