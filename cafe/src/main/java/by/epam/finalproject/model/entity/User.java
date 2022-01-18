package by.epam.finalproject.model.entity;

import java.time.LocalDate;

/**
 * The type User.
 */
public class User extends CustomEntity {
    /**
     * The enum User role.
     */
    public enum UserRole {
        /**
         * Client user role.
         */
        CLIENT(1),
        /**
         * Admin user role.
         */
        ADMIN(2),
        /**
         * Guest user role.
         */
        GUEST(3);
        private long roleId;

        UserRole(long id){
            roleId = id;
        }

        /**
         * Get role id long.
         *
         * @return the long
         */
        public long getRoleId(){
            return roleId;
        }
    }

    /**
     * The enum User state.
     */
    public enum UserState {
        /**
         * New user state.
         */
        NEW(1),
        /**
         * Active user state.
         */
        ACTIVE(2),
        /**
         * Inactive user state.
         */
        INACTIVE(3),
        /**
         * Blocked user state.
         */
        BLOCKED(4),
        /**
         * Unblocked user state.
         */
        UNBLOCKED(5);
        private long stateId;

        UserState (long id){
            stateId = id;
        }

        /**
         * Get state id long.
         *
         * @return the long
         */
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

    /**
     * Instantiates a new User.
     */
    public User(){}

    /**
     * Instantiates a new User.
     *
     * @param userId      the user id
     * @param firstName   the first name
     * @param lastName    the last name
     * @param login       the login
     * @param password    the password
     * @param email       the email
     * @param phoneNumber the phone number
     * @param birthday    the birthday
     * @param discountId  the discount id
     * @param role        the role
     * @param state       the state
     */
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

    /**
     * Instantiates a new User.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param login       the login
     * @param password    the password
     * @param email       the email
     * @param phoneNumber the phone number
     * @param birthday    the birthday
     * @param discountId  the discount id
     * @param role        the role
     * @param state       the state
     */
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

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets birthday.
     *
     * @return the birthday
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Sets birthday.
     *
     * @param birthday the birthday
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Gets discount id.
     *
     * @return the discount id
     */
    public long getDiscountId() {
        return discountId;
    }

    /**
     * Sets discount id.
     *
     * @param discountId the discount id
     */
    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public UserState getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
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
