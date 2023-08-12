package ugps.myweb.gpsinside.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HPController {

    @RequestMapping("/")
    public String goToHomepage(){
        return "index";
    }

    @GetMapping("/home")
    public String gotoHome(){
        return "hp/home";
    }

}
