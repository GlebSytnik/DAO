package dao.implementsmysql;

import dao.VisitsDAO;
import dao.exception.DAOException;
import db.ConnectionHolder;
import db.DBUtil;
import entity.UserDetails;
import entity.Visits;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MysqlVisitsDAO implements VisitsDAO {
    private static MysqlVisitsDAO visitsDAO;
    private static final Logger LOG = Logger.getLogger(MysqlUserDetailsDAO.class.getName());

    private static final String CREATE_VISITS = "INSERT INTO visits(id,training_idt_raining_fk,coach_idcoach_fk,customers_id)VALUES(?,?,?,?)";
    private static final String DELETE_BY_ORDER_VISITS = "DELETE  FROM visits WHERE id=?";
    private static final String GET_VISITS = "SELECT * FROM visits WHERE id = ?";
    private static final String UPDATE_VISITS= "UPDATE visits SET  training_idt_raining_fk=?,coach_idcoach_fk=?,customers_id=? WHERE id=?";
    static MysqlVisitsDAO getInstance(){
        if(visitsDAO==null){
            visitsDAO=new MysqlVisitsDAO();
        }
        return visitsDAO;
    }
    private MysqlVisitsDAO(){
    }
    @Override
    public Integer create(Visits visits) throws DAOException {
        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer key;
        try{
            preparedStatement=connection.prepareStatement(CREATE_VISITS,preparedStatement.RETURN_GENERATED_KEYS);
            int k=0;
            preparedStatement.setInt(++k,visits.getId());
            preparedStatement.setInt(++k,visits.getTrainingId());
            preparedStatement.setInt(++k,visits.getCoachId());
            preparedStatement.setInt(++k,visits.getCustomersId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                key = resultSet.getInt(1);
            } else {
                throw new SQLException("Creating visits failed, no ID obtained.");
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
    public Visits read(Integer key) throws DAOException {
        Visits visits=new Visits();
        Connection connection=ConnectionHolder.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{
            preparedStatement=connection.prepareStatement(GET_VISITS);
            preparedStatement.setInt(1,key);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                visits.setId(resultSet.getInt("id"));
                visits.setTrainingId(resultSet.getInt("training_idt_raining_fk"));
                visits.setCoachId(resultSet.getInt("coach_idcoach_fk"));
                visits.setCustomersId(resultSet.getInt("customers_id"));

            }
        }catch (SQLException e){
            LOG.info("Can not create userDetails." );
            throw new DAOException(e.getMessage(), e);
        }finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return visits;
    }
    @Override
    public void update(Visits visits) throws SQLException {
        PreparedStatement preparedStatement=null;
        Connection connection=ConnectionHolder.getConnection();
        try {
            preparedStatement = connection.prepareStatement(UPDATE_VISITS);
            int k = 0;

            preparedStatement.setInt(++k,visits.getTrainingId());
            preparedStatement.setInt(++k,visits.getCoachId());
            preparedStatement.setInt(++k,visits.getCustomersId());
            preparedStatement.setInt(++k,visits.getId());
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
            preparedStatement = connection.prepareStatement(DELETE_BY_ORDER_VISITS);
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }

}
