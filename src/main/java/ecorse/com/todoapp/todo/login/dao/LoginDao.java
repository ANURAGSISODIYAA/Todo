package ecorse.com.todoapp.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ecorse.com.todoapp.login.model.Login;
import ecorse.com.todoapp.util.JDBCUtils;

public class LoginDao {

	public boolean validate(Login loginBean) throws ClassNotFoundException {
        boolean status = false;


        try {
        	Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection
            .prepareStatement("select * from users where username = ? and password = ? ");
            preparedStatement.setString(1, loginBean.getUserName());
            preparedStatement.setString(2, loginBean.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return status;
    }
}
