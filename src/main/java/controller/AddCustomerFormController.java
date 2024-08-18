package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbTitle;

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colDob;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colSalary;

    @FXML
    private DatePicker dateDob;

    @FXML
    private TableView<Customer> tblCustomers;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSalary;

    List<Customer> customerList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));

        ObservableList<String> titleList = FXCollections.observableArrayList();
        titleList.add("MR.");
        titleList.add("MISS.");
        titleList.add("MRS.");
        cmbTitle.setItems(titleList);
    }
    @FXML
    void btnAddOnAction(ActionEvent event) {
        customerList.add(
                new Customer(
                        txtId.getText(),
                        cmbTitle.getValue() + txtName.getText(),
                        txtAddress.getText(),
                        Double.parseDouble(txtSalary.getText()),
                        dateDob.getValue())
        );

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        customerList.forEach(customer->{
            customerObservableList.add(customer);
        });



        tblCustomers.setItems(customerObservableList);
    }


}
