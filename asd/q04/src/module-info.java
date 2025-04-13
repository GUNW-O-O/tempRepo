module q04 {
	requires javafx.controls;
	requires java.sql;
	requires lombok;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	opens application to javafx.base, javafx.graphics, javafx.fxml;
	opens application.Controller to javafx.fxml;
	opens application.DTO to javafx.base;
}
