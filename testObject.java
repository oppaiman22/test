
import java.io.Serializable;

class TestObject implements Serializable {
    int value;
    String id;

    public TestObject(int inputValue, String inputString) {
        this.value = inputValue;
        this.id = inputString;
    }
}
