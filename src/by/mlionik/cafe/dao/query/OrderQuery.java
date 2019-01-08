package by.mlionik.cafe.dao.query;

public class OrderQuery {
    public static final String SQL_INSERT_ORDER = "INSERT INTO `cafe`.`order`(`id_user`, `delivery_time`, `payment_type`, `state`) VALUES (?, ?, ?, ?)";

    public static final String SQL_INSERT_ORDER_COMPOSITION = "INSERT INTO `cafe`.`order_composition`(`id_order`, `id_dish`) VALUES (?, ?)";

    public static final String SQL_SELECT_ORDERS_IN_PROCESS = "SELECT \n" +
            "`order`.`id_order`, `order`.`id_user`, `order`.`delivery_time`, `order`.`payment_type`,`order`.`state`\n" +
            "FROM `cafe`.`order`\n" +
            "WHERE `order`.`state` = 'in_process';";

    public static final String SQL_SELECT_ORDER_COMPOSITION = "SELECT \n" +
            "`order_composition`.`id`, \n" +
            "`order_composition`.`id_order`, \n" +
            "`order_composition`.`id_dish`\n" +
            "FROM `cafe`.`order_composition`\n" +
            "WHERE `order_composition`.`id_order` = ?";

    public static final String SQL_SELECT_ORDERS_BY_CLIENT_ID ="SELECT\n" +
            "`order`.`id_order`, `order`.`id_user`, `order`.`delivery_time`, `order`.`payment_type`,`order`.`state`\n" +
            "FROM `cafe`.`order`\n" +
            "WHERE `order`.`id_user` = ?";

    public static final String SQL_UPDATE_ORDER_STATE = "UPDATE `cafe`.`order`\n" +
            "SET `order`.`state` = ?\n" +
            "WHERE `order`.`id_order` = ?";
}
