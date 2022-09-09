import model.Building;
import model.Elevator;
import service.LogicBlock;

public class Main {
    public static void main(String[] args) {
        Building building = new Building();
        Elevator elevator = new Elevator(building.getMaxFloor());

        LogicBlock logicBlock = new LogicBlock(building, elevator);
        // specify the number of iteration.
        logicBlock.startMovement(0);
    }
}
