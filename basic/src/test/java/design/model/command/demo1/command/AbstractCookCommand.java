package design.model.command.demo1.command;

import design.model.command.demo1.executor.CookDoing;

import java.util.List;


public abstract class AbstractCookCommand implements CookCommand {
    List<CookDoing> doer;


    public List<CookDoing> getDoer() {
        return doer;
    }

    public void setDoer(List<CookDoing> doer) {
        this.doer = doer;
    }
}
