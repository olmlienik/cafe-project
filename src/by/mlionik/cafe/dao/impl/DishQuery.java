package by.mlionik.cafe.dao.impl;

/**
 * The type Dish query.
 */
class DishQuery {

    /**
     * Instantiates a new dish query.
     */
    private DishQuery() {
    }

    /**
     * The Insert dish.
     */
    static final String SQL_INSERT_DISH = "INSERT INTO\n" +
            "`cafe`.`dish`(`name`, `cost`, `picture`, `category`)\n" +
            "VALUES (?, ?, ?, ?)";

    /**
     * The Select dish by id.
     */
    static final String SQL_SELECT_DISH_BY_ID = "SELECT \n" +
            "    `dish`.`id_dish`,\n" +
            "    `dish`.`name`,\n" +
            "    `dish`.`cost`,\n" +
            "    `dish`.`picture`,\n" +
            "    `dish`.`category`\n" +
            "FROM\n" +
            "    `cafe`.`dish`\n" +
            "WHERE\n" +
            "    `dish`.`id_dish` = ?\n";

    /**
     * The Select all dishes.
     */
    static final String SQL_SELECT_ALL_DISHES = "SELECT \n" +
            "    `dish`.`id_dish`,\n" +
            "    `dish`.`name`,\n" +
            "    `dish`.`cost`,\n" +
            "    `dish`.`picture`,\n" +
            "    `dish`.`category`\n" +
            "FROM\n" +
            "    `cafe`.`dish`\n" +
            "WHERE\n" +
            "        `dish`.`is_deleted` = 0";

    /**
     * The Delete dish by id.
     */
    static final String SQL_DELETE_DISH_BY_ID = "UPDATE `cafe`.`dish` \n" +
            "SET \n" +
            "    `is_deleted` = 1\n" +
            "WHERE\n" +
            "    `id_dish` = ?";
}
