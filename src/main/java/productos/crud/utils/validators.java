package productos.crud.utils;

public class validators {
    public static <T> boolean compareNumbers(Comparable<T> number, T to, CompareOption compare) {
        switch (compare) {
            case EQUALS -> {
                return number.compareTo(to) == 0;
            }
            case LESSTHAN -> {
                return number.compareTo(to) < 1;
            }
            case GREATHERTHAN -> {
                return number.compareTo(to) > 0;
            }
            case null, default -> {
                return false;
            }
        }
    }

    public static boolean emptyString(String text){
        return text.isBlank();
    }
}
