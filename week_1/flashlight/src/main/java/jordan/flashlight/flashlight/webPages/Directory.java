package jordan.flashlight.flashlight.webPages;


import jordan.flashlight.flashlight.db.FlashlightDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Directory {


    // this routes the "/" endpoint to the index html page
    @GetMapping("/")
    public String mainPage(Model model){



        model.addAttribute("text", getCurrentText());
        model.addAttribute("hex", getCurrentHexColor());

        return "index";
    }


    // calls the static instance of the Flashlight object to get the hex color
    public String getCurrentHexColor(){



        return FlashlightDB.getInstance().getFlashlight().getHex_color();
    }


    // calls the static instance of the Flashlight object to get the text
    public String getCurrentText(){


        return FlashlightDB.getInstance().getFlashlight().getText();
    }
}
