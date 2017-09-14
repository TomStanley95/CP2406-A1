import org.junit.Assert;
import org.junit.Test ;

public class TestClient {

    @Test
    public void testUserName(){
        Assert.assertTrue(GameClient.getUserName().getClass().equals(String.class));
    }



}
