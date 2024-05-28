package design.model.command.demo1;

import cn.hutool.core.collection.CollUtil;
import design.model.command.demo1.command.*;
import design.model.command.demo1.executor.CookDoing;
import design.model.command.demo1.executor.ManDoing;
import design.model.command.demo1.executor.WoManDoing;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CookInvokerTest {

    @Test
    public void test(){
        CookInvoker invoker = new CookInvoker ();
        List<CookDoing> doers = CollUtil.newArrayList (new ManDoing(),new WoManDoing());
        CookCommand washCommand=new WashCommand();
        washCommand.setDoer (doers);
        invoker.executor (washCommand);

        CookCommand cut=new CutCommand();
        cut.setDoer (doers);
        invoker.executor (cut);

        CookCommand cooking=new CookingCommand();
        cooking.setDoer (doers);
        invoker.executor (cooking);

        CookCommand serv=new ServerCommand();
        serv.setDoer (doers);
        invoker.executor (serv);

    }
}
