package pl.winiecki.csgoempirebot.bet;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    List<Bet> findAllByTimeAfter(LocalDateTime time, Pageable pageable);

}
