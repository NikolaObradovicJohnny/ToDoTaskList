package eu.execom.todolistgrouptwo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import eu.execom.todolistgrouptwo.R;
import eu.execom.todolistgrouptwo.database.wrapper.UserDAOWrapper;
import eu.execom.todolistgrouptwo.model.User;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity {

    @Bean
    UserDAOWrapper userDAOWrapper;

    @ViewById
    EditText name;

    @ViewById
    EditText username;

    @ViewById
    EditText password;

    @ViewById
    EditText confirmPassword;


    @EditorAction(R.id.password)
    @Click
    void register() {
        final String name = this.name.getText().toString();
        final String username = this.username.getText().toString();
        final String password = this.password.getText().toString();
        final String confirmPassword = this.confirmPassword.getText().toString();
        if (password.equals(confirmPassword)) {
//            final User user = new User(name, username, password);
            final User user = new User(username, password);

            registerUser(user);
        }
    }

    @Background
    void registerUser(User user) {
//        final boolean userCreated = userDAOWrapper.create(user);
        final User userCreated = userDAOWrapper.create(user);

        if (userCreated != null) {
            login(user);
        } else {
            showRegisterError();
        }
    }

    @UiThread
    void login(User user) {
        final Intent intent = new Intent();
        intent.putExtra("user_id", user.getId());

        setResult(RESULT_OK, intent);
        finish();
    }

    @UiThread
    void showRegisterError() {
        username.setError("Username already exists.");
    }

}
