package by.mlionik.cafe.dao;

public class UserQuery {
    public static final String SQL_INSERT_USER = "INSERT INTO\n" +
            "`cafe`.`user`(`login`, `password`, `email`)\n" +
            "VALUES (?, ?, ?)";

    public static final String SQL_SELECT_USER_BY_ID = "SELECT \n" +
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

    public static final String SQL_UPDATE_USER = "UPDATE `cafe`.`user` \n" +
            "SET \n" +
            "    `user`.`email` = ?\n" +
            "WHERE\n" +
            "    `id_user` = ?" +
            "     AND `user`.`is_deleted` = 0";

    public static final String SQL_UPDATE_USER_BALANCE = "UPDATE `cafe`.`user` \n" +
            "SET \n" +
            "    `user`.`balance` = ?\n" +
            "WHERE\n" +
            "    `user`.`id_user` = ?" +
            "     AND `user`.`is_deleted` = 0";


    public static final String SQL_UPDATE_USER_LOGIN = "UPDATE `cafe`.`user` \n" +
            "SET \n" +
            "    `user`.`login` = ?\n" +
            "WHERE\n" +
            "    `user`.`id_user` = ?" +
            "     AND `user`.`is_deleted` = 0";

    public static final String SQL_UPDATE_USER_PASSWORD = "UPDATE `cafe`.`user` \n" +
            "SET \n" +
            "    `user`.`password` = ?\n" +
            "WHERE\n" +
            "    `user`.`id_user` = ?" +
            "     AND `user`.`is_deleted` = 0";

    public static final String SQL_DELETE_USER_BY_ID = "UPDATE `cafe`.`user` \n" +
            "SET \n" +
            "    `is_deleted` = 1\n" +
            "WHERE\n" +
            "    `id_user` = ?";


    public static final String SQL_SELECT_USER_BY_LOGIN = "SELECT \n" +
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

    public static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT \n" +
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

}
