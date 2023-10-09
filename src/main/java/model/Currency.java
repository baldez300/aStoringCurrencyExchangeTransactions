package model;

/*
    Assignment: Storing currency exchange transactions
    In the previous assignment, you modified the currency converter application so that it uses JPA to store the currency data in the database. Now, modify the application so that it also stores the currency exchange transactions in the database. An example of a transaction is: convert 100 euros to Swedish crowns.

    For the modification, it is advisable to make a new branch in your Git repository. You may create a new database for the application, or you may choose to use the same database as in the previous assignment.

    Use the following class structure for your entities:

    Class diagram

    As you see from the diagram, each Transaction object is associated to two Currency objects: the source currency and the target currency.

    It is enough to store the transactions in the database. You do not need to modify the application so that it would read the transactions from the database. In general, the observed behaviour of the application should not change from the previous application. The only difference is that the transactions are now stored in the database.

    This time, let JPA construct the schema for you based on your annotations. You can do this by modifying the corresponding property in the persistence.xml file:

    <property name="javax.persistence.schema-generation.database.action" value="drop-and-create"/>
    Each transaction has a transaction id. The transaction id should become the primary key in the database table. Let the database server generate the transaction id automatically.

    Use HeidiSQL to check that the database schema was created correctly. Pay attention to the primary keys and foreign keys: are they correct?

    For this assignment, you get points in the following way:

    Both Currency and Transaction classes are implemented and annotated: 1 point
    The application successfully generates the database schema: 1 point
    There is a new DAO class for the Transaction class with a method for storing a transaction in the database: 1 point
    The handler method for the conversion button calls the new DAO method to store the transaction in the database: 1 point
    The application successfully stores the transactions in the database: 1 point
    The transaction id is generated automatically by the database server: 1 point
*/

public class Currency {
    private final String abbreviation;
    private final String name;
    private final double conversionRate;

    public Currency(String abbreviation, String name, double conversionRate) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.conversionRate = conversionRate;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    @Override
    public String toString() {
        return abbreviation + " - " + name;
    }
}
