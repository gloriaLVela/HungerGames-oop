public class Character {
    private String name;
    private int healthLeft;
    private int sucessfullAttacks;
    private int currentLevel;

    public Character(String name) {
        this.name = name;
        this.healthLeft = 100;
        this.sucessfullAttacks = 0;
        this.currentLevel = 1;
    }

    public void increaseSucessfullAttackTotal() {
        this.sucessfullAttacks += 1;
    }

    public String getName() {
        return this.name;
    }

    public int getHealthLeft(){
        return this.healthLeft;
    }

    public int getCurrentLevel(){
        return this.currentLevel;
    }

    public void decreaseLives(int damage){
        this.healthLeft -= damage;
    }
    public void increaseLives(){
        this.healthLeft += 10;
    }

    public void increaseLevel(){
        if ((this.sucessfullAttacks % 3 == 0) && this.currentLevel < 6){
            this.currentLevel += 1;
        }
    }

}
