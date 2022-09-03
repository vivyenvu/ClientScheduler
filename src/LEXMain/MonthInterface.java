package LEXMain;

/**
 * Interface for the lambda that takes an integer and converts it to its month name. Eg. 1 = JANUARY.
 * Used in Contacts Schedules report.
 */
public interface MonthInterface {
    String getMonthName (int i);
}
