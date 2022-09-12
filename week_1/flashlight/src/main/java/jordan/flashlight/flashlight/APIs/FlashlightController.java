package jordan.flashlight.flashlight.APIs;


import jordan.flashlight.flashlight.db.FlashlightDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/flashlight")
public class FlashlightController {







    @GetMapping("/all-colors")
    public ResponseEntity<Map<String, String>> getAllColors(){


        return new ResponseEntity<>(FlashlightDB.getInstance().getList(), HttpStatus.OK);







    }


    @GetMapping("/current/{value}")
    public ResponseEntity<?> getCurrentStatus(@PathVariable("value") String value){


        if(value.equalsIgnoreCase("status")){

            return new ResponseEntity<>(FlashlightDB.getInstance().getFlashlight(), HttpStatus.OK);

        } else if (value.equalsIgnoreCase("hex")){


            return new ResponseEntity<>(FlashlightDB.getInstance().getFlashlight().getHex_color(), HttpStatus.OK );
        }
        else if (value.equalsIgnoreCase("color")){


            return new ResponseEntity<>(FlashlightDB.getInstance().getFlashlight().getNamed_color(), HttpStatus.OK );
        }
        else if (value.equalsIgnoreCase("text")){


            return new ResponseEntity<>(FlashlightDB.getInstance().getFlashlight().getText(), HttpStatus.OK );
        }
        else {



            return new ResponseEntity<>("Invalid Path Paramter", HttpStatus.BAD_REQUEST);
        }







    }




    @PostMapping("/change")
    public ResponseEntity<?> changeFlashlight(@RequestParam(name = "hex", required = false) String hex, @RequestParam(name = "text", required = false) String text){


        if (hex == null){


            if(text != null){


                FlashlightDB.getInstance().updateText(text);

                return new ResponseEntity<>(FlashlightDB.getInstance().updateText(text), HttpStatus.OK);




            }





        }else{

            if(FlashlightDB.getInstance().getList().containsKey(hex.toUpperCase())){




                if (text == null){

                    FlashlightDB.getInstance().updateFlashlight(hex);

                }
                else{

                    FlashlightDB.getInstance().updateFlashlight(hex.toUpperCase(), text);

                }

                return new ResponseEntity<>(FlashlightDB.getInstance().getFlashlight(), HttpStatus.OK);
            }




        }



        return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);



    }

}
