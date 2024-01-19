package nl.novi.gamenight.Dto.User;


import nl.novi.gamenight.Model.User.UserRole;

public class UserOutputDto {

    public Long userID;
    public String userName;
    public UserRole userRole;


    /* TODO Delete password from outputDto when password is encoded*/
    public String password;

}
