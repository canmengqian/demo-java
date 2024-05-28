package demojava.bean;

public class Test {
    record  Person(String name,int age){}

    public static void main(String[] args) {
      Person person=  new Person ("aa",1);
      person.age ();
      person.name();
    }
}
