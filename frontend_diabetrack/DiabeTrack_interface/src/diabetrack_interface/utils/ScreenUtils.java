/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diabetrack_interface.utils;

import java.util.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author ESDPC
 */
public class ScreenUtils {
    public static void moveToSecondaryScreen(Stage stage) {
    
    ObservableList<Screen> screens = Screen.getScreens();
    if (screens.size() > 1) {
        Screen secondary = screens.get(1);
        Rectangle2D bounds = secondary.getVisualBounds();
        
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        
        
    
    }
    
    
    }
    
}
