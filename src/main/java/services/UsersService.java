package services;

import builders.HomeParameterStringBuilder;
import builders.UserParameterStringBuilder;
import java.util.HashMap;
import models.Home;
import models.User;


public class UsersService  extends ServiceSkeleton
{    
    private static final String BASE_URL = "http://197.52.108.54:4444/api/users";
    private static UsersService _usersService;

    private UsersService() 
    {
  
    }
    
    
    public static UsersService getInstance()
    {
        if(_usersService == null)
        {
            _usersService = new UsersService();
        }
        return _usersService;
    }
    
    public HashMap<String, Object> login(String email, String password){
        final String PATH = BASE_URL + "/auth/login";
        final String REQUEST_METHOD = REQUEST_METHOD_POST;
        final int PARSE_AS = 1;//as user
        HashMap<String, String> requestParameters;
        requestParameters = UserParameterStringBuilder.setupUserLoginRequestParameters(email, password);
        return fetchData(PATH, REQUEST_METHOD, requestParameters, PARSE_AS);
    }
    
    public HashMap<String, Object> signUp(String name, String email, String birthDate, String password, String passwordConfirmation){
        final String PATH = BASE_URL + "/auth/signup";
        final String REQUEST_METHOD = REQUEST_METHOD_POST;
        final int PARSE_AS = 1;//as user
        HashMap<String, String> requestParameters;
        requestParameters = UserParameterStringBuilder.setupUserSignUpRequestParameters(name, email, birthDate, password, passwordConfirmation);
        return fetchData(PATH, REQUEST_METHOD, requestParameters, PARSE_AS);
    }
    
    public HashMap<String, Object> updateUser(User user){
        final String PATH = BASE_URL + "/update/" + user.getId();
        final String REQUEST_METHOD = REQUEST_METHOD_POST;
        final int PARSE_AS = 1;//as user
        HashMap<String, String> requestParameters;
        requestParameters = UserParameterStringBuilder.setupUserUpdateRequestParameters(user);
        return fetchData(PATH, REQUEST_METHOD, requestParameters, PARSE_AS);
    }
    //****must put Home attributes in both Home and HomeParameterStringBuilder 0classes 
    public HashMap<String, Object> addHomeToUser(String userId, Home home){
        final String PATH = BASE_URL + "/add-home/" + userId;
        final String REQUEST_METHOD = REQUEST_METHOD_POST;
        final int PARSE_AS = 2;// as Home
        HashMap<String, String> requestParameters;
        requestParameters =  HomeParameterStringBuilder.setupAddHomeRequestParameters(home.getName(), home.getAddress());
        return fetchData(PATH, REQUEST_METHOD, requestParameters, PARSE_AS);
    }
    
    
      public HashMap<String, Object> addHomeToUser(String userId, String homeId){
        final String PATH = BASE_URL + "/add-home/" + homeId + "/" + userId;
        final String REQUEST_METHOD = REQUEST_METHOD_POST;
        final int PARSE_AS = 2;// as Home
        return fetchData(PATH, REQUEST_METHOD, PARSE_AS);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
} //CLASS END 
