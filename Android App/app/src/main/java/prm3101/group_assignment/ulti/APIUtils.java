package prm3101.group_assignment.ulti;

/**
 * Created by ASUS on 21/10/2017.
 */

public class APIUtils {
//    public static final String BASE_URL = "https://fptlife-webservice.herokuapp.com/FPTLife/v1/";
    public static final String BASE_URL = "http://10.0.3.2:3000/FPTLife/v1/";
//    public static final String BASE_URL = "http://192.168.150.103:3000/FPTLife/v1/";

    public static APIService getAPIService() {
        return RestService.getClient(BASE_URL).create(APIService.class);
    }
}
