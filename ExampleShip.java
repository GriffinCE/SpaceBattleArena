import java.awt.Color;

import ihs.apcs.spacebattle.*;
import ihs.apcs.spacebattle.commands.*;

public class ExampleShip extends BasicSpaceship {
   
   private int worldWidth;
   private int worldHeight;
   double middleX = worldWidth / 2;
   double middleY = worldHeight / 2;
   private boolean facingMiddle = false;
    public static void main(String[] args)
    {
        TextClient.run("10.56.98.121", new ExampleShip());
    }

    @Override
    public RegistrationData registerShip(int numImages, int tempWorldWidth, int tempWorldHeight)
    {
        worldWidth = tempWorldWidth;
        worldHeight = tempWorldHeight;
        return new RegistrationData("Griffin's Ship", new Color(255, 255, 255), 0);
    }

    @Override
    public ShipCommand getNextCommand(BasicEnvironment env)
    {   
        Point midpoint = new Point(middleX, middleY);
        ObjectStatus shipStatus = env.getShipStatus();
        System.out.println(shipStatus);
        if (facingMiddle == true) {
            return new ThrustCommand('B', 5, 1);
        } else {
            return new RotateCommand(ship.getPosition().getAngleTo(this.midpoint) - ship.getOrientation());
        }
    }
}