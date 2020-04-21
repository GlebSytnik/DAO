package entity;

import dao.CoachDAO;
import dao.exception.DAOException;

import java.util.List;
import java.util.Objects;

public class Coach  {
    private int id;
    private String qualification;
    private int userdetailsId;
    private int timetablesId;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserdetailsId() {
        return userdetailsId;
    }

    public void setuserdetailsId(int userdetailsId) {
        this.userdetailsId = userdetailsId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }



    public int getTimetablesid() {
        return timetablesId;
    }

    public void setTimetables_id(int timetablesId) {
        this.timetablesId = timetablesId;
    }
   // public List<CoachNumber> getNumbers() {
       // return numbers;
   // }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coach)) return false;
        Coach coach = (Coach) o;
        return getId() == coach.getId() &&
                userdetailsId == coach.userdetailsId &&
                timetablesId == coach.timetablesId &&
                Objects.equals(getQualification(), coach.getQualification());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQualification(), userdetailsId, timetablesId);
    }

    @Override
    public String toString() {
        return "Coach{" +
                "id=" + id +
                ", qualification='" + qualification + '\'' +
                ", userdetailsId=" + userdetailsId +
                ", timetablesId=" + timetablesId +
                '}';
    }


}
