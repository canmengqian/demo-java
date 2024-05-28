package design.model.command.demo1.command;

public class ServerCommand extends AbstractCookCommand{
    @Override
    public void execut() {
        super.getDoer ().forEach (d->{
            d.servFood ();
        });
    }
}
