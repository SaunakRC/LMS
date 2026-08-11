package LearningSystem.Service;

import LearningSystem.Entity.User;

public interface UserService {

    User signup(User user);

    User signin(String email, String password);

    String logout();

}