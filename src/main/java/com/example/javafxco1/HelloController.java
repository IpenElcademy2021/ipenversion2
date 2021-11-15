package com.example.javafxco1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class HelloController {
    @FXML
    private TreeView treeview_SideMenu;
    @FXML
    ImageView image_Home, image_Office, image_User, image_Office2, image_Exit, image_UserS2 = new ImageView();
    @FXML
    private TextField txtf_Username;
    @FXML
    private PasswordField passwordfield_Password;
    @FXML
    private TableColumn tablecolumn_Caisse, tablecolumn_NAVS, tablecolumn_Nom, tablecolumn_Prenom, tablecolumn_DOB, tablecolumn_Sexe;
    @FXML
    private TableView tableview_Members;
    @FXML
    private VBox vbox_fragment1;


    Connection con;
    PreparedStatement prepstatment;
    ResultSet rs;

    //
    private Stage stage;
    private Scene scene;
    private Parent root;

    MethodClass MC = new MethodClass();

    @FXML
    protected void initialize() {
        //Setting ICONS to imageview
        //image_Home.setImage(new Image("home.png"));
        //image_Office.setImage(new Image("office-building.png"));
        //image_User.setImage(new Image("user.png"));
        //image_Office2.setImage(new Image("office2.png"));
       // image_Exit.setImage(new Image("power.png"));


        //Populating TREEVIEW
        TreeItem<String> rootItem = new TreeItem<>("Files");
        //TreeItem<String> rootItem = new TreeItem<>("Files", new ImageView(new Image("Folder_Icon.png")));

        TreeItem<String> branchItem1 = new TreeItem<>("Assures");
        TreeItem<String> branchItem2 = new TreeItem<>("Prestation");
        TreeItem<String> branchItem3 = new TreeItem<>("Rassemblement de CI");
        TreeItem<String> branchItem4 = new TreeItem<>("Compte individuel");


        TreeItem<String> leafItem1 = new TreeItem<>("CI Orphelin");
        TreeItem<String> leafItem2 = new TreeItem<>("Confirmation a");
        TreeItem<String> leafItem3 = new TreeItem<>("Inscription CI re");
        TreeItem<String> leafItem4 = new TreeItem<>("Revocation");

        TreeItem<String> leafItem5 = new TreeItem<>("Ouverture de CI");
        TreeItem<String> leafItem6 = new TreeItem<>("Inscriptin CI");


        TreeItem<String> leafItem7 = new TreeItem<>("Confirmation");


        branchItem3.getChildren().addAll(leafItem1, leafItem2, leafItem3, leafItem4);
        branchItem4.getChildren().addAll(leafItem5, leafItem6);
        leafItem5.getChildren().addAll(leafItem7);

        rootItem.getChildren().addAll(branchItem1, branchItem2, branchItem3, branchItem4);

        treeview_SideMenu.setShowRoot(false);
        treeview_SideMenu.setRoot(rootItem);

        //
        //TABLEVIEW ADD DATA
        //Define data in ObservableList
        final ObservableList<TVMembers> data = FXCollections.observableArrayList(
                new TVMembers("Notre Caisse profesionelle","59069940","Rohan","Gary","15/10/21","Male"),
                new TVMembers("Notre Caisse cantonale","59658452","Ronaldo","Cristiano","20/10/21","Male"),
                new TVMembers("Notre Caisse profesionelle","51423698","Fernandes","Bruno","21/10/21","Male"),
                new TVMembers("Notre Caisse profesionelle","51472694","Evra","Patrice","22/10/21","Male"),
                new TVMembers("Notre Caisse cantonale","51324863","Ferguson","ALex","25/10/21","Male")
        );

        //Associate data with columns
        tablecolumn_Caisse.setCellValueFactory(new PropertyValueFactory<TVMembers,String>("caisse"));
        tablecolumn_NAVS.setCellValueFactory(new PropertyValueFactory<TVMembers,String>("num"));
        tablecolumn_Nom.setCellValueFactory(new PropertyValueFactory<TVMembers,String>("nom"));
        tablecolumn_Prenom.setCellValueFactory(new PropertyValueFactory<TVMembers,String>("prenom"));
        tablecolumn_DOB.setCellValueFactory(new PropertyValueFactory<TVMembers,String>("dob"));
        tablecolumn_Sexe.setCellValueFactory(new PropertyValueFactory<TVMembers,String>("sex"));
        //add data to table
        tableview_Members.setItems(data);
    }

    String userrole, AccStatus = "";
    public void login(ActionEvent e) {
        String uname = txtf_Username.getText();
        String passw = passwordfield_Password.getText();


        if(uname.equals("") || passw.equals(""))
        {
            MC.MessageBox("Username or Password is BLANK!", "Warning!");
        }
        else
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/rau", "root", "rauelcadb");

                prepstatment = con.prepareStatement("select * from rauauthentication where username=? and password=?");

                prepstatment.setString(1, uname);
                prepstatment.setString(2, passw);

                rs = prepstatment.executeQuery();


                if (rs.next()) {
                    AccStatus = rs.getString(5);
                    userrole = rs.getString(4);

                    if (AccStatus.equals("0")) {
                        MC.MessageBox("Account disabled, please contact an ADMIN", "DEAD Account");
                    } else {
                        if (userrole.equals("1")) {
                            MC.MessageBox("You have been logged in! as a User.", "Success! (User)");
                        } else if (userrole.equals("2")) {
                            MC.MessageBox("You have been logged in! as an ADMIN.", "Success! (ADMIN)");
                        }
                    }
                }
                else {
                    MC.MessageBox("Failed login attempt, try again!", "Failure!");
                    txtf_Username.setText("");
                    passwordfield_Password.setText("");
                    txtf_Username.requestFocus();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
    }

    public void switchToScene1(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root, 1100, 750);
        stage.setScene(scene);
        scene.getStylesheets().add("Stylesheet.css");
        stage.show();
    }

    public void switchToScene2(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root, 1100, 750);
        stage.setScene(scene);
        scene.getStylesheets().add("Stylesheet.css");
        stage.show();
    }

    public void SwitchToFragment1() throws IOException {
        VBox vBoxf1 = FXMLLoader.load((getClass().getResource("vBoxf1.fxml")));
        vbox_fragment1.getChildren().setAll(vBoxf1);
    }

    public void SwitchToFragment2() throws IOException {
        VBox vBoxf2 = FXMLLoader.load((getClass().getResource("vBoxf2.fxml")));
        vbox_fragment1.getChildren().setAll(vBoxf2);
    }

    public void SwitchToFragment3() throws IOException {
        VBox vBoxf3 = FXMLLoader.load((getClass().getResource("vBoxf3.fxml")));
        vbox_fragment1.getChildren().setAll(vBoxf3);
    }

    public void SwitchToFragment4() throws IOException {
        VBox vBoxf4 = FXMLLoader.load((getClass().getResource("vBoxf4.fxml")));
        vbox_fragment1.getChildren().setAll(vBoxf4);
    }

    //
}