package ugps.myweb.gpsinside.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class HPController {

    @GetMapping(value={"/", "/home"})
    public String goToHomepage(){
        return "pages/MainPage";
    }

    @GetMapping(value={"/ba"})
    public String goToBoardPage(){
        log.info("Board Page is called!");
        return "BoardPage";
    }


}
