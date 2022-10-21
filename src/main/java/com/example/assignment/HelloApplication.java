package com.example.assignment;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
public class HelloApplication extends Application {
    GridPane gp_home = new GridPane();
    Label hm_welcome=new Label("Welcome to Database Manager");
    Label lb_home=new Label("Menu");
    Button home=new Button("Home");
    Button create = new Button("Create");
    Button read=new Button("Read");
    Button update= new Button("Update");
    Button delete = new Button("Delete");

    Label rgn=new Label("RegNo :- ");
    TextField regno=new TextField();
    Label nm=new Label("Name :- ");
    TextField name=new TextField();
    Label cg=new Label("CGPA :- ");
    TextField cgpa=new TextField();
    Button submit=new Button("Submit");
    Label notify = new Label();

    public void reset(){
        gp_home.getChildren().removeAll(rgn,regno,nm,name,cg,cgpa,submit,hm_welcome,notify);
        submit.setText("Submit");
        regno.setText("");
        name.setText("");
        cgpa.setText("");
        notify.setText("");
    }

    public void setHome() {
        gp_home.add(hm_welcome,6,2,8,3);
    }

    public void onsubmitCreate(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vit","root","jayjajoo");
            PreparedStatement rs=con.prepareStatement("insert into vitian values(?,?,?)");
            rs.setString(1,regno.getText().toString());
            rs.setString(2,name.getText().toString());
            rs.setFloat(3,Float.parseFloat(cgpa.getText().toString()));
            int i=rs.executeUpdate();
            if(i==1){
                notify.setText("Record Inserted");
            }
            else{
                notify.setText("Failed to insert");
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void onsubmitRead(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vit","root","jayjajoo");
            String query="select * from vitian where regno=?";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setString(1,regno.getText().toString());
            ResultSet rs=stmt.executeQuery();
            if(!rs.next()){
                name.setText("");
                cgpa.setText("");
                notify.setText("No records Found");
            }
            else{
                do{
                    name.setText(rs.getString(2).toString());
                    cgpa.setText(Float.toString(rs.getFloat(3)));
                    notify.setText("Read Successfully");
                }while(rs.next());
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void onsubmitUpdate(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vit","root","jayjajoo");
            String query="update vitian set name=? , cgpa=? where regno=?";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setString(3,regno.getText().toString());
            stmt.setString(1,name.getText().toString());
            stmt.setFloat(2,Float.parseFloat(cgpa.getText().toString()));
            int i=stmt.executeUpdate();

            if(i==1){
                notify.setText("Record Updated");
            }
            else{
                notify.setText("Failed to update");
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void onsubmitDelete(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vit","root","jayjajoo");
            String query="delete from vitian where regno=?";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setString(1,regno.getText().toString());
            int i=stmt.executeUpdate();
            if(i==1){
                notify.setText("Deleted successfully");
            }
            else{
                notify.setText("Failed to delete");
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void set_Create_Update(){
        gp_home.add(rgn,4,1,5,1);
        gp_home.add(regno,6,1,9,1);
        gp_home.add(nm,4,2,5,1);
        gp_home.add(name,6,2,9,1);
        gp_home.add(cg,4,3,5,1);
        gp_home.add(cgpa,6,3,9,1);
        gp_home.add(submit,7,4,9,1);
        gp_home.add(notify,8,5,9,1);
    }

    public void setRead() {
        gp_home.add(rgn,4,1,5,1);
        gp_home.add(regno,6,1,9,1);
        gp_home.add(submit,7,2,9,1);
        gp_home.add(nm,4,3,5,1);
        gp_home.add(name,6,3,9,1);
        gp_home.add(cg,4,4,5,1);
        gp_home.add(cgpa,6,4,9,1);
        gp_home.add(notify,8,5,9,1);
    }

    public void setDelete(){
        gp_home.add(rgn,4,1,5,1);
        gp_home.add(regno,6,1,9,1);
        gp_home.add(submit,7,2,9,1);
        submit.setText("Delete Record");
        gp_home.add(notify,8,5,9,1);
    }

    public void db(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vit","root","jayjajoo");
            Statement stmt;
            stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from vitian");
            while(rs.next())
                System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void initialise(){
        lb_home.setPadding(new Insets(10));
        lb_home.setPrefHeight(50);
        lb_home.setPrefWidth(100);
        home.setPrefWidth(100);
        create.setPrefWidth(100);
        read.setPrefWidth(100);
        update.setPrefWidth(100);
        delete.setPrefWidth(100);
        regno.setPrefWidth(100);
        name.setPrefWidth(100);
        cgpa.setPrefWidth(100);
        submit.setPrefWidth(150);
        gp_home.add(hm_welcome,6,2,8,3);
        lb_home.setBorder(new Border(new BorderStroke(Color.rgb(20,30,40), BorderStrokeStyle.SOLID ,new CornerRadii(3),new BorderWidths(1))));
        gp_home.add(lb_home,0,0,10,1);
        gp_home.add(home,0,1,4,1);
        gp_home.add(create,0,2,4,1);
        gp_home.add(read,0,3,4,1);
        gp_home.add(update,0,4,4,1);
        gp_home.add(delete,0,5,4,1);
        gp_home.setPadding(new Insets(30,30,30,30));
        gp_home.setHgap(30);
        gp_home.setVgap(30);
    }

    @Override
    public void start(Stage stage) throws IOException {
        initialise();
        home.setOnAction((ActionEvent e)->{
            reset();
            setHome();
        });
        create.setOnAction((ActionEvent e)->{
            reset();
            submit.setText("Insert Record");
            set_Create_Update();
        });
        read.setOnAction((ActionEvent e)->{
            reset();
            submit.setText("Search");
            setRead();
        });
        update.setOnAction((ActionEvent e)->{
            reset();
            submit.setText("Update Record");
            set_Create_Update();
        });
        delete.setOnAction((ActionEvent e)->{
            reset();
            setDelete();
        });
        submit.setOnAction((ActionEvent e1)->{
            if(submit.getText().toString().equals("Insert Record")){
                onsubmitCreate();
            }
            if(submit.getText().toString().equals("Search")){
                onsubmitRead();
            }
            if(submit.getText().toString().equals("Update Record")){
                onsubmitUpdate();
            }
            if(submit.getText().toString().equals("Delete Record")){
                onsubmitDelete();
            }
        });
        Scene scene = new Scene(gp_home, 500, 450);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}