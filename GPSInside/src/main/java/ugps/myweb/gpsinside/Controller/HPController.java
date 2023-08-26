package ugps.myweb.gpsinside.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ugps.myweb.gpsinside.Entity.UserBoard;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class HPController {

    @GetMapping(value = "/home")
    public String goToHomepageRoot(){
        return "redirect:/";
    }
    @GetMapping(value={"/"})
    public String goToHomepage(){
        return "pages/MainPage";
    }

    @GetMapping(value={"/ba"})
    public String goToBoardPage(Model model){
        List<UserBoard> relation = new ArrayList<>();
        model.addAttribute("relation", relation);
        log.info("Board Page is called!");
        return "pages/BoardPage";
    }


}
