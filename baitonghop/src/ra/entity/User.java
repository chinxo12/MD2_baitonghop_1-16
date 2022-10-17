package ra.entity;

import javax.swing.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class User implements Serializable{
    private String userName;
    private String password;
    private boolean checkUser = true;

    public User() {
    }

    public User(String userName, String password, boolean checkUser) {
        this.userName = userName;
        this.password = password;
        this.checkUser = checkUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCheckUser() {
        return checkUser;
    }

    public void setCheckUser(boolean checkUser) {
        this.checkUser = checkUser;
    }
    public void register (Scanner scanner, List<User> listUser){
        do {
            System.out.println("Nhập vào tên đăng nhập ");
            String name = scanner.nextLine();
            if (name.trim().length()>=6){
                boolean checkName = false;
                for (User user :listUser) {
                    if (user.getUserName().equals(name)){
                        checkName = true;
                        break;
                    }
                }
                if (!checkName){
                    this.userName = name;
                    break;
                }else {
                    System.err.println("Đã có user này trong hệ thống !");
                }
            }else {
                System.err.println("Vui lòng nhập tên đăng nhập từ 6 ký t !!!");
            }
        }while (true);
        inputPassword(scanner);
    }
    public String inputPassword (Scanner scanner){
        do {
            String passWord;
            do {
                System.out.println("Nhập vào Mật khẩu:");
                passWord = scanner.nextLine();
                if (passWord.trim().length()>=6){
                    break;
                }else {
                    System.err.println("Mật khẩu phải lớn hơn 6 ký tự ");
                }
            } while (true);
            String repeatPassWord;
            do {
                System.out.println("Xác nhận lại mật khẩu: ");
                repeatPassWord = scanner.nextLine();
                if (repeatPassWord.trim().length()>=6){
                    if (passWord.equals(repeatPassWord)){
                        break;
                    }else {
                        System.err.println("Mật khẩu không trùng lặp !!!");
                    }
                }else {
                    System.err.println("Mật khẩu phải lớn hơn 6 ký tự ");
                }
            } while (true);
            this.password = passWord;
            break;
        } while (true);
        return this.password;
    }
    public void getUserData (String path,List<User> list){
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(path);
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            List<User> userList = (List<User>) ois.readObject();
            list.addAll(userList);
        } catch (IOException ex1) {
            ex1.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ois!=null){
                    ois.close();
                }
                if (fis!=null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void writeUser (String path, List<User> list){
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            file = new File(path);
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
    }
    public void changePassword (Scanner scanner, List<User> list){
        System.out.println("Nhập vào ");
    }
}
