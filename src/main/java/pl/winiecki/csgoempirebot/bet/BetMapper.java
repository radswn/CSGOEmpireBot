package pl.winiecki.csgoempirebot.bet;

import java.time.LocalDateTime;

public class BetMapper {

    static BetDto toDto(Bet bet){
        Long id = bet.getId();
        double amount = bet.getAmount();
        double currentBalance = bet.getCurrentBalance();
        String betSide = bet.getBetSide();
        String result = bet.getResult();
        String win;
        LocalDateTime dateTime = bet.getTime();

        if(bet.isWin())
            win = "win";
        else
            win = "lose";

        return new BetDto(id, amount, currentBalance, betSide, result, win, dateTime);
    }
}
