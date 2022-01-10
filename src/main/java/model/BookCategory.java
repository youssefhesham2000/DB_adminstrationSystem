package model;

public enum BookCategory {
    SCIENCE,
    ART,
    RELIGION,
    HISTORY,
    GEOGRAPHY;

    public static BookCategory getBookCategory(int category) {
        return switch (category) {
            case 0 -> SCIENCE;
            case 1 -> ART;
            case 2 -> RELIGION;
            case 3 -> HISTORY;
            case 4 -> GEOGRAPHY;
            default -> null;
        };
    }

    public static String getBookCategoryText(int category) {
        return switch (category) {
            case 0 -> "Science";
            case 1 -> "Art";
            case 2 -> "Religion";
            case 3 -> "History";
            case 4 -> "Geography";
            default -> null;
        };
    }

    public static int getCategoryIndex(BookCategory category) {
        return switch (category) {
            case SCIENCE -> 0;
            case ART -> 1;
            case RELIGION -> 2;
            case HISTORY -> 3;
            case GEOGRAPHY -> 4;
        };
    }
}
