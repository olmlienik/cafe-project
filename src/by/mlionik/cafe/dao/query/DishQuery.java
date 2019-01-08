package by.mlionik.cafe.dao.query;

public class DishQuery {
    public static final String SQL_INSERT_DISH = "INSERT INTO\n" +
            "`cafe`.`dish`(`name`, `cost`, `picture`, `category`)\n" +
            "VALUES (?, ?, ?, ?)";

    public static final String SQL_SELECT_DISH_BY_ID = "SELECT \n" +
            "    `dish`.`id_dish`,\n" +
            "    `dish`.`name`,\n" +
            "    `dish`.`cost`,\n" +
            "    `dish`.`picture`,\n" +
            "    `dish`.`category`\n" +
            "FROM\n" +
            "    `cafe`.`dish`\n" +
            "WHERE\n" +
            "    `dish`.`id_dish` = ?\n";

    public static final String SQL_SELECT_ALL_DISHES = "SELECT \n" +
            "    `dish`.`id_dish`,\n" +
            "    `dish`.`name`,\n" +
            "    `dish`.`cost`,\n" +
            "    `dish`.`picture`,\n" +
            "    `dish`.`category`\n" +
            "FROM\n" +
            "    `cafe`.`dish`\n" +
            "WHERE\n" +
            "        `dish`.`is_deleted` = 0";

    public static final String SQL_UPDATE_DISH = "UPDATE `cafe`.`dish` \n" +
            "SET \n" +
            "    `dish`.`name` = ?,\n" +
            "    `dish`.`cost` = ?,\n" +
            "    `dish`.`picture` = ?,\n" +
            "    `dish`.`category` = ?,\n" +
            "WHERE\n" +
            "    `id_dish` = ?" +
            "     AND `dish`.`is_deleted` = 0";

    public static final String SQL_DELETE_DISH_BY_ID = "UPDATE `cafe`.`user` \n" +
            "SET \n" +
            "    `is_deleted` = 1\n" +
            "WHERE\n" +
            "    `id_dish` = ?";


}
