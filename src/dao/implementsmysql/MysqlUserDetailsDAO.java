package dao.implementsmysql;

import dao.UserDetailsDAO;
import dao.exception.DAOException;
import db.ConnectionHolder;
import db.DBUtil;

import entity.UserDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MysqlUserDetailsDAO implements UserDetailsDAO {
    private static MysqlUserDetailsDAO userDetailsDAO;
    private static final Logger LOG = Logger.getLogger(MysqlUserDetailsDAO.class.getName());

    private static final String CREATE_USER_DETAILS = "INSERT INTO user_details(id,password,phone,avatar_referense,login,first_name,last_name,age)VALUES(?,?,?,?,?,?,?,?)";
    private static final String DELETE_BY_ORDER_USER_DETAILS = "DELETE  FROM user_details WHERE id=?";
    private static final String GET_USER_DETAILS = "SELECT * FROM user_details WHERE id = ?";
    private static final String UPDATE_USER_DETAILS= "UPDATE user_details SET  password=?,phone=?,avatar_referense=?,login=?,first_name=?,last_name=?,age=? WHERE id=?";
    static MysqlUserDetailsDAO getInstance(){
        if(userDetailsDAO==null){
            userDetailsDAO=new MysqlUserDetailsDAO();
        }
        return userDetailsDAO;
    }
    public MysqlUserDetailsDAO(){
    }
    @Override
    public Integer create(UserDetails userDetails) throws DAOException {
        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer key;
        try{
            preparedStatement=connection.prepareStatement(CREATE_USER_DETAILS,preparedStatement.RETURN_GENERATED_KEYS);
            int k=0;
            preparedStatement.setInt(++k,userDetails.getId());
            preparedStatement.setString(++k,userDetails.getPassword());
            preparedStatement.setString(++k,userDetails.getPhone());
            preparedStatement.setString(++k,userDetails.getAvatarReferense());
            preparedStatement.setString(++k,userDetails.getLogin());
            preparedStatement.setString(++k,userDetails.getFirstName());
            preparedStatement.setString(++k,userDetails.getLastName());
            preparedStatement.setInt(++k,userDetails.getAge());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                key = resultSet.getInt(1);
            } else {
                throw new SQLException("Creating userDetails failed, no ID obtained.");
            }

        } catch (SQLException e){
            LOG.info("Can not create userDetails.");
            throw new DAOException(e.getMessage(), e);
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return key;
    }
    @Override
    public UserDetails read(Integer key) throws DAOException {
        UserDetails userDetails=new UserDetails();
        Connection connection=ConnectionHolder.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{
            preparedStatement=connection.prepareStatement(GET_USER_DETAILS);
            preparedStatement.setInt(1,key);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                userDetails.setId(resultSet.getInt("id"));
                userDetails.setPassword(resultSet.getString("password"));
                userDetails.setPhone(resultSet.getString("phone"));
                userDetails.setAvatarReferense(resultSet.getString("avatar_referense"));
                userDetails.setLogin(resultSet.getString("login"));
                userDetails.setFirstName(resultSet.getString("first_name"));
                userDetails.setLastName(resultSet.getString("last_name"));
                userDetails.setAge(resultSet.getInt("age"));
            }
        }catch (SQLException e){
            LOG.info("Can not create userDetails." );
            throw new DAOException(e.getMessage(), e);
        }finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return userDetails;
    }
    @Override
    public void update(UserDetails userDetails) throws SQLException {
        PreparedStatement preparedStatement=null;
        Connection connection=ConnectionHolder.getConnection();
        try {
            preparedStatement = connection.prepareStatement(UPDATE_USER_DETAILS);
            int k = 0;

            preparedStatement.setString(++k,userDetails.getPassword());
            preparedStatement.setString(++k,userDetails.getPhone());
            preparedStatement.setString(++k,userDetails.getAvatarReferense());
            preparedStatement.setString(++k,userDetails.getLogin());
            preparedStatement.setString(++k,userDetails.getFirstName());
            preparedStatement.setString(++k,userDetails.getLastName());
            preparedStatement.setInt(++k,userDetails.getAge());
            preparedStatement.setInt(++k,userDetails.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }
    @Override
    public void delete(Integer key) throws DAOException {
        PreparedStatement preparedStatement=null;
        Connection connection=ConnectionHolder.getConnection();
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ORDER_USER_DETAILS);
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }





}
