package mapper;

import domain.User;

import java.util.List;

public interface UserMapper {
    int insertUser(User user);
    List<User> selectUser();
}
