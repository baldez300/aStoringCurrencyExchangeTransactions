package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    @ManyToOne
    @JoinColumn(name = "source_currency_id", referencedColumnName = "id")
    private CurrenciesJPA sourceCurrency;

    @ManyToOne
    @JoinColumn(name = "target_currency_id", referencedColumnName = "id")
    private CurrenciesJPA targetCurrency;

    @Column(name = "amount")
    private double amount;

    // Implementing constructors, getters, setters, and other necessary methods.

    public Transaction(CurrenciesJPA sourceCurrency, CurrenciesJPA targetCurrency, double amount, int transactionId) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.amount = amount;
        this.transactionId = transactionId;
    }

    public Transaction() {
    }


    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public CurrenciesJPA getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(CurrenciesJPA sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public CurrenciesJPA getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(CurrenciesJPA targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
