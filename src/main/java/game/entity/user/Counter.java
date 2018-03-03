package game.entity.user;

public interface Counter {
    /**
     * Вызывается при победе. Должен увеличивать счетчик побед.
     */
    void win();

    /**
     * Вызывается при поражении. Должен увеличивать счетчик поражений.
     */
    void lose();

    /**
     * Вызывается при ничьей. Должен увеличивать счетчик ничьих.
     */
    void draw();

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
