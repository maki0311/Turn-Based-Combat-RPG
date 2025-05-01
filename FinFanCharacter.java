public class FinFanCharacter {
    private String name;
    private int health;
    private int attack;
    private static final int MAX_HEALTH = 100; // Assuming 100 is the maximum health

    // Constructor
    public FinFanCharacter(String name, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for health
    public int getHealth() {
        return health;
    }

    // Setter for health
    public void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException("Health cannot be negative.");
        }
        this.health = health;
    }

    // Getter for attack
    public int getAttack() {
        return attack;
    }

    // Setter for attack
    public void setAttack(int attack) {
        if (attack < 0) {
            throw new IllegalArgumentException("Attack cannot be negative.");
        }
        this.attack = attack;
    }

    // Method for taking damage
    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative.");
        }
        health -= damage;
        if (health < 0) {
            health = 0; // Ensures health doesn't drop below zero
        }
    }

    // Calculate damage (can be modified as per game logic)
    public int calcDamage(FinFanCharacter opponent) {
        return getAttack(); // Simple implementation; adjust as needed
    }

    // Heal method
    public void heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Healing amount cannot be negative.");
        }
        health += amount;
        if (health > MAX_HEALTH) {
            health = MAX_HEALTH; // Ensures health doesn't exceed max limit
        }
    }
}
