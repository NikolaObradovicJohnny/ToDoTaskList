package eu.execom.todolistgrouptwo.database.wrapper;

import org.androidannotations.annotations.EBean;
import org.androidannotations.ormlite.annotations.OrmLiteDao;
import org.androidannotations.rest.spring.annotations.RestService;

import eu.execom.todolistgrouptwo.api.RestApi;
import eu.execom.todolistgrouptwo.database.DatabaseHelper;
import eu.execom.todolistgrouptwo.database.dao.UserDAO;
import eu.execom.todolistgrouptwo.model.User;
import eu.execom.todolistgrouptwo.util.NetworkingUtils;

@EBean
public class UserDAOWrapper {

    @OrmLiteDao(helper = DatabaseHelper.class)
    UserDAO userDAO;

    @RestService
    RestApi restApi;

    public User findByUsernameAndPassword(String username, String password) {
        return userDAO.findByUsernameAndPassword(username, password);
    }

    public User create(User user) {
        restApi.register(NetworkingUtils.registerUserCredentials(user.getUsername(),user.getPassword(),user.getPassword()));
        return user;
    }

    public User findById(long userId) {
        return userDAO.queryForId(userId);
    }
}
