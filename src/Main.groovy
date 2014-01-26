import com.zoowii.scheme.SObject
import com.zoowii.scheme.Scheme

/**
 * Created by zoowii on 14-1-26.
 */
class Main {
    public static void main(String[] args) {
        Scheme scheme = new Scheme()
        List<SObject> code = new LinkedList<>()
        SObject result = scheme.run(code)
        println("result: " + result)
    }
}
