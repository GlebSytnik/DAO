package entity;

import dao.TrainingDAO;
import dao.exception.DAOException;

import java.util.Objects;

public class Training implements TrainingDAO {
    private  int id;
    private String typeTraining;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeTraining() {
        return typeTraining;
    }

    public void setTypeTraining(String typeTraining) {
        this.typeTraining = typeTraining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Training)) return false;
        Training training = (Training) o;
        return getId() == training.getId() &&
                Objects.equals(getTypeTraining(), training.getTypeTraining());
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", typeTraining='" + typeTraining + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTypeTraining());
    }

    @Override
    public Integer create(Training object) throws DAOException {
        return null;
    }

    @Override
    public Training read(Integer key) throws DAOException {
        return null;
    }

    @Override
    public void update(Training object) throws DAOException {

    }

    @Override
    public void delete(Integer key) throws DAOException {

    }
}
