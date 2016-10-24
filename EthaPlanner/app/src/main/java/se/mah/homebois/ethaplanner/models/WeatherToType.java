package se.mah.homebois.ethaplanner.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Patrik on 2016-10-23.
 */

public class WeatherToType {

    private static Map<Integer, String[]> list;

    //public static void init()
    static {
        list = new HashMap<>();
        list.put(0, new String[]{"Aniskryddad sprit", "Tequila och Mezcal"});
        list.put(1, new String[]{"Aniskryddad sprit", "Tequila och Mezcal"});
        list.put(2, new String[]{"Aniskryddad sprit", "Tequila och Mezcal"});
        list.put(3, new String[]{"Aniskryddad sprit", "Tequila och Mezcal"});
        list.put(4, new String[]{"Aniskryddad sprit", "Tequila och Mezcal"});
        list.put(5, new String[]{"sprit", "Öl", "vin", "Glögg"});
        list.put(6, new String[]{"sprit", "Öl", "vin", "Cider"});
        list.put(7, new String[]{"sprit", "Öl", "vin", "Cider"});
        list.put(8, new String[]{"Glögg", "sprit", "vin"});
        list.put(9, new String[]{"vin", "Öl", "Whisky"});
        list.put(10, new String[]{"Glögg", "sprit", "vin"});
        list.put(11, new String[]{"Vin", "Tequila och Mezcal", "Likör"});
        list.put(12, new String[]{"Vin", "Tequila och Mezcal", "Likör"});
        list.put(13, new String[]{"Glögg", "Likör", "Tequila och Mezcal", "Whisky"});
        list.put(14, new String[]{"Glögg", "Likör", "Tequila och Mezcal", "Whisky"});
        list.put(15, new String[]{"Glögg", "Likör", "Tequila och Mezcal", "Whisky"});
        list.put(16, new String[]{"Glögg", "Likör", "Tequila och Mezcal", "Whisky"});
        list.put(17, new String[]{"Glögg", "Likör", "Tequila och Mezcal", "Whisky"});
        list.put(18, new String[]{"Rom", "Tequila och Mezcal", "Whisky", "Öl", "vin"});
        list.put(19, new String[]{"Rom", "Tequila och Mezcal", "Whisky", "Öl", "vin"});
        list.put(20, new String[]{"Rom", "Tequila och Mezcal", "Whisky", "Öl", "vin"});
        list.put(21, new String[]{"Rom", "Tequila och Mezcal", "Whisky", "Öl", "vin"});
        list.put(22, new String[]{"Rom", "Tequila och Mezcal", "Whisky", "Öl", "vin"});
        list.put(23, new String[]{"Rom", "vin", "Whisky"});
        list.put(24, new String[]{"Rom", "vin", "Whisky"});
        list.put(25, new String[]{"Aniskryddad sprit", "Aperitif"});
        list.put(26, new String[]{"vin", "Drinkar och Cocktails", "Öl"});
        list.put(27, new String[]{"vin", "Drinkar och Cocktails", "Öl"});
        list.put(28, new String[]{"vin", "Drinkar och Cocktails", "Öl"});
        list.put(29, new String[]{"vin", "Drinkar och Cocktails", "Öl"});
        list.put(30, new String[]{"vin", "Drinkar och Cocktails", "Öl"});
        list.put(31, new String[]{"Cognac", "Öl", "Rom", "Whisky"});
        list.put(32, new String[]{"Öl", "Cider"});
        list.put(33, new String[]{"vin", "Öl"});
        list.put(34, new String[]{"vin", "Öl"});
        list.put(35, new String[]{"vin", "Öl", "Whisky"});
        list.put(36, new String[]{"Öl", "Cider"});
        list.put(37, new String[]{"Aniskryddad sprit", "Tequila och Mezcal", "Rom", "Whisky", "Cognac"});
        list.put(38, new String[]{"Aniskryddad sprit", "Tequila och Mezcal", "Rom", "Whisky", "Cognac"});
        list.put(39, new String[]{"Aniskryddad sprit", "Tequila och Mezcal", "Rom", "Whisky", "Cognac"});
        list.put(40, new String[]{"vin", "Tequila och Mezcal", "Likör"});
        list.put(41, new String[]{"Glögg", "Likör", "Tequila och Mezcal", "Whisky"});
        list.put(42, new String[]{"Glögg", "Likör", "Tequila och Mezcal", "Whisky", "Cognac"});
        list.put(43, new String[]{"Glögg", "Likör", "Tequila och Mezcal", "Whisky", "Cognac"});
        list.put(44, new String[]{"Vin", "Tequila och Mezcal", "Likör"});
        list.put(45, new String[]{"Aniskryddad sprit", "Tequila och Mezcal"});
        list.put(46, new String[]{"Vin", "Tequila och Mezcal", "Likör"});
        list.put(47, new String[]{"Aniskryddad sprit", "Tequila och Mezcal"});
        list.put(3200, new String[]{"Öl"});
    }

    public static String[] getTypes(int code) {
        return list.get(code);
    }
}
