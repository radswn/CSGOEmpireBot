package pl.winiecki.csgoempirebot.bet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime time;
    private double amount;
    private double currentBalance;
    private String betSide;
    private String result;
    private boolean win;

    public Bet() {
    }

    public Bet(LocalDateTime time, double amount, double currentBalance, String betSide, String result, boolean win) {
        this.time = time;
        this.amount = amount;
        this.currentBalance = currentBalance;
        this.betSide = betSide;
        this.result = result;
        this.win = win;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getBetSide() {
        return betSide;
    }

    public void setBetSide(String betSide) {
        this.betSide = betSide;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", time=" + time +
                ", amount=" + amount +
                ", currentBalance=" + currentBalance +
                ", betSide='" + betSide + '\'' +
                ", result='" + result + '\'' +
                ", win=" + win +
                '}';
    }
}
