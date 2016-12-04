package eu.execom.todolistgrouptwo.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterExtras;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
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
////    @RestService
////    RestApi restApi;
//
    @AfterViews
    void getTask(){
//        long id = Long.parseLong(idTask);

//        Button btn = (Button) findViewById(R.id.btnDone);
//        btn.setText("NE NE NE");
//        TextView textView = (TextView) findViewById(R.id.titleTV);
//        Toast.makeText(this,textView.getText().toString(),Toast.LENGTH_SHORT).show();
//        textView.setText(titleTask);
//        TextView textView2 = (TextView) findViewById(R.id.descriptionTV);
//        textView2.setText(descriptionTask);
        titleTV.setText(titleTask);
        Toast.makeText(this,titleTask.toString(),Toast.LENGTH_SHORT).show();
//        titleTV.setText("steva");
        descriptionTV.setText(descriptionTask);
//        descriptionTV.text
        Toast.makeText(this,descriptionTask,Toast.LENGTH_SHORT).show();
        if (finishedTask){
            Toast.makeText(this,"FINISHED",Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this,"NOT FINISHED",Toast.LENGTH_SHORT).show();

//        task = restApi.getTaskById(id);

    }

}
