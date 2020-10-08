package th.ac.ku.bankaccount.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private int bankId;
    private String Transactiontype;
    private double amount;

    public int getId() {
        return id;
    }

    public int getBankId() {
        return bankId;
    }

    public String getTransactiontype() {
        return Transactiontype;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public void setTransactiontype(String transactiontype) {
        Transactiontype = transactiontype;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
