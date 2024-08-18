package controller;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewCustomerFormController implements Initializable {



    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtDOB;
    public TextField txtSearch;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        for ( int i = 0; i<tblCustomer.getItems().size(); i++) {
            tblCustomer.getItems().clear();
        }

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));

        List<Customer> customerList = DBConnection.getInstance().getConnection();
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        customerList.forEach(obj->{
            customerObservableList.add(obj);
        });
        tblCustomer.setItems(customerObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Customer> customerList = DBConnection.getInstance().getConnection();
        txtId.setEditable(false);

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, customer, t1) ->{
            System.out.println(t1);

            txtId.setText(t1.getId());
            txtName.setText(t1.getName());
            txtAddress.setText(t1.getAddress());
            txtDOB.setText(String.valueOf(t1.getDob()));
            int index = customerList.indexOf(t1);




        });
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        List<Customer> customerList = DBConnection.getInstance().getConnection();
        for(Customer customer : customerList){
            if(Objects.equals(txtId.getText(), customer.getId())){
                customer.setName(txtName.getText());
                customer.setAddress(txtAddress.getText());
                customer.setDob(LocalDate.parse(txtDOB.getText()));
            }
        }
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtDOB.setText("");

    }


    public void btnSearchOAnction(ActionEvent actionEvent) {
        List<Customer> customerList = DBConnection.getInstance().getConnection();
        for(Customer customer: customerList){
            if(Objects.equals(txtSearch.getText(), customer.getId()) || Objects.equals(txtSearch.getText(), customer.getName())){
                txtId.setText(customer.getId());
                txtName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txtDOB.setText(String.valueOf(customer.getDob()));
            }
        }
        txtSearch.setText("");
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtDOB.setText("");
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        List<Customer> customerList = DBConnection.getInstance().getConnection();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do You Want To Delete This Customer?");
        for(Customer customer : customerList){
            if(Objects.equals(txtId.getText(), customer.getId())){
                customerList.remove(customer);
            }

        }
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtDOB.setText("");

    }
}
