import org.junit.Assert;
import org.junit.Test ;

public class TestClient {

    @Test
    public void testUserName(){
        Assert.assertTrue(GameApp.getUserName().getClass().equals(String.class));
    }



}
