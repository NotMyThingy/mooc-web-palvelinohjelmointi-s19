package jokes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Transactional
    public void vote(Long id, String value) {
        Vote vote = this.findByJokeId(id);

        if ("up".equals(value)) {
            vote.setUpVotes(vote.getUpVotes() + 1);
        } else if ("down".equals(value)) {
            vote.setDownVotes(vote.getDownVotes() + 1);
        }

        voteRepository.save(vote);
    }

    @Transactional
    public Vote findByJokeId(Long jokeId) {
        Vote vote = voteRepository.findByJokeId(jokeId);
        if (vote == null) {
            vote = new Vote(jokeId, 0, 0);
            voteRepository.save(vote);
        }

        return vote;
    }
}
