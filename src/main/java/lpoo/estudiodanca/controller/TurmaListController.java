package lpoo.estudiodanca.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lpoo.estudiodanca.modelo.db.DbException;
import lpoo.estudiodanca.modelo.services.TurmaService;
import lpoo.estudiodanca.modelo.vo.Turma;
import lpoo.estudiodanca.principal.Main;
import lpoo.estudiodanca.visao.gui.util.Utils;

public class TurmaListController implements Initializable{
	
	private TurmaService service;
	
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
	
	private ObservableList<Turma> obsList;
	
	@FXML
	public void onBtnNewAction(ActionEvent event) throws IOException {
		Stage parentStage = Utils.currentStage(event);
		Turma obj = new Turma();
		createDialogForm(obj, "/lpoo/estudiodanca/visao/gui/TurmaForm.fxml", parentStage);
	}
	
	public void setTurmaService(TurmaService service) {
		this.service = service;
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
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Turma> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewTurma.setItems(obsList);
	}
	
	private void createDialogForm(Turma obj, String absoluteName,Stage parentStage) throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			TurmaFormController  controller = loader.getController();
			controller.setTurma(obj);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Entre com os dados da Turma");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		
	}
	
}
