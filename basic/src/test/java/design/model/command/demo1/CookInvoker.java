package design.model.command.demo1;

import design.model.command.demo1.command.CookCommand;

import java.util.List;

public class CookInvoker {
    public  void executor(List<CookCommand> commands){
        for(CookCommand command:commands){
            command.execut ();
        }
    };

    public  void executor(CookCommand command){
            command.execut ();
    };
}
