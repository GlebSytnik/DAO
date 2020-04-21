package entity;

import java.util.Objects;

public class CoachNumber {
    private Coach coach;
    private String number;

    @Override
    public String toString() {
        return "CoachNumber{" +
                "coach=" + coach +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoachNumber)) return false;
        CoachNumber that = (CoachNumber) o;
        return Objects.equals(getCoach(), that.getCoach()) &&
                Objects.equals(getNumber(), that.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCoach(), getNumber());
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
