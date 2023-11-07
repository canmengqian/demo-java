package design.model.command.demo1.command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WashCommand extends AbstractCookCommand{

    @Override
    public void execut() {
        super.getDoer ().forEach (d->{
            d.wash ();
        });
    }
}
