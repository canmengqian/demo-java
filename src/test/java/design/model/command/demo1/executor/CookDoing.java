package design.model.command.demo1.executor;

public interface CookDoing {
    /**
     * 切菜
     */
    void  cut();

    /**
     * 洗菜
     */
    void  wash() ;

    /**
     * 炒菜
     */
    void cook();

    /**
     * 端菜
     */
    void servFood();
}
