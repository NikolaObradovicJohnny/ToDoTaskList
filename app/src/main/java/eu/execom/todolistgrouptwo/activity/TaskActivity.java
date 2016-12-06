package eu.execom.todolistgrouptwo.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.androidannotations.annotations.AfterExtras;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import eu.execom.todolistgrouptwo.R;
import eu.execom.todolistgrouptwo.api.RestApi;
import eu.execom.todolistgrouptwo.model.Task;
import eu.execom.todolistgrouptwo.util.NetworkingUtils;

/**
 * Created by Johnny on 12/3/2016.
 */
@EActivity(R.layout.activity_task)
public class TaskActivity extends AppCompatActivity {

    @ViewById(R.id.titleTV)
    TextView titleTV;

    @ViewById(R.id.descriptionTV)
    TextView descriptionTV;

    @Extra
    long idTask;

    @Extra
    String titleTask;

    @Extra
    String descriptionTask;

    @Extra
    boolean finishedTask;
//
    @RestService
    RestApi restApi;

    @AfterViews
    void getTask(){
        Toast.makeText(this,"ID: "+idTask,Toast.LENGTH_SHORT).show();

        titleTV.setText(titleTask);
//        Toast.makeText(this,titleTask.toString(),Toast.LENGTH_SHORT).show();
        descriptionTV.setText(descriptionTask);
//        Toast.makeText(this,descriptionTask,Toast.LENGTH_SHORT).show();
        if (finishedTask){
            Toast.makeText(this,"FINISHED",Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this,"NOT FINISHED",Toast.LENGTH_SHORT).show();

    }

    @Click
    void btnDone(){
        Task task = new Task();
        task.setId(idTask);
        task.setDescription(descriptionTask);
        task.setTitle(titleTask);
        task.setFinished(true);

        updateTask(task);
    }

    @Click
    void btnRemove(){
        Toast.makeText(this,"CLICK REMOVE",Toast.LENGTH_SHORT).show();
        Task task = new Task();
        task.setId(idTask);
        task.setDescription(descriptionTask);
        task.setTitle(titleTask);

        deleteTask(task);
    }

    @Background
    void updateTask(Task task){
        restApi.updateTaskById(task,(int)idTask);
    }

    @Background
    void deleteTask(Task task){
        restApi.removeTaskById((int)task.getId());
    }

}
