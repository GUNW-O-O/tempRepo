module JavaFX {
	requires javafx.controls;
	requires javafx.fxml;			// 추가
	opens application to javafx.graphics, javafx.fxml;
}
