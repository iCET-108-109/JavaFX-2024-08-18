package controller.order;

import controller.customer.CustomerController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import model.Customer;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderFromController implements Initializable {

    public TextField txtCustomerAddress;
    @FXML
    private Button btnPlaceOrderOnAction;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<?> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderTime;

    @FXML
    private TableView<?> tblCart;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtItemDescription;

    @FXML
    private TextField txtItemStock;

    @FXML
    private TextField txtQty;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
        loadCustomerIds();
        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            System.out.println(t1);
            if(t1!=null){
                searchCustomer(t1);
            }
        });
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    private void searchCustomer(String id){
        Customer customer = CustomerController.getInstance().searchCustomer(id);
        System.out.println(customer);
        txtCustomerName.setText(customer.getName());
        txtCustomerAddress.setText(customer.getAddress());

    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblOrderDate.setText(f.format(date));

//        -----------------------------------------------

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblOrderTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void loadCustomerIds(){
        List<String> allCustomerIds = CustomerController.getInstance().getAllCustomerIds();
        ObservableList<String> objects = FXCollections.observableArrayList();
        allCustomerIds.forEach(id->{
            objects.add(id);
        });

        cmbCustomerId.setItems(objects);
    }


}
