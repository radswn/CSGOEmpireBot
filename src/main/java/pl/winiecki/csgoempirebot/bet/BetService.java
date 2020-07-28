package pl.winiecki.csgoempirebot.bet;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BetService {

    private BetRepository betRepository;
    private BetStatsCalculator calculator;

    public BetService(BetRepository betRepository, BetStatsCalculator calculator) {
        this.betRepository = betRepository;
        this.calculator = calculator;
    }

    public List<BetDto> getAll(int pageNumber) {
        return betRepository
                .findAll(PageRequest.of(pageNumber, 50))
                .getContent()
                .stream()
                .map(BetMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BetDto> getBySpecificTime(int pageNumber, String type) {
        List<Bet> bets = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNumber, 50);
        LocalDateTime currentTime = LocalDateTime.now();

        switch (type) {
            case "hour": {
                bets = betRepository.findAllByTimeAfter(currentTime.minusHours(1), pageable);
                break;
            }

            case "8hours": {
                bets = betRepository.findAllByTimeAfter(currentTime.minusHours(8), pageable);
                break;
            }

            case "day": {
                bets = betRepository.findAllByTimeAfter(currentTime.minusDays(1), pageable);
                break;
            }

            case "week": {
                bets = betRepository.findAllByTimeAfter(currentTime.minusWeeks(1), pageable);
                break;
            }

            case "month": {
                bets = betRepository.findAllByTimeAfter(currentTime.minusMonths(1), pageable);
                break;
            }
        }
        return bets
                .stream()
                .map(BetMapper::toDto)
                .collect(Collectors.toList());
    }


    public void addBet(Bet bet) {
        betRepository.save(bet);
    }
}
