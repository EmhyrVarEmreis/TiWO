package dao;

import model.User;

public class UserDao extends AbstractCRUD<User>{
    @Override
    protected String getListQuery() {
        return "FROM User";
    }

    @Override
    protected Class getRequiredClass() {
        return User.class;
    }

}
