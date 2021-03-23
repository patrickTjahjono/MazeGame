public class Cell extends Position {
    private int isSolid;
    private int containsReward;
    private int containsBonusReward;
    private int containsPunishment;
    private int containsEnemy;
    private int containEndCell;

    public Cell(int x, int y, int isSolid) {
        super(x, y);
        this.isSolid = isSolid;
        this.containsReward = 0;
        this.containsBonusReward = 0;
        this.containsPunishment = 0;
        this.containEndCell = 0;

    }

    public int getIsSolid() {
        return isSolid;
    }

    public void setIsSolid(int isSolid) {
        this.isSolid = isSolid;
    }

    public int getContainsReward() {
        return containsReward;
    }

    public void setContainsReward(int containsReward) {
        this.containsReward = containsReward;
    }

    public int getContainsBonusReward() {
        return containsBonusReward;
    }

    public void setContainsBonusReward(int containsBonusReward) {
        this.containsBonusReward = containsBonusReward;
    }

    public int getContainsPunishment() {
        return containsPunishment;
    }

    public void setContainsPunishment(int containsPunishment) {
        this.containsPunishment = containsPunishment;
    }

    public int getContainsRewardOrPunishment() {
        if (this.getContainsReward() == 1 || this.getContainsBonusReward() == 1 || this.getContainsPunishment() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getContainsEnemy() {
        return containsEnemy;
    }

    public void setContainsEnemy(int containsEnemy) {
        this.containsEnemy = containsEnemy;
    }

    public void setContainEndCell(int containEndCell) {
        this.containEndCell = containEndCell;
    }

    public int getContainEndCell() {
        return containEndCell;
    }
}
