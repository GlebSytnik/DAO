package entity;

import dao.UserDetailsDAO;
import dao.exception.DAOException;

import java.util.Objects;

public class UserDetails implements UserDetailsDAO {
    private  int id;
    private String password;
    private String phone;
    private String avatarReferense;
    private String login;
    private String firstName;
    private String lastName;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatarReferense() {
        return avatarReferense;
    }

    public void setAvatarReferense(String avatarReferense) {
        this.avatarReferense = avatarReferense;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", avatarReferense='" + avatarReferense + '\'' +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetails)) return false;
        UserDetails that = (UserDetails) o;
        return getId() == that.getId() &&
                getAge() == that.getAge() &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getPhone(), that.getPhone()) &&
                Objects.equals(getAvatarReferense(), that.getAvatarReferense()) &&
                Objects.equals(getLogin(), that.getLogin()) &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPassword(), getPhone(), getAvatarReferense(), getLogin(), getFirstName(), getLastName(), getAge());
    }

    @Override
    public Integer create(UserDetails object) throws DAOException {
        return null;
    }

    @Override
    public UserDetails read(Integer key) throws DAOException {
        return null;
    }

    @Override
    public void update(UserDetails object) throws DAOException {

    }

    @Override
    public void delete(Integer key) throws DAOException {

    }
}
