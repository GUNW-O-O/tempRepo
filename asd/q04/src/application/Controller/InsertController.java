package application.Controller;

import java.io.IOException;

import application.DTO.Board;
import application.Service.BoardService;
import application.Service.BoardServiceImpl;
import application.Util.SceneUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class InsertController {
	
	@FXML
    private TextArea taContent;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextField tfWriter;
    
    private BoardService boardService = new BoardServiceImpl();

	// 목록 화면으로 이동
	public void moveToList(ActionEvent event) throws IOException {
		SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
	}
	
	// 글쓰기 처리
	public void insert(ActionEvent event) throws IOException {
		Board board = new Board(tfTitle.getText(), tfWriter.getText(), taContent.getText());
		int result = boardService.insert(board);
		
		if (result > 0 ) {
			System.out.println("글쓰기 성공");
			SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
		}
	}
	
	
}
