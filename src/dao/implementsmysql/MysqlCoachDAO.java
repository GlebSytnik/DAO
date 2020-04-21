package dao.implementsmysql;

import dao.CoachDAO;
import dao.exception.DAOException;
import db.ConnectionHolder;
import db.DBUtil;
import entity.Coach;

import java.sql.*;
import java.util.logging.Logger;

public class MysqlCoachDAO  implements CoachDAO {
    private static MysqlCoachDAO coachDAO;
    private static final Logger LOG = Logger.getLogger(MysqlCoachDAO.class.getName());


    private static final String CREATE_COACH = "INSERT INTO coach(id,qualification,user_details_iduser_details,timetables_id)VALUES(?,?,?,?)";
    private static final String DELETE_BY_ORDER_COACH = "DELETE  FROM coach WHERE id=?";
    private static final String GET_COACH = "SELECT * FROM coach WHERE id = ?";
    private static final String UPDATE_COACH = "UPDATE coach SET  qualification=?,user_details_iduser_details=?,timetables_id=? WHERE id=?";

    static MysqlCoachDAO getInstance(){
        if(coachDAO==null){
            coachDAO=new MysqlCoachDAO();
        }
        return coachDAO;
    }
    private MysqlCoachDAO(){
    }

    @Override
    public Integer create(Coach coach) throws DAOException {
        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer key;
        try{
            preparedStatement=connection.prepareStatement(CREATE_COACH,preparedStatement.RETURN_GENERATED_KEYS);
            int k=0;
            preparedStatement.setInt(++k,coach.getId());
            preparedStatement.setString(++k,coach.getQualification());
            preparedStatement.setInt(++k,coach.getUserdetailsId());
            preparedStatement.setInt(++k,coach.getTimetablesid());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                key = resultSet.getInt(1);
            } else {
                throw new SQLException("Creating coach failed, no ID obtained.");
            }

        } catch (SQLException e){
            LOG.info("Can not create bill.");
            throw new DAOException(e.getMessage(), e);
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return key;
    }

    @Override
    public Coach read(Integer key) throws DAOException {
        Coach coach=new Coach();
        Connection connection=ConnectionHolder.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{
            preparedStatement=connection.prepareStatement(GET_COACH);
            preparedStatement.setInt(1,key);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                coach.setId(resultSet.getInt("id"));
                coach.setQualification(resultSet.getString("qualification"));
                coach.setuserdetailsId(resultSet.getInt("user_details_iduser_details"));
                coach.setTimetables_id(resultSet.getInt("timetables_id"));
            }
        }catch (SQLException e){
            LOG.info("Can not create bill." );
            throw new DAOException(e.getMessage(), e);
        }finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return coach;
    }

    @Override
    public void update(Coach coach) throws DAOException, SQLException {
        PreparedStatement preparedStatement=null;
        Connection connection=ConnectionHolder.getConnection();
        try {
            preparedStatement = connection.prepareStatement(UPDATE_COACH);
            int k = 0;

            preparedStatement.setString(++k,coach.getQualification());
            preparedStatement.setInt(++k,coach.getUserdetailsId());
            preparedStatement.setInt(++k,coach.getTimetablesid());
            preparedStatement.setInt(++k,coach.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }

    @Override
    public void delete(Integer key) throws DAOException, SQLException {
        PreparedStatement preparedStatement=null;
        Connection connection=ConnectionHolder.getConnection();
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ORDER_COACH);
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }
}
