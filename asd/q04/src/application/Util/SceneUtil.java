package application.Util;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SceneUtil {

	private static SceneUtil instance;
	
	Stage stage;
	Scene scene;
	Parent root;
	FXMLLoader loader;
	
	
	private SceneUtil() {
		
	}
	
	public static SceneUtil getInstance() {
		if( instance == null ) {
			instance = new SceneUtil();
		}
		return instance;
	}
	
	public void close(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("프로그램 종료");
		alert.setHeaderText("프로그램을 종료하시겠습니까?");
		alert.setContentText("프로그램이 종료됩니다");
		
		if (alert.showAndWait().get() == ButtonType.OK ) {
			stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.close();
		}
	}
	
	/**
	 * 화면 이동 (ActionEvent)
	 * @param event
	 * @param fxml
	 * @throws IOException
	 */
	public void switchScene(ActionEvent event, String fxml) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(fxml));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * 화면 이동 (지정된 root 인스턴스)
	 * @param event
	 * @param fxml
	 * @param root
	 */
	public void switchScene(MouseEvent event, String fxml, Parent root) {
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * 화면 이동 (지정된 root 인스턴스)
	 * @param event
	 * @param fxml
	 * @param root
	 */
	public void switchScene(ActionEvent event, String fxml, Parent root) {
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	/**
	 * fxml 에 지정된 Controller 가져오기
	 * @param fxml
	 * @return
	 * @throws IOException
	 */
	public Object getController(String fxml) throws IOException {
		loader = new FXMLLoader(getClass().getResource(fxml) );
		root = loader.load();
		
		return loader.getController();
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Parent getRoot() {
		return root;
	}

	public void setRoot(Parent root) {
		this.root = root;
	}

	public FXMLLoader getLoader() {
		return loader;
	}

	public void setLoader(FXMLLoader loader) {
		this.loader = loader;
	}
	
	
	
}















