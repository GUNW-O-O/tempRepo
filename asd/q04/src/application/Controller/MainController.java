package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.DTO.Board;
import application.Service.BoardService;
import application.Service.BoardServiceImpl;
import application.Util.SceneUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController implements Initializable{
	
	@FXML
	private TableView<Board> boardTableView;
	
	@FXML
	private TableColumn<Board, Integer> colBoardNo;
	@FXML
	private TableColumn<Board, Integer> colTitle;
	@FXML
	private TableColumn<Board, Integer> colWriter;
	@FXML
	private TableColumn<Board, Integer> colRegDate;
	@FXML
	private TableColumn<Board, Integer> colUpdDate;
	@FXML
	private TableColumn<Board, CheckBox> colCbDelete;
	@FXML
	private CheckBox cbAll;
	
	BoardService boardService = new BoardServiceImpl();
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// 데이터 초기화
		List<Board> boardList = new ArrayList<Board>();
		boardList = boardService.list();
		
		for (Board board : boardList) {
			System.out.println(board);
		}
		
		ObservableList<Board> list = FXCollections.observableArrayList(boardList);
		
		colBoardNo.setCellValueFactory(new PropertyValueFactory<>("BoardNo"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
		colWriter.setCellValueFactory(new PropertyValueFactory<>("Writer"));
		colRegDate.setCellValueFactory(new PropertyValueFactory<>("RegDate"));
		colUpdDate.setCellValueFactory(new PropertyValueFactory<>("UpdDate"));
		colCbDelete.setCellValueFactory(new PropertyValueFactory<>("CbDelete"));
		
		// TableView 에 데이터 리스트 지정
		boardTableView.setItems(list);
		
		cbAll.setSelected(false);
		// 전체 체크박스 클릭 이벤트
		cbAll.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				CheckBox checkBox = (CheckBox) event.getSource();
				boolean checkall = checkBox.isSelected();
				boardTableView.getItems().stream().forEach((board) -> {
					board.getCbDelete().setSelected(checkall);
				});
			}
			
		});
		
		// 테이블뷰 더블 클릭 이벤트
		boardTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if (event.getClickCount() == 2 &&
					boardTableView.getSelectionModel().getSelectedItem() != null) {
					System.out.println("더블 클릭");
					int boardNo = boardTableView.getSelectionModel().getSelectedItem().getBoardNo();
					try {
						ReadController readController = (ReadController) SceneUtil.getInstance().getController(UI.READ.getPath());
						readController.read(boardNo);
						
						Parent root = SceneUtil.getInstance().getRoot();
						SceneUtil.getInstance().switchScene(event, UI.READ.getPath(), root);
						
					} catch (IOException e) {
						System.err.println("[목록->읽기] 화면 이동 중 예외 발생!");
						e.printStackTrace();
					}
				}
			}
			
		});
		
		
	}
	/**
	 * 프로그램 종료
	 * @param event
	 */
	public void close(ActionEvent event) {
		System.out.println("프로그램 종료?");
		SceneUtil.getInstance().close(event);
	}
	/**
	 * 선택 삭제
	 * @param event
	 */
	public void deleteSelected(ActionEvent event) {
		boardTableView.getItems().stream().forEach((board) -> {
			CheckBox cbDelete = board.getCbDelete();
			boolean checked = cbDelete.isSelected();
			
			if (checked) {
				int boardNo = board.getBoardNo();
				boardService.delete(boardNo);
			}
		});
		initialize(null, null);
	}
	
	/**
	 * 글쓰기 화면으로 이동
	 * @param event
	 * @throws IOException 
	 */
	public void moveToInsert(ActionEvent event) throws IOException {
		SceneUtil.getInstance().switchScene(event, UI.INSERT.getPath());
	}
	

}














