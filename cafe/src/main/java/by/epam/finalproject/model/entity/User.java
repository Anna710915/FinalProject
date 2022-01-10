package by.epam.finalproject.model.entity;

import java.time.LocalDate;

public class User extends CustomEntity {
    public enum UserRole {
        CLIENT(1), ADMIN(2), GUEST(3);
        private long roleId;

        UserRole(long id){
            roleId = id;
        }

        public long getRoleId(){
            return roleId;
        }
    }
    public enum UserState {
        NEW(1), ACTIVE(2), INACTIVE(3), BLOCKED(4), UNBLOCKED(5);
        private long stateId;

        UserState (long id){
            stateId = id;
        }

        public long getStateId(){
            return stateId;
        }
    }
    private long userId;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private int phoneNumber;
    private LocalDate birthday;
    private long discountId;
    private UserRole role;
    private UserState state;

    public User(){}

    public User(long userId, String firstName, String lastName, String login,
                String password, String email, int phoneNumber, LocalDate birthday, long discountId, UserRole role, UserState state) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.discountId = discountId;
        this.role = role;
        this.state = state;
    }

    public User(String firstName, String lastName, String login,
                String password, String email, int phoneNumber, LocalDate birthday, long discountId, UserRole role, UserState state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.discountId = discountId;
        this.role = role;
        this.state = state;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (phoneNumber != user.phoneNumber) return false;
        if (discountId != user.discountId) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
        if (role != user.role) return false;
        return state == user.state;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + phoneNumber;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (int) (discountId ^ (discountId >>> 32));
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", birthday=").append(birthday);
        sb.append(", discountId=").append(discountId);
        sb.append(", role=").append(role);
        sb.append(", state=").append(state);
        sb.append('}');
        return sb.toString();
    }
}
