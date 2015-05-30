package blogpian.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by rodolfo on 5/25/15.
 */

@Controller
public class SiteController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "rodolfo baeza");
        return "index";
    }

}
