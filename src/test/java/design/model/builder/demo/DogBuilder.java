package design.model.builder.demo;

public class DogBuilder {
    private String name;
    private String type;
    public Dog build(){
        return  new Dog (this.name,this.type);
    }
    public static DogBuilder builder(){
        return  new DogBuilder ();
    }

    public DogBuilder name(String name){
        this.name = name;
        return  this;
    }
    public DogBuilder type(String type){
        this.type = type;
        return  this;
    }
}
