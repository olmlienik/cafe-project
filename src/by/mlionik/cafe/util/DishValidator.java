package by.mlionik.cafe.util;

import java.io.File;
import java.util.regex.Pattern;

/**
 * The type Dish validator.
 */
public class DishValidator {
    private static final String COST_PATTERN = "([\\d]{1,3}((\\.)([\\d]){1,2})?)";
    private static final String PICTURE_NAME_PATTERN = "(.*/)*.+\\.(png|jpg|jpeg|PNG|JPG)$";
    private static final String IMAGES_FOLDER_REAL_PATH = "E:\\cafe\\out\\artifacts\\cafe_war_exploded\\images\\";
    private static final String DISH_NAME_PATTERN = "\\w+\\s*\\w*";

    /**
     * Checks if the dish name id valid.
     *
     * @param dishName the dish name
     * @return true, if dish name is valid
     */
    public static boolean isValidDishName(String dishName) {
        return Pattern.matches(DISH_NAME_PATTERN, dishName);
    }

    /**
     * Checks if picture exists.
     *
     * @param pictureName the picture name
     * @return true, if picture exists
     */
    public static boolean isExistingPicture(String pictureName) {
        if (Pattern.matches(PICTURE_NAME_PATTERN, pictureName)) {
            File file = new File(IMAGES_FOLDER_REAL_PATH + pictureName);
            return (file.exists());
        }
        return false;
    }

    /**
     * Checks if cost is valid.
     *
     * @param cost the cost
     * @return true, if cost is valid
     */
    public static boolean isValidCost(String cost) {
        return Pattern.matches(COST_PATTERN, cost);
    }
}
