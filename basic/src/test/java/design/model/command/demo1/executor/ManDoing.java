package design.model.command.demo1.executor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ManDoing implements CookDoing{

    @Override
    public void cut() {
        log.info ("男人切菜");
    }

    @Override
    public void wash()  {
  log.error ("男人不会洗菜");
    }

    @Override
    public void cook() {
        log.info ("男人炒菜");
    }

    @Override
    public void servFood() {
        log.info ("男人端菜");
    }
}
