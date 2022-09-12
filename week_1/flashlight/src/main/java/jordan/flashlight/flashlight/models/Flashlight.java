package jordan.flashlight.flashlight.models;


import java.util.HashMap;
import java.util.Map;


// object type for the flashlight


// hex color --> rgb string with preceding #
//  named_color --> human name of the color generated from hex
// text --> text for the flashlight
public class Flashlight {


    private String hex_color;
    private String named_color;

    private Map<String, String> colorList = initalizeColors();
    private String text;


    public Flashlight(){

        this.text = "Send in requests!";
        this.named_color = "WHITE";
        this.hex_color = "#FFFFFF";
    }

    public Flashlight(String txt){


        this.text = txt;
        this.named_color = "WHITE";
        this.hex_color = "#FFFFFF";
    }


    public Flashlight(String hex_color, String text){


        this.hex_color = hex_color;
        this.text = text;


    }

    private Map<String , String > initalizeColors(){


        Map<String, String> colorLobby = new HashMap<>();


        colorLobby.put("#C0C0C0", "SILVER");
        colorLobby.put("#808080", "GRAY");
        colorLobby.put("#808000", "OLIVE");
        colorLobby.put("#800000", "MAROON");
        colorLobby.put("#00FF00", "LIME");
        colorLobby.put("#00FFFF", "AQUA");
        colorLobby.put("#008080", "TEAL");
        colorLobby.put("#000080", "NAVY");
        colorLobby.put("#FF00FF", "FUCHSIA");
        colorLobby.put("#800080", "PURPLE");
        colorLobby.put("#CD5C5C", "INDIANRED");
        colorLobby.put("#F08080", "LIGHTCORAL");
        colorLobby.put("#FA8072", "SALMON");
        colorLobby.put("#E9967A", "DARKSALMON");
        colorLobby.put("#FFA07A", "LIGHTSALMON");
        colorLobby.put("#B22222", "FIREBRICK");

        return colorLobby;




    }

    public String getHex_color() {
        return hex_color;
    }

    public void setHex_color(String hex_color) {
        this.hex_color = hex_color;
    }

    public String getNamed_color() {
        return named_color;
    }

    public void setNamed_color(String named_color) {
        this.named_color = named_color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, String> getColorList(){

        return colorList;
    }
}
