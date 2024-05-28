package design.model.command.demo1.executor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WoManDoing implements CookDoing{

    @Override
    public void cut() {
       log.error ("女人不会切菜");

    }

    @Override
    public void wash()  {
        log.info ("女人洗菜");
    }

    @Override
    public void cook() {
       log.error ("女人不会炒菜");
    }

    @Override
    public void servFood() {
        log.info ("女人端菜");
    }
}
