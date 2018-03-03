package game.entity.user;

public class CounterImpl implements Counter{
    private int wins;
    private int lose;
    private int draw;

    @Override
    public void win(){
        wins++;
    }

    @Override
    public void lose(){
        lose++;
    }

    @Override
    public void draw() {
        draw++;
    }

    @Override
    public void reset(){
        wins = 0;
        lose = 0;
    }

    @Override
    public int getWinCount() {
        return wins;
    }

    @Override
    public int getLoseCount() {
        return lose;
    }

    @Override
    public int getDrawCount() {
        return draw;
    }

    @Override
    public int getScore() {
        return wins - lose;
    }
}
