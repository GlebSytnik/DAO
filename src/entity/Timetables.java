package entity;

import dao.TimetablesDAO;
import dao.exception.DAOException;

import java.util.Objects;

public class Timetables implements TimetablesDAO {
    private int id;
    private String dataTime;
    private String dataTime2;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getDataTime2() {
        return dataTime2;
    }

    public void setDataTime2(String dataTime2) {
        this.dataTime2 = dataTime2;
    }

    @Override
    public String toString() {
        return "TimetablesDAO{" +
                "id=" + id +
                ", dataTime='" + dataTime + '\'' +
                ", dataTime2='" + dataTime2 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Timetables)) return false;
        Timetables that = (Timetables) o;
        return getId() == that.getId() &&
                Objects.equals(getDataTime(), that.getDataTime()) &&
                Objects.equals(getDataTime2(), that.getDataTime2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDataTime(), getDataTime2());
    }

    @Override
    public Integer create(Timetables object) throws DAOException {
        return null;
    }

    @Override
    public Timetables read(Integer key) throws DAOException {
        return null;
    }

    @Override
    public void update(Timetables object) throws DAOException {

    }

    @Override
    public void delete(Integer key) throws DAOException {

    }
}
