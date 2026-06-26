import org.testng.Assert;
import org.testng.annotations.Test;

public class TaskTest {

    @Test
    public void testNewTask() {
        Assert.assertEquals("123", "123");
    }

    @Test
    public void myOwnTest() {
        Assert.assertTrue(5 > 3);
    }
}