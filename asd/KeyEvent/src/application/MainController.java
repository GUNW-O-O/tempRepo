package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;

public class MainController {

    @FXML private Circle circle;
    @FXML private Button up;
    @FXML private Button down;
    @FXML private Button left;
    @FXML private Button right;
    
    private double x;			// Circle x좌표
    private double y;			// Circle y좌표
    
    @FXML
    public void initialize() {
    	if ( circle != null ) {
    		x = circle.getCenterX();	// 원 중심 x 좌표
    		y = circle.getCenterY();	// 원 중심 y 좌표
    	}
    	
    }

    @FXML
    void up(ActionEvent event) {
    	System.out.println("UP");
    	circle.setCenterY( y -= 10 );
    }

    @FXML
    void down(ActionEvent event) {
    	System.out.println("DOWN");
    	circle.setCenterY( y += 10 );
    }

    @FXML
    void left(ActionEvent event) {
    	System.out.println("LEFT");
    	circle.setCenterX( x -= 10);
    }

    @FXML
    void right(ActionEvent event) {
    	System.out.println("RIGHT");
    	circle.setCenterX( x += 10);
    }

    void keyEvent() {
    	Scene scene = Main.scene;
    	System.out.println("키입력");
    	if( scene != null ) {
    		// 키입력 이벤트 등록
    		scene.setOnKeyPressed( event -> {
    			KeyCode KeyCode = event.getCode();
    			switch (KeyCode) {
    			case UP : 		circle.setCenterY( y -= 10); break;
    			case DOWN : 	circle.setCenterY( y += 10); break;
    			case LEFT : 	circle.setCenterX( x -= 10); break;
    			case RIGHT : 	circle.setCenterX( x += 10); break;
    			}
    		});
    	}
    }
    
    
    
}









