package by.mlionik.cafe.util;

import java.io.File;
import java.util.regex.Pattern;

public class DishValidator {
    private static final String COST_PATTERN =  "([\\d]{1,3}((\\.)?([\\d]){1,2})?)";
    private static final String PICTURE_NAME_PATTERN = "(.*/)*.+\\.(png|jpg|jpeg|PNG|JPG)$";
    private static final String IMAGES_FOLDER_REAL_PATH = "E:\\cafe\\out\\artifacts\\cafe_war_exploded\\images\\";

    public static boolean isValidDishName(String dishName){
        return true;
    }

    public static boolean isExistingPicture(String pictureName){
        if (Pattern.matches(PICTURE_NAME_PATTERN, pictureName))
        {
            File file = new File(IMAGES_FOLDER_REAL_PATH + pictureName);
            return (file.exists());
        }
        return false;
    }

    public static boolean isValidCost(String cost){
        return Pattern.matches(COST_PATTERN, cost);
    }
}
