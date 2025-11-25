package sum25.se196853.demojavafxs3.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sum25.se196853.demojavafxs3.entity.User;
import sum25.se196853.demojavafxs3.service.UserService;

import java.util.List;

@Component
public class UserListController {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> idColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;

    @Autowired
    private UserService userService;

    private ObservableList<User> userList;

    @FXML
    public void initialize() {
        // Cấu hình các cột để binding dữ liệu từ entity User
        // "id", "username", "password" phải khớp với tên thuộc tính trong class User
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        // Load dữ liệu
        loadUsers();
    }

    private void loadUsers() {
        List<User> users = userService.getAllUsers();
        userList = FXCollections.observableArrayList(users);
        userTable.setItems(userList);
    }
}
