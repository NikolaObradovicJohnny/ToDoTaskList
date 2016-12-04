package eu.execom.todolistgrouptwo.api;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Header;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Put;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

import eu.execom.todolistgrouptwo.api.interceptor.AuthenticationInterceptor;
import eu.execom.todolistgrouptwo.constant.ApiConstants;
import eu.execom.todolistgrouptwo.model.Task;
import eu.execom.todolistgrouptwo.model.dto.TokenContainerDTO;

/**
 * Created by Jovana Protic on 27-Nov-16.
 */
@Rest(rootUrl = ApiConstants.ROOT_URL, converters = {GsonHttpMessageConverter.class,
        FormHttpMessageConverter.class}, interceptors = AuthenticationInterceptor.class)
public interface RestApi {

    @Header(name = "Content-Type", value = "application/x-www-form-urlencoded")
    @Post(value = ApiConstants.LOGIN_PATH)
    TokenContainerDTO login(@Body LinkedMultiValueMap<String, String> accountInfo);

    @Post(value = ApiConstants.LOGOUT_PATH)
    void logout();

    @Get(value = ApiConstants.TASK_PATH)
    List<Task> getAllTasks();

    @Post(value = ApiConstants.TASK_PATH)
    Task createTask(@Body Task task);

    @Header(name = "Content-Type", value = "application/x-www-form-urlencoded")
    @Put(value = ApiConstants.TASK_PATH)
    Task updateTask(@Body Task task);

//    @Get(value = ApiConstants.TASK_PATH + '/')
//    Task getTaskById(@Body long id);


}
