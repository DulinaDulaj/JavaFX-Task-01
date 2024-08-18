package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import model.Customer;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    @FXML
    private DatePicker DOB;

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList <String> titles=FXCollections.observableArrayList();
        titles.add("Mr");
        titles.add("Mrs");
        titles.add("Miss");
        cmbTitle.setItems(titles);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        List <Customer> customerList= DBConnection.getInstance().getConnection();
        customerList.add(new Customer(  txtID.getText(),
                                        cmbTitle.getValue(),
                                        txtName.getText(),
                                        txtAddress.getText(),
                                        DOB.getValue()
        ));
        txtID.setText("");
        cmbTitle.setValue("");
        txtName.setText("");
        txtAddress.setText("");

    }
}
