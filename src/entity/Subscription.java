package entity;

import dao.SubscriptionDAO;
import dao.exception.DAOException;

public class Subscription implements SubscriptionDAO {
    private int  id;
    private String typesubscription;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypesubscription() {
        return typesubscription;
    }

    public void setTypesubscription(String typesubscription) {
        this.typesubscription = typesubscription;
    }

    @Override
    public Integer create(Subscription object) throws DAOException {
        return null;
    }

    @Override
    public Subscription read(Integer key) throws DAOException {
        return null;
    }

    @Override
    public void update(Subscription object) throws DAOException {

    }

    @Override
    public void delete(Integer key) throws DAOException {

    }
}
