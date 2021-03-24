/**
 * Stores information and represents the possible states an (x, y) position on the game board may be in.
 */
public class Cell extends Position {
    private int isSolid;
    private int containsReward;
    private int containsBonusReward;
    private int containsPunishment;
    private int containsEnemy;
    private int containEndCell;

    /**
     * Creates a new Cell that stores information about a given (x, y) position
     *
     * @param x       is contained within the interval [0, 19] as dictated by Board and BoardState
     * @param y       is contained within the interval [0, 9] as dictated by Board and BoardState
     * @param isSolid indicates whether a Cell is not solid/traversable (0) or solid/non-traversable (1)
     */
    public Cell(int x, int y, int isSolid) {
        super(x, y);
        this.isSolid = isSolid;
        this.containsReward = 0;
        this.containsBonusReward = 0;
        this.containsPunishment = 0;
        this.containEndCell = 0;
    }

    /**
     * @return whether a Cell is traversable or not
     */
    public int getIsSolid() {
        return isSolid;
    }

    /**
     * @param isSolid whether a Cell is solid (1) or not solid (0)
     */
    public void setIsSolid(int isSolid) {
        this.isSolid = isSolid;
    }

    /**
     * @return whether a Cell contains a regular reward (1) or does not contain a regular reward (0)
     */
    public int getContainsReward() {
        return containsReward;
    }

    /**
     * @param containsReward whether a cell contains a regular reward (1) or does not contain a regular reward (0)
     */
    public void setContainsReward(int containsReward) {
        this.containsReward = containsReward;
    }

    /**
     * @return whether a Cell contains a bonus reward (1) or does not contain a bonus reward (0)
     */
    public int getContainsBonusReward() {
        return containsBonusReward;
    }

    /**
     * @param containsBonusReward whether a Cell contains a bonus reward (1) or does not contain a bonus reward (0)
     */
    public void setContainsBonusReward(int containsBonusReward) {
        this.containsBonusReward = containsBonusReward;
    }

    /**
     * @return whether a Cell contains a punishment (1) or does not contain a punishment (0)
     */
    public int getContainsPunishment() {
        return containsPunishment;
    }

    /**
     * @param containsPunishment whether a Cell contains a punishment (1) or does not contain a punishment (0)
     */
    public void setContainsPunishment(int containsPunishment) {
        this.containsPunishment = containsPunishment;
    }

    /**
     * @return whether a Cell contains a reward, bonus reward or punishment (1) or
     * none of the aforementioned score modifiers (0)
     */
    public int getContainsRewardOrPunishment() {
        if (this.getContainsReward() == 1 || this.getContainsBonusReward() == 1 || this.getContainsPunishment() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * @return whether a Cell contains an enemy (1) or does not contain an enemy (0)
     */
    public int getContainsEnemy() {
        return containsEnemy;
    }

    /**
     * @param containsEnemy whether a Cell contains an enemy (1) or does not contain an enemy (0)
     */
    public void setContainsEnemy(int containsEnemy) {
        this.containsEnemy = containsEnemy;
    }

    /**
     * @param containEndCell whether a Cell is the End Cell (1) or not the End Cell (0)
     */
    public void setContainEndCell(int containEndCell) {
        this.containEndCell = containEndCell;
    }

    /**
     * @return whether a Cell is the End Cell (1) or not the End Cell (0)
     */
    public int getContainEndCell() {
        return containEndCell;
    }
}
