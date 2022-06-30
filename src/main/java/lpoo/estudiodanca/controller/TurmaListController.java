package lpoo.estudiodanca.controller;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lpoo.estudiodanca.modelo.vo.Turma;
import lpoo.estudiodanca.principal.Main;

public class TurmaListController implements Initializable{
	
	@FXML
	private TableView<Turma> tableViewTurma;
	@FXML
	private TableColumn<Turma, Long> tableColumnId;
	@FXML
	private TableColumn<Turma, Long> tableColumnName;
	@FXML
	private TableColumn<Turma, Time> tableColumnHorario;
	
	@FXML
	private Button btnNew;
	
	@FXML
	public void onBtnNewAction() {
		System.out.println("Ta on!!");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewTurma.prefHeightProperty().bind(stage.heightProperty());
		
		
	}
}
