package com.kkb.xzk.validation;

import java.sql.*;

/**
 * @Author: HanDing
 * @Description:
 * @Date Created in 2021-07-22 19:13
 * @Modified By:
 */
public class DatabaseValidation {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb6?serverTimeZone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";
    private Connection connection;
    private PreparedStatement preparedStatement;

    public DatabaseValidation() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_NAME);
        connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    public boolean validate(String userName, String password){
        if(userName==null || "".equals(userName) || "".equals(password)){
            return false;
        }
        String sql = "select userpass from user where username=?";
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String userpass = resultSet.getString(1);
                if (userpass == null || "".equals(userpass)) {
                    return false;
                } else if (password.equals(userpass)) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
}
