package design.model.filter.demo1;

public class AgeGe25Filter extends PersonFilter {
    @Override
    public Boolean filter(Person person) {
        return person.getAge ()>=25;
    }
}
