package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

	
	// 버튼
	@FXML
	private Button button;	// Main.fxml <Button fx:id="button">
							// button 변수명과 fx:id 속성값 button 이 일치해야한다.
	
	
	// 버튼 클릭 이벤트 메소드
	public void clickButton(ActionEvent event) throws IOException {
		System.out.println("A화면으로 이동!");
		Main.setRoot("A");
	}
}
