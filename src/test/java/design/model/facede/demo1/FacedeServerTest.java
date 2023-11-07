package design.model.facede.demo1;

import org.junit.jupiter.api.Test;

public class FacedeServerTest {
    @Test
    public void test(){
        FacedeServer.introduce ();
        FacedeServer.cooking ().forEach (System.out::println);
        FacedeServer.wash ();
        FacedeServer.settle (199.88);
    }
}
