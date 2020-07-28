package pl.winiecki.csgoempirebot.bet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BetDto {

    private Long id;
    private String time;
    private String date;
    private int hour;
    private int minute;
    private int second;
    private int day;
    private int month;
    private int year;
    private double amount;
    private double currentBalance;
    private String betSide;
    private String result;
    private String win;

    public BetDto(Long id, double amount, double currentBalance, String betSide, String result, String win, LocalDateTime dateTime) {
        this.id = id;
        this.time = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.date = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.hour = dateTime.getHour();
        this.minute = dateTime.getMinute();
        this.second = dateTime.getSecond();
        this.day = dateTime.getDayOfMonth();
        this.month = dateTime.getMonthValue();
        this.year = dateTime.getYear();
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }
}
