package lpoo.estudiodanca.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lpoo.estudiodanca.modelo.vo.Turma;
import lpoo.estudiodanca.visao.gui.util.Constraints;

public class TurmaFormController implements Initializable {

	private Turma  entity;
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtHorario;
	
	@FXML
	private Label labelErrorName;
	
	@FXML
	private Button btnSave;
	
	@FXML
	private Button btnCancel;
	
	public void setTurma(Turma entity) {
		this.entity = entity;
	}
	
	@FXML
	public void onBtnSaveAction() {
		System.out.println("oiii");
	}
	
	@FXML
	public void onBtnCancelAction() {
		System.out.println("ola");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
		Constraints.setTextFieldMaxLength(txtHorario, 30);
	}
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getNome());
		txtHorario.setText(String.valueOf(entity.getHorario()));
	}

}
