import java.awt.Color;

import ihs.apcs.spacebattle.*;
import ihs.apcs.spacebattle.commands.*;

public class ExampleShip extends BasicSpaceship {
   
   private int worldWidth;
   private int worldHeight;
   private double middleX;
   private double middleY;
   private boolean facingMiddle = false;
   private Point midpoint;
   public double distanceToMid = 0;
   public boolean getDistance = false;
   public boolean torpedo = false;
   public boolean getRadar = false;
    public static void main(String[] args)
    {
        TextClient.run("10.56.98.121", new ExampleShip());
    }

    @Override
    public RegistrationData registerShip(int numImages, int tempWorldWidth, int tempWorldHeight)
    {
        worldWidth = tempWorldWidth;
        worldHeight = tempWorldHeight;
        middleX = worldWidth / 2;
        middleY = worldHeight / 2;
        midpoint = new Point(middleX, middleY);
        return new RegistrationData("Griffin's Ship", new Color(255, 255, 255), 0);
    }


    @Override
    public ShipCommand getNextCommand(BasicEnvironment env)
    {   
        if (!getRadar) {
   //        return RadarCommand(2);
        }
        ObjectStatus shipStatus = env.getShipStatus();
        while (shipStatus.getPosition().getAngleTo(midpoint) - shipStatus.getOrientation() < 5 && shipStatus.getPosition().getAngleTo(midpoint) - shipStatus.getOrientation() > -5) {
            if (!getDistance) {
               distanceToMid = shipStatus.getPosition().getDistanceTo(midpoint);
               getDistance = true;
            }
            if (shipStatus.getPosition().getDistanceTo(midpoint) < 200) {
                  return new BrakeCommand(.01);
            }
            else {
               if (torpedo) {
                  torpedo = false;
                  return new FireTorpedoCommand('F');
               } else {
                  torpedo = true;
                  return new ThrustCommand('B', 1, .5);
               }
            }
        }
        return new RotateCommand(shipStatus.getPosition().getAngleTo(midpoint) - shipStatus.getOrientation());
    }
}