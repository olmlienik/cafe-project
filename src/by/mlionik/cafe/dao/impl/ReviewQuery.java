package by.mlionik.cafe.dao.impl;

/**
 * The type Review query.
 */
class ReviewQuery {

    /**
     * Instantiates a new review query.
     */
    private ReviewQuery(){

    }

    /**
     * The Insert review.
     */
    static final String SQL_INSERT_REVIEW = "INSERT INTO\n" +
            "`cafe`.`review`(`id_client`, `body`, `date`)\n" +
            "VALUES (?, ?, ?)";

    /**
     * The Select review by id.
     */
    static final String SQL_SELECT_REVIEW_BY_ID = "SELECT \n" +
            "    `review`.`id_review`,\n" +
            "    `review`.`id_client`,\n" +
            "    `review`.`body`,\n" +
            "    `review`.`date`\n" +
            "FROM\n" +
            "    `cafe`.`review`\n" +
            "WHERE\n" +
            "    `review`.`id_review` = ?\n";

    /**
     * The Delete review by id.
     */
    static final String SQL_DELETE_REVIEW_BY_ID = "UPDATE `cafe`.`review` \n" +
            "SET \n" +
            "    `is_deleted` = 1\n" +
            "WHERE\n" +
            "    `id_review` = ?";

    /**
     * The Select all reviews.
     */
    static final String SQL_SELECT_ALL_REVIEWS = "SELECT \n" +
            "`review`.`id_review`,\n" +
            "`review`.`id_client`,\n" +
            "`review`.`body`,\n" +
            "`review`.`date`\n" +
            "FROM `cafe`.`review`\n" +
            "WHERE `review`.`is_deleted` = 0";
}
