package by.mlionik.cafe.dao;

public class OrderQuery {
    public static final String SQL_INSERT_ORDER = "INSERT INTO\n" +
            "`cafe`.`order`(`id_user`, `delivery_time`, `payment_type`)\n" +
            "VALUES (?, ?, ?)";

    public static final String SQL_INSERT_ORDER_COMPOSITION = "INSERT INTO\n" +
            "`cafe`.`order_composition`(`id_order`, `id_dish`)\n" +
            "VALUES (?, ?)";

    public static final String SQL_SELECT_ORDERS_IN_PROCESS = "SELECT \n" +
            "    `order`.`id_order`,\n" +
            "    `order`.`id_user`,\n" +
            "    `order`.`delivery_time`,\n" +
            "    `order`.`payment_type`,\n" +
            "    `order`.`state`\n" +
            "FROM\n" +
            "    `cafe`.`order`\n" +
            "WHERE\n" +
            "        `order`.`state` = 'in_process'";

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
