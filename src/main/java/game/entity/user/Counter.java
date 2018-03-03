package game.entity.user;

public interface Counter extends SetScore {
    /**
     * Сбрасывает все счетчики
     */
    void reset();

    /**
     * получить количество побед
     * @return количество побед
     */
    int getWinCount();

    /**
     * получить количество поражений
     * @return количество поражений
     */
    int getLoseCount();

    /**
     * Получить количество ничьих
     * @return количество ничьих
     */
    int getDrawCount();

    /**
     * Получить итоговый счет. Логика начисления очков должна лежать внутри методов wins, lose, draw
     * @return итоговый счет
     */
    int getScore();

}
