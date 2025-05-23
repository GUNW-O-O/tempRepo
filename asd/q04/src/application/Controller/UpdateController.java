package application.Controller;

import java.io.IOException;

import application.DTO.Board;
import application.Service.BoardService;
import application.Service.BoardServiceImpl;
import application.Util.SceneUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UpdateController {
	
	@FXML
    private TextArea taContent;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextField tfWriter;
	
    private BoardService boardService = new BoardServiceImpl();
    
    int boardNo;
    
	// 글읽기 처리
	public void read(int boardNo) {
		this.boardNo = boardNo;
		Board board = boardService.select(boardNo);
		tfTitle.setText( board.getTitle() );
		tfWriter.setText( board.getWriter() );
		taContent.setText( board.getContent() );
	}
	
	// 목록 화면 이동
	public void moveToList(ActionEvent event) throws IOException {
		SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
	}
	
	// 글삭제 처리
	public void delete(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("게시글 삭제");
		alert.setHeaderText("게시글을 정말로 삭제하겠습니까? (글번호 : "+boardNo+")");
		alert.setContentText("삭제 처리 후에는 되돌릴 수 없습니다.");
		
		int result = 0;
		if (alert.showAndWait().get() == ButtonType.OK ) {
			result = boardService.delete(boardNo);
		}
		
		if (result > 0) {
			System.out.println("글 삭제 성공");
			SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
		}
		
	}
	
	// 글수정 처리
	public void update(ActionEvent event) throws IOException {
		Board board = new Board(tfTitle.getText(), tfWriter.getText(), taContent.getText());
		board.setBoardNo(boardNo);
		
		int result = boardService.update(board);
		if( result > 0 ) {
			System.out.println("글수정 성공");
			SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
		}
	}
	
}















