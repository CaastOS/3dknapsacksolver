package gui;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
* The following class in an extension of the JavaFX class Group, and it contains and organize all the methods to rotate the camera three dimensionally around the group itself.
*/

public class RGroup extends Group {

    // The first thing to allow three dimensional perspective is to calculate when the object needs to translate due to a change of perspective

    public Translate translate  = new Translate(); 

    
    // The three axis of the group gets created and assigned. Each object is of type javafx.scene.transform.Rotate

    public Rotate rotateX = new Rotate();
    { rotateX.setAxis(Rotate.X_AXIS); }
    public Rotate rotateY = new Rotate();
    { rotateY.setAxis(Rotate.Y_AXIS); }
    public Rotate rotateZ = new Rotate();
    { rotateZ.setAxis(Rotate.Z_AXIS); }

    // A Scale object is also needed to deal with the transformation is size caused by perspective changes

    public Scale scale = new Scale();

    // Instatiate a superclass object and allow the group to transform

    public RGroup() {
        super(); 
        getTransforms().addAll(translate, rotateZ, rotateY, rotateX, scale); 
    }

    /**
    * Rotate the Z axis by z degrees.
    * @param  z degrees of rotation 
    */

    public void setRotateZ(double z) {
        rotateZ.setAngle(z);
    }

    /**
    * Set the Pivot of the axis of a group in order to rotate around a given point
    * @param  group group that will have its pivot changed
    * @param  xCoord new X coordinates of the point
    * @param  yCoord new Y coordinates of the point
    * @param  zCoord new Z coordinates of the point
    */

    public static void setPivot(RGroup group, int xCoord, int yCoord, int zCoord) {
        group.rotateX.setPivotX(xCoord);
        group.rotateX.setPivotY(yCoord);
        group.rotateX.setPivotZ(zCoord);
        group.rotateY.setPivotX(xCoord);
        group.rotateY.setPivotY(yCoord);
        group.rotateY.setPivotZ(zCoord);
        group.rotateZ.setPivotX(xCoord);
        group.rotateZ.setPivotY(yCoord);
        group.rotateZ.setPivotZ(zCoord);
    }

}
