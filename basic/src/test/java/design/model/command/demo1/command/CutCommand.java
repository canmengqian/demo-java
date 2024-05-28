package design.model.command.demo1.command;

public class CutCommand extends AbstractCookCommand{

    @Override
    public void execut() {
        super.getDoer ().forEach (d->{
            d.cut ();
        });
    }
}
