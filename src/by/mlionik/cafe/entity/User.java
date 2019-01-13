package by.mlionik.cafe.entity;

import by.mlionik.cafe.entity.type.RoleType;

import java.util.ArrayList;
import java.util.List;

/**
 * The type User.
 */
public class User extends Entity {
    private int id;
    private boolean isBanned;
    private String login;
    private String password;
    private String email;
    private double loyaltyPoints;
    private double balance;
    private RoleType role;
    private boolean isDeleted;
    private List<Review> reviews = new ArrayList<>();

    /**
     * Instantiates a new user.
     */
    public User() {
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Checks if is banned.
     *
     * @return true, if is banned
     */
    public boolean isBanned() {
        return isBanned;
    }

    /**
     * Sets the checks if is banned.
     *
     * @param banned the new checks if is banned
     */
    public void setIsBanned(boolean banned) {
        isBanned = banned;
    }

    /**
     * Gets the login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login.
     *
     * @param login the new login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the loyalty points.
     *
     * @return the loyalty points
     */
    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * Sets the loyalty points.
     *
     * @param loyaltyPoints the new loyalty points
     */
    public void setLoyaltyPoints(double loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    /**
     * Gets the balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance.
     *
     * @param balance the new balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public RoleType getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(RoleType role) {
        this.role = role;
    }

    /**
     * Checks if deleted.
     *
     * @return true, if is deleted
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Sets the deleted state.
     *
     * @param deleted the new deleted
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * Gets the reviews.
     *
     * @return the reviews
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Sets the reviews.
     *
     * @param reviews the new reviews
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (isBanned != user.isBanned) return false;
        if (Double.compare(user.loyaltyPoints, loyaltyPoints) != 0) return false;
        if (Double.compare(user.balance, balance) != 0) return false;
        if (isDeleted != user.isDeleted) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (role != user.role) return false;
        return reviews != null ? reviews.equals(user.reviews) : user.reviews == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (isBanned ? 1 : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        temp = Double.doubleToLongBits(loyaltyPoints);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", isBanned=" + isBanned +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                ", balance=" + balance +
                ", role=" + role +
                ", isDeleted=" + isDeleted +
                ", reviews=" + reviews +
                '}';
    }
}
