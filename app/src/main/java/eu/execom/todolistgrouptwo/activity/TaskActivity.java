package eu.execom.todolistgrouptwo.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.androidannotations.annotations.AfterExtras;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import eu.execom.todolistgrouptwo.R;
import eu.execom.todolistgrouptwo.api.RestApi;
import eu.execom.todolistgrouptwo.model.Task;

/**
 * Created by Johnny on 12/3/2016.
 */
@EActivity(R.layout.activity_task)
public class TaskActivity extends AppCompatActivity {

//    @Bean
//    Task task;
//
//    @ViewById(R.id.title)
//    TextView title;
//
//    @ViewById(R.id.description)
//    TextView description;

//    @Extra
//    String idTask;
//
////    @RestService
////    RestApi restApi;
//
//    @AfterExtras
//    void getTask(){
//        long id = Long.parseLong(idTask);
//
////        task = restApi.getTaskById(id);
//
//    }

}
