package design.model.command.demo1.command;

public class CookingCommand extends AbstractCookCommand{
    @Override
    public void execut() {
        super.getDoer ().forEach (d->{
            d.cook ();
        });
    }
}
