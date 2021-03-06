package dao.implementsmysql;

import dao.TrainingDAO;
import dao.exception.DAOException;
import db.ConnectionHolder;
import db.DBUtil;
import entity.Subscription;
import entity.Training;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MysqlTrainingDAO implements TrainingDAO {
    private static MysqlTrainingDAO trainingDAO;
    private static final Logger LOG = Logger.getLogger(MysqlSubscriptionDAO.class.getName());

    private static final String CREATE_TRAINING = "INSERT INTO training(id,type_training)VALUES(?,?)";
    private static final String DELETE_BY_ORDER_TRAINING = "DELETE  FROM training WHERE id=?";
    private static final String GET_TRAINING = "SELECT * FROM training WHERE id = ?";
    private static final String UPDATE_TRAINING= "UPDATE training SET  type_training=? WHERE id=?";
    static MysqlTrainingDAO getInstance(){
        if(trainingDAO==null){
            trainingDAO=new MysqlTrainingDAO();
        }
        return trainingDAO;
    }
    private MysqlTrainingDAO(){
    }
    @Override
    public Integer create(Training training) throws DAOException {
        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer key;
        try{
            preparedStatement=connection.prepareStatement(CREATE_TRAINING,preparedStatement.RETURN_GENERATED_KEYS);
            int k=0;
            preparedStatement.setInt(++k,training.getId());
            preparedStatement.setString(++k,training.getTypeTraining());
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                key = resultSet.getInt(1);
            } else {
                throw new SQLException("Creating training failed, no ID obtained.");
            }

        } catch (SQLException e){
            LOG.info("Can not create training.");
            throw new DAOException(e.getMessage(), e);
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return key;
    }
    @Override
    public Training read(Integer key) throws DAOException {
        Training training=new Training();
        Connection connection=ConnectionHolder.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{
            preparedStatement=connection.prepareStatement(GET_TRAINING);
            preparedStatement.setInt(1,key);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                training.setId(resultSet.getInt("id"));
                training.setTypeTraining(resultSet.getString("type_training"));
            }
        }catch (SQLException e){
            LOG.info("Can not create training." );
            throw new DAOException(e.getMessage(), e);
        }finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return training;
    }
    @Override
    public void update(Training training) throws DAOException, SQLException {
        PreparedStatement preparedStatement=null;
        Connection connection=ConnectionHolder.getConnection();
        try {
            preparedStatement = connection.prepareStatement(UPDATE_TRAINING);
            int k = 0;

            preparedStatement.setString(++k,training.getTypeTraining());
            preparedStatement.setInt(++k,training.getId());
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
            preparedStatement = connection.prepareStatement(DELETE_BY_ORDER_TRAINING);
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }
}
