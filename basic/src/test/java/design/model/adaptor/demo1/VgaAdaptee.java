package design.model.adaptor.demo1;

/**
 * 旧版已经实现的Vga接口
 */
public class VgaAdaptee {
  public   Vga getVga(){
      return new Vga ("低清图像");
    }
}
