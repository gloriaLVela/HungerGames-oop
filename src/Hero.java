public class Hero extends Character {
    private int potionsLeft;


    Hero(String name, int potionsLeft){
        super(name);
        //Character(name);
        this.potionsLeft = potionsLeft;

    }

    public int getPotionsLeft(){
        return this.potionsLeft;
    };
    public void decreasePotions(){
        this.potionsLeft -=1;
    }

    public void drinkPotion(){
        this.decreasePotions();
        this.increaseLives();
    }

}
