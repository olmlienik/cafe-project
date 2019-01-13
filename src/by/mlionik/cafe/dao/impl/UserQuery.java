package by.mlionik.cafe.dao.impl;

/**
 * The type User query.
 */
class UserQuery {

    /**
     * Instantiates a new user query.
     */
    private UserQuery() {
    }

    /**
     * The Insert user.
     */
    static final String SQL_INSERT_USER = "INSERT INTO\n" +
            "`cafe`.`user`(`login`, `password`, `email`)\n" +
            "VALUES (?, ?, ?)";

    /**
     * The Select user by id.
     */
    static final String SQL_SELECT_USER_BY_ID = "SELECT \n" +
            "    `user`.`id_user`,\n" +
            "    `user`.`role`,\n" +
            "    `user`.`login`,\n" +
            "    `user`.`password`,\n" +
            "    `user`.`email`,\n" +
            "    `user`.`loyalty_points`,\n" +
            "    `user`.`balance`,\n" +
            "    `user`.`is_banned`,\n" +
            "    `user`.`is_deleted`\n" +
            "FROM\n" +
            "    `cafe`.`user`\n" +
            "WHERE\n" +
            "    `user`.`id_user` = ?\n";

    /**
     * The Update user by id.
     */
    static final String SQL_UPDATE_USER = "UPDATE `cafe`.`user` \n" +
            "SET \n" +
            "    `user`.`email` = ?\n" +
            "WHERE\n" +
            "    `id_user` = ?" +
            "     AND `user`.`is_deleted` = 0";

    /**
     * The Update user balance.
     */
    static final String SQL_UPDATE_USER_BALANCE = "UPDATE `cafe`.`user` \n" +
            "SET \n" +
            "    `user`.`balance` = ?\n" +
            "WHERE\n" +
            "    `user`.`id_user` = ?" +
            "     AND `user`.`is_deleted` = 0";

    /**
     * The Update user login.
     */
    static final String SQL_UPDATE_USER_LOGIN = "UPDATE `cafe`.`user` \n" +
            "SET \n" +
            "    `user`.`login` = ?\n" +
            "WHERE\n" +
            "    `user`.`id_user` = ?" +
            "     AND `user`.`is_deleted` = 0";

    /**
     * The Update user ban state.
     */
    static final String SQL_UPDATE_USER_BAN = "UPDATE `cafe`.`user`\n" +
            "SET `user`.`is_banned` = ?\n" +
            "WHERE `user`.`id_user` = ? ";

    /**
     * The Update user password.
     */
    static final String SQL_UPDATE_USER_PASSWORD = "UPDATE `cafe`.`user` \n" +
            "SET \n" +
            "    `user`.`password` = ?\n" +
            "WHERE\n" +
            "    `user`.`id_user` = ?" +
            "     AND `user`.`is_deleted` = 0";

    /**
     * The Update user loyalty points.
     */
    static final String SQL_UPDATE_USER_LOYALTY_POINTS = "UPDATE `cafe`.`user` \n" +
            "SET `user`.`loyalty_points` = ?\n" +
            "WHERE `user`.`id_user` = ? AND `user`.`is_deleted` = 0";

    /**
     * The Delete user by id.
     */
    static final String SQL_DELETE_USER_BY_ID = "UPDATE `cafe`.`user` \n" +
            "SET \n" +
            "    `is_deleted` = 1\n" +
            "WHERE\n" +
            "    `id_user` = ?";

    /**
     * The Select user by login.
     */
    static final String SQL_SELECT_USER_BY_LOGIN = "SELECT \n" +
            "    `user`.`id_user`,\n" +
            "    `user`.`role`,\n" +
            "    `user`.`login`,\n" +
            "    `user`.`password`,\n" +
            "    `user`.`email`,\n" +
            "    `user`.`loyalty_points`,\n" +
            "    `user`.`balance`,\n" +
            "    `user`.`is_banned`,\n" +
            "    `user`.`is_deleted`\n" +
            "FROM\n" +
            "    `cafe`.`user`\n" +
            "WHERE\n" +
            "    `user`.`login` = ?\n" +
            "        AND `user`.`is_deleted` = 0";

    /**
     * The Select user by login and password.
     */
    static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT \n" +
            "    `user`.`id_user`,\n" +
            "    `user`.`role`,\n" +
            "    `user`.`login`,\n" +
            "    `user`.`password`,\n" +
            "    `user`.`email`,\n" +
            "    `user`.`loyalty_points`,\n" +
            "    `user`.`balance`,\n" +
            "    `user`.`is_banned`,\n" +
            "    `user`.`is_deleted`\n" +
            "FROM\n" +
            "    `cafe`.`user`\n" +
            "WHERE\n" +
            "    `user`.`login` = ?\n" +
            "        AND `user`.`password` = ?\n" +
            "        AND `user`.`is_deleted` = 0";


    /**
     * The Select unbanned users.
     */
    static final String SQL_SELECT_UNBANNED_USERS = "SELECT\n" +
            "`user`.`id_user`,\n" +
            "`user`.`role`,\n" +
            "`user`.`login`,\n" +
            "`user`.`password`,\n" +
            "`user`.`email`,\n" +
            "`user`.`loyalty_points`,\n" +
            "`user`.`balance`,\n" +
            "`user`.`is_banned`,\n" +
            "`user`.`is_deleted`\n" +
            "FROM `cafe`.`user`\n" +
            "WHERE `user`.`is_deleted` = 0 AND `user`.`is_banned` = 0";

    /**
     * The Select banned users.
     */
    static final String SQL_SELECT_BANNED_USERS = "SELECT\n" +
            "`user`.`id_user`,\n" +
            "`user`.`role`,\n" +
            "`user`.`login`,\n" +
            "`user`.`password`,\n" +
            "`user`.`email`,\n" +
            "`user`.`loyalty_points`,\n" +
            "`user`.`balance`,\n" +
            "`user`.`is_banned`,\n" +
            "`user`.`is_deleted`\n" +
            "FROM `cafe`.`user`\n" +
            "WHERE `user`.`is_deleted` = 0 AND `user`.`is_banned` = 1";
}
