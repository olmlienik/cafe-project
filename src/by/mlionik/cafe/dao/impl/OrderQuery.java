package by.mlionik.cafe.dao.impl;

/**
 * The type Order query.
 */
class OrderQuery {

    /**
     * Instantiates a new order query.
     */
    private OrderQuery() {

    }

    /**
     * The Insert order.
     */
    static final String SQL_INSERT_ORDER = "INSERT INTO `cafe`.`order`(`id_user`, `delivery_time`, `payment_type`, `state`) VALUES (?, ?, ?, ?)";

    /**
     * The Insert order composition.
     */
    static final String SQL_INSERT_ORDER_COMPOSITION = "INSERT INTO `cafe`.`order_composition`(`id_order`, `id_dish`) VALUES (?, ?)";

    /**
     * The Select orders in process.
     */
    static final String SQL_SELECT_ORDERS_IN_PROCESS = "SELECT \n" +
            "`order`.`id_order`, `order`.`id_user`, `order`.`delivery_time`, `order`.`payment_type`,`order`.`state`\n" +
            "FROM `cafe`.`order`\n" +
            "WHERE `order`.`state` = 'in_process';";

    /**
     * The Select order composition.
     */
    static final String SQL_SELECT_ORDER_COMPOSITION = "SELECT \n" +
            "`order_composition`.`id`, \n" +
            "`order_composition`.`id_order`, \n" +
            "`order_composition`.`id_dish`\n" +
            "FROM `cafe`.`order_composition`\n" +
            "WHERE `order_composition`.`id_order` = ?";

    /**
     * The Select orders by client id.
     */
    static final String SQL_SELECT_ORDERS_BY_CLIENT_ID = "SELECT\n" +
            "`order`.`id_order`, `order`.`id_user`, `order`.`delivery_time`, `order`.`payment_type`,`order`.`state`\n" +
            "FROM `cafe`.`order`\n" +
            "WHERE `order`.`id_user` = ?";

    /**
     * The Update order state.
     */
    static final String SQL_UPDATE_ORDER_STATE = "UPDATE `cafe`.`order`\n" +
            "SET `order`.`state` = ?\n" +
            "WHERE `order`.`id_order` = ?";
}
