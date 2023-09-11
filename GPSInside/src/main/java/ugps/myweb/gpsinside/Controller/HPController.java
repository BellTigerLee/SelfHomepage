package ugps.myweb.gpsinside.Controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ugps.myweb.gpsinside.Entity.UserBoard;

import java.util.ArrayList;
import java.util.List;

@Controller

public class HPController {

    private final Logger log = LoggerFactory.getLogger(HPController.class);

    @GetMapping(value = "/home")
    public String goToHomepageRoot(){
        return "redirect:/";
    }
    @GetMapping(value={"/"})
    public String goToHomepage(){
        log.info("Homepage가 요청되었습니다!");
        log.info("Homepage를 반환합니다!");
        return "pages/MainPage";
    }




}
