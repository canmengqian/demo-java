package design.model.builder.demo;

import org.junit.jupiter.api.Test;

public class DogBuilderTest {
    @Test
    public  void test(){
        System.out.println (DogBuilder.builder ().name ("铁子").type ("德牧").build ().toString ());
      Dog dog=  DogBuilder.builder ().name ("金子").type ("金毛").build ();
        System.out.println (dog.toString ());
    }

    public  void  testJson(){

    }

}
