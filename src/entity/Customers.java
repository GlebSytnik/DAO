package entity;

import dao.CustomersDAO;
import dao.exception.DAOException;

import java.util.Objects;

public class Customers implements CustomersDAO {
    private  int id;
    private  int height;
    private  int weight;
    private  int subscriptionId;
    private  int userdetailsId;
    private  int coachId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public int getUserdetailsId() {
        return userdetailsId;
    }

    public void setUserdetailsId(int userdetailsId) {
        this.userdetailsId = userdetailsId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", height=" + height +
                ", weight=" + weight +
                ", subscriptionId=" + subscriptionId +
                ", userdetailsId=" + userdetailsId +
                ", coachId=" + coachId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customers)) return false;
        Customers customers = (Customers) o;
        return getId() == customers.getId() &&
                getHeight() == customers.getHeight() &&
                getWeight() == customers.getWeight() &&
                getSubscriptionId() == customers.getSubscriptionId() &&
                getUserdetailsId() == customers.getUserdetailsId() &&
                getCoachId() == customers.getCoachId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHeight(), getWeight(), getSubscriptionId(), getUserdetailsId(), getCoachId());
    }

    @Override
    public Integer create(Customers object) throws DAOException {
        return null;
    }

    @Override
    public Customers read(Integer key) throws DAOException {
        return null;
    }

    @Override
    public void update(Customers object) throws DAOException {

    }

    @Override
    public void delete(Integer key) throws DAOException {

    }
}
