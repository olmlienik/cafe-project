package by.mlionik.cafe.entity;

import by.mlionik.cafe.entity.type.RoleType;
import java.math.BigDecimal;
import java.util.List;

public class User extends Entity {
    private int id;
    private boolean isBanned;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int loyaltyPoints;
    private BigDecimal balance;
    private RoleType role;
    private boolean isDeleted;

    private List<String> invalidUserInfo;

    public List<String> getInvalidUserInfo() {
        return invalidUserInfo;
    }

    public void setInvalidUserInfo(List<String> invalidUserInfo) {
        this.invalidUserInfo = invalidUserInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setIsBanned(boolean banned) {
        isBanned = banned;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", isBanned=" + isBanned +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                ", balance=" + balance +
                ", role=" + role +
                ", isDeleted=" + isDeleted +
                ", invalidUserInfo=" + invalidUserInfo +
                '}';
    }
}
