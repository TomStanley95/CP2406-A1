import org.junit.Assert;
import org.junit.Test ;

public class TestClient {

    @Test
    public void testUserName(){
        //Tests that my username is a string
        Assert.assertTrue(GameApp.getUserName().getClass().equals(String.class));

        // TODO put this in Test Client/Server
        // TODO add test for maximum users
//        String userName = "Tron";
//        ClientCommunication.addUserToServer(userName);
//        ClientCommunication.addUserToServer(userName);

    }



}
