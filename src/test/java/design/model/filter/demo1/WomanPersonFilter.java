package design.model.filter.demo1;

import java.util.List;

public class WomanPersonFilter extends PersonFilter{
    @Override
    public Boolean filter(Person people) {
        return people.getSex () ==0;
    }
}
