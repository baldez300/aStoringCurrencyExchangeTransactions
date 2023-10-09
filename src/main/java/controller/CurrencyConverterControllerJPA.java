package controller;

import entity.CurrenciesJPA;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Currency;
import model.CurrencyModelJPA; // Updated model
import entity.Transaction;
import dao.TransactionDAO;

import java.util.List;

public class CurrencyConverterControllerJPA {
    private final CurrencyModelJPA currencyModelDB; // Updated model
    private final TransactionDAO transactionDAO; // Add this line

    public CurrencyConverterControllerJPA() {
        currencyModelDB = new CurrencyModelJPA(); // Updated model
        transactionDAO = new TransactionDAO(); // Initialize TransactionDAO
    }

    public ObservableList<Currency> getCurrencies() {
        List<CurrenciesJPA> currencyList = currencyModelDB.getCurrencies(); // Updated model
        ObservableList<Currency> observableCurrencyList = FXCollections.observableArrayList();

        for (CurrenciesJPA currencies : currencyList) {
            // Convert Currencies to Currency
            Currency currency = new Currency(
                    currencies.getAbbreviation(),
                    currencies.getCurrencyName(),
                    currencies.getConversionRate()
            );
            observableCurrencyList.add(currency);
        }

        return observableCurrencyList;
    }

    public double convertCurrency(double amount, Currency sourceCurrency, Currency targetCurrency) {
        if (sourceCurrency == null || targetCurrency == null) {
            return 0.0; // Handle invalid input gracefully
        }

        // Get the conversion rates
        double sourceRate = sourceCurrency.getConversionRate();
        double targetRate = targetCurrency.getConversionRate();

        // Calculate the converted amount
        return (amount / sourceRate) * targetRate;
    }

    // Updated method to persist new currency
    public void persist(CurrenciesJPA newCurrency) {
        currencyModelDB.persist(newCurrency);
    }

    // Updated method to persist new transaction
    public void storeTransaction(Currency sourceCurrency, Currency targetCurrency, double amount) {
        Transaction transaction = new Transaction();

        // Find the corresponding CurrenciesJPA objects based on Currency objects
        CurrenciesJPA sourceCurrencyJPA = findCurrenciesJPAByAbbreviation(sourceCurrency.getAbbreviation());
        CurrenciesJPA targetCurrencyJPA = findCurrenciesJPAByAbbreviation(targetCurrency.getAbbreviation());

        if (sourceCurrencyJPA != null && targetCurrencyJPA != null) {
            transaction.setSourceCurrency(sourceCurrencyJPA);
            transaction.setTargetCurrency(targetCurrencyJPA);
            transaction.setAmount(amount);

            transactionDAO.persist(transaction);
        }
    }

    // Helper method to find CurrenciesJPA by abbreviation
    private CurrenciesJPA findCurrenciesJPAByAbbreviation(String abbreviation) {
        List<CurrenciesJPA> currenciesJPAList = currencyModelDB.getCurrencies(); // Updated model

        for (CurrenciesJPA currenciesJPA : currenciesJPAList) {
            if (currenciesJPA.getAbbreviation().equals(abbreviation)) {
                return currenciesJPA;
            }
        }

        return null;
    }

}
