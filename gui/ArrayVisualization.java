package gui;

import algorithms.dancinglinks.DancingLinks;
import algorithms.dancinglinks.DancingLinksRunnable;

/**
 * This class renders the 3d space which will be filled with 3d parcels.
 * It allows the user to control his percpective.
 * It extends the JavaFX class Application.
 */

import algorithms.genetic.*;
import algorithms.greedy.GreedySearch;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ArrayVisualization extends Application {

    /**
     * initialize the type of search and thread of algorithm
     */

    public static String type;
    static String algorithm;
    public static String value1;
    public static String value2;
    public static String value3;
    public static String quantity1;
    public static String quantity2;
    public static String quantity3;
    static int[] values = new int[3];
    public static int[] quantities = new int[3];
    TextField[] textFields;
    public static boolean unlimited;
    static boolean stop;
    Thread thread;
    public Button stopButton;

    /**
     * initialize the cargo's space array
     */

    public static int depth = 33;
    public static int height = 8;
    public static int width = 5;

    /**
     * Creating RGroup objects to allow control of the camera's around the 3D world.
     */

    static Scene scene;
    final Group groot = new Group();
    final Group root = new Group();
    final RGroup axisGroup = new RGroup();
    final RGroup visGroup = new RGroup();
    final RGroup arrForm = new RGroup();
    final RGroup world = new RGroup();
    final RGroup cameraRGroup = new RGroup();
    final RGroup cameraRGroup2 = new RGroup();
    final RGroup cameraRGroup3 = new RGroup();
    final PerspectiveCamera camera = new PerspectiveCamera(true);

    /**
     * initalizing parameters to set the camera's start point and speed
     */

    private final double CAMERA_INITIAL_DISTANCE = -depth * 15;
    private final double CAMERA_INITIAL_X_ANGLE = 0;
    private final double CAMERA_INITIAL_Y_ANGLE = 10;
    private final double CAMERA_NEAR_CLIP = 0.1;
    private final double CAMERA_FAR_CLIP = 2000.0;
    private final double MOUSE_SPEED = 0.1;
    private final double ROTATION_SPEED = 2.0;
    private final int BLOCK_SIZE = 10;
    private final int OFFSET = 0;
    private final int xCoord = (width * BLOCK_SIZE + (width * OFFSET)) / 2;
    private final int yCoord = (height * BLOCK_SIZE + (height * OFFSET)) / 2;
    private final int zCoord = (depth * BLOCK_SIZE + (depth * OFFSET)) / 2;

    /**
     * initalizing parameters to keep track of the mouse's position at a given
     * moment
     */

    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;

    /**
     * Set up the camera and set the rotate point around the center of the 3D array
     */

    private void buildCamera() {

        root.getChildren().add(cameraRGroup);

        cameraRGroup.getChildren().add(cameraRGroup2);
        cameraRGroup2.getChildren().add(cameraRGroup3);
        cameraRGroup3.getChildren().add(camera);
        cameraRGroup3.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        camera.setTranslateY(-(depth * (height / width)) - 10);
        camera.setTranslateX(-(depth * (height / width)) + 12);

        cameraRGroup.rotateY.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraRGroup.rotateX.setAngle(CAMERA_INITIAL_X_ANGLE);

        RGroup.setPivot(cameraRGroup, xCoord - (BLOCK_SIZE / 2), yCoord - (BLOCK_SIZE / 2), zCoord - (BLOCK_SIZE / 2));

    }

    /**
     * creating a mouse handler allowing the user to control the view of the cargo and general clicks
     * @param scene scene object to handle events  
     * @param subScene subScene object to get the mouse movements from
     * @param root root Node
     */

    private void handleMouse(Scene scene, SubScene subScene, final Node root) {
        subScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });

        subScene.setOnMouseDragged(new EventHandler<MouseEvent>() {

            int speed = 3;

            @Override
            public void handle(MouseEvent me) {

                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);

                if (me.isPrimaryButtonDown()) {
                    if (me.isShiftDown()) {
                        double newY = camera.getTranslateY() + mouseDeltaY * MOUSE_SPEED * (speed * 2);
                        double newX = camera.getTranslateX() + mouseDeltaX * MOUSE_SPEED * (speed * 2);
                        camera.setTranslateY(newY);
                        camera.setTranslateX(newX);
                    } else {
                        cameraRGroup.rotateY.setAngle(
                                cameraRGroup.rotateY.getAngle() - mouseDeltaX * MOUSE_SPEED * speed * ROTATION_SPEED);
                        cameraRGroup.rotateX.setAngle(
                                cameraRGroup.rotateX.getAngle() + mouseDeltaY * MOUSE_SPEED * speed * ROTATION_SPEED);
                    }
                }
            }
        });

        subScene.setOnScroll(new EventHandler<ScrollEvent>() {

            @Override
            public void handle(ScrollEvent me) {
                double deltaY = me.getDeltaY();
                camera.setTranslateZ(camera.getTranslateZ() + deltaY);
            }

        });

        Button subButton = (Button) scene.lookup("#sub");
        stopButton = (Button) scene.lookup("#stop");
        CheckBox unlimitedObj = (CheckBox) scene.lookup("#unlimited");

        @SuppressWarnings("unchecked")
        ComboBox<String> typeObj = (ComboBox<String>) scene.lookup("#type");

        @SuppressWarnings("unchecked")
        ComboBox<String> algoObj = (ComboBox<String>) scene.lookup("#algo");

        TextField qAObj = (TextField) scene.lookup("#qa");
        TextField qBObj = (TextField) scene.lookup("#qb");
        TextField qCObj = (TextField) scene.lookup("#qc");
        TextField vAObj = (TextField) scene.lookup("#va");
        TextField vBObj = (TextField) scene.lookup("#vb");
        TextField vCObj = (TextField) scene.lookup("#vc");
        textFields = new TextField[] { qAObj, qBObj, qCObj, vAObj, vBObj, vCObj };

        EventHandler<ActionEvent> submitHandler = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (parseInput()) {
                    type = typeObj.getValue();
                    algorithm = algoObj.getValue();
                    value1 = vAObj.getText();
                    value2 = vBObj.getText();
                    value3 = vCObj.getText();

                    values = new int[] { Integer.parseInt(value1), Integer.parseInt(value2), Integer.parseInt(value3) };

                    unlimited = unlimitedObj.isSelected();
                    if(!unlimited) {
                    quantity1 = qAObj.getText();
                    quantity2 = qBObj.getText();
                    quantity3 = qCObj.getText();
                    quantities = new int[] { Integer.parseInt(quantity1),
                    Integer.parseInt(quantity2), Integer.parseInt(quantity3) };
                    }
                    
                    stopButton.setDisable(false);
                    subButton.setDisable(true);
                    runAlgorithm();
                }
            }
        };

        EventHandler<ActionEvent> stopHandler = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                stop = true;
                stopButton.setDisable(true);
                subButton.setDisable(false);
            }
        };

        EventHandler<ActionEvent> unlimitedHandler = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                unlimited = unlimitedObj.isSelected();
                
                if (unlimited) {
                    qAObj.setDisable(true);
                    qBObj.setDisable(true);
                    qCObj.setDisable(true);
                } else {
                    qAObj.setDisable(false);
                    qBObj.setDisable(false);
                    qCObj.setDisable(false);
                }
            }
        };

        algoObj.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            @SuppressWarnings("rawtypes")
            public void changed(ObservableValue ov, String t, String t1) {
                if(t1.equals("Dancing Links")) {
                    unlimitedObj.setSelected(true);
                    unlimitedObj.setDisable(true);
                    qAObj.setDisable(true);
                    qBObj.setDisable(true);
                    qCObj.setDisable(true);
                    unlimited = true;
                } else {
                    unlimitedObj.setDisable(false);
                }
            }    
         });

        typeObj.valueProperty().addListener(new ChangeListener<String>() {
           String[] textPromptParcel= new String[] { "Quantity A", "Quantity B", "Quantity B", "Value A", "Value B", "Value C" };
           String[] textPromptPent = new String[] { "Quantity L", "Quantity P", "Quantity T", "Value L", "Value P", "Value T" };
            @Override
            @SuppressWarnings("rawtypes")
            public void changed(ObservableValue ov, String t, String t1) {
                if(t1.equals("Pentominoes")) {
                    for(int i = 0; i < textFields.length; i++) {
                        textFields[i].setPromptText(textPromptPent[i]);
                    }
                } else {
                    for(int i = 0; i < textFields.length; i++) {
                        textFields[i].setPromptText(textPromptParcel[i]);
                    }
                }
            }    
         });

        subButton.setOnAction(submitHandler);
        stopButton.setOnAction(stopHandler);
        stopButton.setDisable(true);
        unlimitedObj.setOnAction(unlimitedHandler);
    }

    /**
    * sanitize input from textField
    * @return true if input is okay, false if not
    */
    public boolean parseInput() {
        int index = 0;
        if(unlimited) index = 3;

        for (int i = index; i < textFields.length; i++) {
            if (textFields[i].getText().equals(""))
                textFields[i].setText("0");

            if (!textFields[i].getText().matches("^[0-9]+$"))
                return false;
        }
        int sum = 0;

        if(unlimited) return true;

        for(int i = 0; i < 3; i++) {
            sum += Integer.parseInt(textFields[i].getText());
        }

        if(sum == 0) textFields[0].setText("1");

        return true;
    }

    /**
     * creating a keyboard handler allowing the user to control the view of the cargo and general keyboard binds
     * @param scene scene object to handle events  
     * @param subScene subScene object to get the keyboard events from
     * @param root root Node
     */

    private void handleKeyboard(SubScene subScene, final Node root) {

        int depthSpeed = 20;
        subScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                        camera.setTranslateZ(camera.getTranslateZ() + depthSpeed);
                        break;
                    case S:
                        camera.setTranslateZ(camera.getTranslateZ() - depthSpeed);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * setting the parcels' colors and diffusion color in the cargo's space
     * 
     * @param i represents the parcel's id
     * @return material
     */

    private PhongMaterial getColor(int i) {

        final PhongMaterial material = new PhongMaterial();

        if (i == 1) {
            material.setDiffuseColor(Color.web("3c210e"));
            return material;
        } else if (i == 2) {
            material.setDiffuseColor(Color.DARKGREEN);
            return material;
        } else if (i == 3) {
            material.setDiffuseColor(Color.WHITE);
            return material;
        }

        return null; // never called

    }

    /**
     * filling the cargo with the parcels by coloring the each cube
     * that belongs to a specific type of parcel with its representitive color
     */

    public void createContainerOutlines() {

        int boxWidth = xCoord * 2;
        int boxHeight = yCoord * 2;
        int boxDepth = zCoord * 2;
        int offset = -(BLOCK_SIZE / 2);

        Point3D p1 = new Point3D(offset, offset, offset);
        Point3D p2 = new Point3D(boxWidth + offset, offset, offset);
        Point3D p3 = new Point3D(offset, boxHeight + offset, offset);
        Point3D p4 = new Point3D(boxWidth + offset, boxHeight + offset, offset);
        Point3D p5 = new Point3D(offset, offset, boxDepth + offset);
        Point3D p6 = new Point3D(boxWidth + offset, offset, boxDepth + offset);
        Point3D p7 = new Point3D(offset, boxHeight + offset, boxDepth + offset);
        Point3D p8 = new Point3D(boxWidth + offset, boxHeight + offset, boxDepth + offset);

        createLine(p1, p2);
        createLine(p1, p3);
        createLine(p1, p5);
        createLine(p2, p6);
        createLine(p2, p4);
        createLine(p3, p4);
        createLine(p3, p7);
        createLine(p4, p8);
        createLine(p5, p6);
        createLine(p5, p7);
        createLine(p6, p8);
        createLine(p7, p8);
    }

    /**
     * create line between origin and target
     * @param origin origin of the line
     * @param target target of the line
     */

    public void createLine(Point3D origin, Point3D target) { // Math taken from math stackexchange question #42984225

        double width = 0.2;

        Point3D yPoint = new Point3D(0, 1, 0);
        Point3D diff = target.subtract(origin);
        double height = diff.magnitude();

        Point3D mid = target.midpoint(origin);
        Translate moveToOriginCenter = new Translate(mid.getX(), mid.getY(), mid.getZ());

        Point3D rAxis = diff.crossProduct(yPoint);
        double angle = Math.acos(diff.normalize().dotProduct(yPoint));
        Rotate rotate = new Rotate(-Math.toDegrees(angle), rAxis);

        Cylinder line = new Cylinder(width, height);

        line.getTransforms().addAll(moveToOriginCenter, rotate);
        world.getChildren().addAll(line);
    }

    /**
     * create 3D container
     * @param arr 3D array to represent
     */

    private void buildBox(int[][][] arr) {

        arrForm.getChildren().clear();
        visGroup.getChildren().clear();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                for (int k = 0; k < arr[i][j].length; k++) {
                    if (arr[i][j][k] == 0)
                        continue;

                    PhongMaterial color;
                    color = getColor(arr[i][j][k]);
                    Box box = new Box();
                    box.setWidth(BLOCK_SIZE);
                    box.setHeight(BLOCK_SIZE);
                    box.setDepth(BLOCK_SIZE);
                    box.setMaterial(color);
                    box.setTranslateX((k * BLOCK_SIZE) + (k * OFFSET));
                    box.setTranslateY((j * BLOCK_SIZE) + (j * OFFSET));
                    box.setTranslateZ((i * BLOCK_SIZE) + (i * OFFSET));
                    arrForm.getChildren().add(box);
                }
            }
        }

        visGroup.getChildren().add(arrForm);
        world.getChildren().addAll(visGroup);
    }

    /**
     * update current array representation
     * @param arr 3D array to represent
     */

    public void updateState(int[][][] newArr) {
        world.getChildren().removeAll(visGroup);
        buildBox(newArr);
    }

    /**
     * update score label
     * @param solutionScore solution score to be printed
     */

    public static void setScore(int solutionScore) {
        Label score = (Label) scene.lookup("#score");
        score.setText(solutionScore + "");
    }

    /**
     * start selected algorithm
     */

    private void runAlgorithm() {
        switch (algorithm) {
            case "Genetic Algorithm":
                GA.setVisualizer(this);
                thread = new Thread(new GA());
                break;
            case "Dancing Links":
                DancingLinks.setVisualizer(this);
                thread = new Thread(new DancingLinksRunnable());
                break;
            case "Greedy Algorithm":
                GreedySearch.setVisualizer(this);
                thread = new Thread(new GreedySearch());
                break;
        }
        stop = false;
        thread.start();
    }

    /**
     * get stop status
     * @return true if has to be stopped, false otherwise
     */

    public static boolean getStop() {
        return stop;
    }

    /**
     * runtime method JavaFX
     * @throws IOException if FXML file is not there
     * @param primaryStage primaryStage
     */

    @Override
    public void start(Stage primaryStage) throws IOException {

        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);

        buildCamera();
        createContainerOutlines();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/GUI.fxml"));
        VBox pane = loader.load();
        scene = new Scene(pane, 900, 500);
        scene.setFill(Color.GREY);
        primaryStage.setTitle("3D Knapsack");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        Pane leftpane = (Pane) scene.lookup("#leftpane");
        SubScene subScene = new SubScene(root, 600, 500, true, SceneAntialiasing.BALANCED);
        subScene.setCamera(camera);
        leftpane.getChildren().add(subScene);
        handleMouse(scene, subScene, world);
        subScene.setPickOnBounds(true);
        handleKeyboard(subScene, world);

        leftpane.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                subScene.requestFocus();
            }

        });
    }

    /**
     * JavaFX method to handle program stop
     */
    
    @Override
    public void stop() {
        stop = true;
    }
}