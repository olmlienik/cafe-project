package by.mlionik.cafe.entity;

/**
 * The type Review.
 */
public class Review extends Entity {
    private int id;
    private int idClient;
    private String login;
    private String body;
    private String date;

    /**
     * Instantiates a new review.
     */
    public Review() {
    }

    /**
     * Instantiates a new review.
     *
     * @param idClient the id client
     * @param body     the body
     * @param date     the date
     */
    public Review(int idClient, String body, String date) {
        this.idClient = idClient;
        this.body = body;
        this.date = date;
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
     * Gets the id client.
     *
     * @return the id client
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * Sets the id client.
     *
     * @param idClient the new id client
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * Gets the body.
     *
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body.
     *
     * @param body the new body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Gets the date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date.
     *
     * @param date the new date
     */
    public void setDate(String date) {
        this.date = date;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (id != review.id) return false;
        if (idClient != review.idClient) return false;
        if (login != null ? !login.equals(review.login) : review.login != null) return false;
        if (body != null ? !body.equals(review.body) : review.body != null) return false;
        return date != null ? date.equals(review.date) : review.date == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idClient;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", login='" + login + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                '}';
    }
}
