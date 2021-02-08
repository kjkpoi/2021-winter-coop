// Save obstacle and gem information of the given map.
public class PointInfo {
    private boolean obstacle = false;
    private boolean gem = false;

    public boolean getObstacle() {
        return obstacle;
    }

    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    public boolean getGem() {
        return gem;
    }

    public void setGem(boolean gem) {
        this.gem = gem;
    }

}
