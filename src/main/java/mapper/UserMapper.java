package mapper;
import org.apache.ibatis.annotations.Param;

import domain.User;

import java.util.List;

public interface UserMapper {
    int insertUser(User user);
    List<User> selectUser();
    List<User> findAllByUsername(@Param("username")String username);
    List<User> findAllByUsernameAndPassword(@Param("username")String username,@Param("password")String password);











}
