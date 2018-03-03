package game.controller;

import game.entity.Result;
import game.entity.hand.*;
import game.entity.user.User;

public class GameController {
    private final User first;
    private final User second;

    public GameController(User first, User second) {
        this.first = first;
        this.second = second;
    }

    public void restart(){
        first.reset();
        second.reset();
    }

    public void firstChoice(HandForm form){
        first.setHand(getHand(form));
    }

    public void secondChoice(HandForm form){
        second.setHand(getHand(form));
    }

    public Result fight(){
        return first.fight(second);
    }

    private Hand getHand(HandForm form){
        if (form == HandForm.ROCK){
            return new Rock();
        }else if (form == HandForm.PAPER){
            return new Paper();
        }else{
            return new Scissors();
        }
    }
}
