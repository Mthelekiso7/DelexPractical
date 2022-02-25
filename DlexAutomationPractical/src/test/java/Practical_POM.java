import java.util.HashMap;
import java.util.Map;

public class Practical_POM {

    public static void setPage_Path()
    {
        Map<String,String> My_Page_Objects = new HashMap();

        //Logo
        My_Page_Objects.put("txtlogo","XPATH|//*[@id='header_logo']/a/img");
        //*[@id="header_logo"]/a/img

    }
}
