package src.com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ValidateList {
    List<User> userList;

    public ValidateList() {
        userList = new ArrayList<>();
    }

    public ValidateList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> uuidList) {
        this.userList = uuidList;
    }

    public void add(User user) {
        this.userList.add(user);
    }

    @Override
    public String toString() {
        return "ValidateList{" +
                "userList=" + userList +
                '}';
    }
}
