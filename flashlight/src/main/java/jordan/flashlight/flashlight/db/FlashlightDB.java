package jordan.flashlight.flashlight.db;

import jordan.flashlight.flashlight.models.Flashlight;

import java.util.Map;


// static class for holding the flashlight object
public class FlashlightDB {


    private static FlashlightDB flashlightStorage;
    private static Flashlight flashlight;



    private FlashlightDB(){


        flashlight = new Flashlight();








    }


    public static synchronized FlashlightDB getInstance(){


        if(flashlightStorage == null){


            flashlightStorage = new FlashlightDB();
        }

        return flashlightStorage;
    }

    public Map<String, String> getList(){


        return flashlight.getColorList();
    }



    public Flashlight updateFlashlight(String hexColor, String text){


        Map<String, String> colorMap = flashlight.getColorList();


        if(colorMap.containsKey(hexColor.toUpperCase())){

            flashlight.setHex_color(hexColor.toUpperCase());
            flashlight.setText(text);
            flashlight.setNamed_color(colorMap.get(hexColor.toUpperCase()));

            return flashlight;




        }else {


            return new Flashlight("Invalid color (hex value not in lookup map)");
        }



    }


    public Flashlight updateFlashlight(String hexColor){


        Map<String, String> colorMap = flashlight.getColorList();


        if(colorMap.containsKey(hexColor.toUpperCase())){

            flashlight.setHex_color(hexColor.toUpperCase());
            flashlight.setNamed_color(colorMap.get(hexColor.toUpperCase()));

            return flashlight;




        }else {


            return new Flashlight("Invalid color (hex value not in lookup map)");
        }



    }

    public Flashlight updateText(String t){


        flashlight.setText(t);

        return flashlight;




    }





    public Flashlight getFlashlight(){


        return flashlight;
    }
}
