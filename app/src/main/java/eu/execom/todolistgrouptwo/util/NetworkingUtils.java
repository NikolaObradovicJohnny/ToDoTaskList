package eu.execom.todolistgrouptwo.util;

import org.springframework.util.LinkedMultiValueMap;

/**
 * Created by Nikola Obradovic on 27-Nov-16.
 */

public class NetworkingUtils {

    public static LinkedMultiValueMap<String ,String> packUserCredentials(String email, String password){
        final LinkedMultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.set("grant_type","password");
        map.set("username",email);
        map.set("password",password);
        return map;
    }

    public static LinkedMultiValueMap<String ,String> registerUserCredentials(String email, String password, String confirmPassword){
        final LinkedMultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.set("grant_type","password");
        map.set("email",email);
        map.set("password",password);
        map.set("confirmPassword",password);
        return map;
    }

    public static LinkedMultiValueMap<String ,String> unpackUserCredentials(){
        final LinkedMultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        return map;
    }

    public static LinkedMultiValueMap<String ,String> finishTask(long id, String title,String description){
        final LinkedMultiValueMap<String,String> map = new LinkedMultiValueMap<>();
//        map.set("grant_type","password");
        map.set("id",id+"");
        map.set("title",title);
        map.set("description",description);
        map.set("finished","true");
        return map;
    }
}
