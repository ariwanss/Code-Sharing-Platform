package platform.controllers;

import javassist.NotFoundException;
import org.apache.coyote.Response;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import platform.model.Code;
import platform.services.CodeService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/code")
public class HtmlController {

    @Autowired
    private CodeService codeService;

    @GetMapping("/new")
    public String newCode() {
        return "new_code";
    }

    /*@GetMapping("/{id}")
    public String getCodePageById(@PathVariable long id, Model model) {
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
        codeOptional = codeService.getCode(id);
        if (codeOptional.isPresent()) {
            model.addAttribute("code", codeOptional.get());
            return "view_code";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }*/

    @GetMapping("/{uuid}")
    public String getCodePageByUUID(@PathVariable UUID uuid, Model model) {
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
        codeOptional = codeService.getCode(uuid);
        if (codeOptional.isPresent()) {
            model.addAttribute("code", codeOptional.get());
            return "view_code";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/latest")
    public String get10LatestCodesPage(Model model) {
        List<Code> codes = codeService.getNLatestCodes(10);
        model.addAttribute("title", "Latest");
        model.addAttribute("codeList", codes);
        return "view_latest_codes";
    }

}
