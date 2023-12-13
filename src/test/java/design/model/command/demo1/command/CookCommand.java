package design.model.command.demo1.command;

import design.model.command.demo1.executor.CookDoing;

import java.util.List;

public interface CookCommand {
    public  void execut();

    public  void setDoer(List<CookDoing> doers);
}
