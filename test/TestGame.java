import org.junit.Assert;
import org.junit.Test ;

public class TestGame {
//    @Test
//    public void testGetUserName(){
//        //Tests that the username returned is a string
//        Assert.assertTrue(GameApp.getUserName().getClass().equals(String.class));
//    }
    @Test
    public void testAddUserToServer() throws Exception{
        String userName = "Tron";
        Assert.assertEquals("Player Tron added successfully",GameServer.addUserToGame(userName) );

    }
    @Test
    public void testAddMultipleSameToServer() throws Exception{
        String userName = "Tron";
        GameServer.addUserToGame(userName);
        Assert.assertEquals("Failed, that user already exists",GameServer.addUserToGame(userName));
    }
    @Test
    public void add21UsersToServer() throws Exception{
        for (int i = 0; i < 19 - 1; i++){
            String user = "" + i;
            GameServer.addUserToGame(user);
        }
        Assert.assertEquals("Failed, the game lobby is full", GameServer.addUserToGame("Post"));
    }
    @Test
    public void removeCurrentUserFromGame(){
        String userName = "Tron";
        GameServer.addUserToGame(userName);
        Assert.assertEquals("Player " + userName + " removed successfully", GameServer.removeUserFromGame(userName));
    }
    @Test
    public void removeInvalidUserFromGame(){
        String userName = "Tron";
        GameServer.addUserToGame(userName);
        Assert.assertEquals("Failed, that player doesn't exists", GameServer.removeUserFromGame("Larry"));
    }
}
