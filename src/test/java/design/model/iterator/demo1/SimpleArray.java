package design.model.iterator.demo1;

import io.vavr.collection.Array;

import java.util.ArrayList;
import java.util.List;

public class SimpleArray<T> {
  private List<T> elements = new ArrayList<> ();



    public  void add(T t){
       elements.add (t);
    }

    public Iterator<T> iterator(){
        return  new SimpleArrayIterator ();
    }

    private class  SimpleArrayIterator implements Iterator<T> {

        private  int index=0;
        @Override
        public boolean hasNext() {
            return index < elements.size ();
        }

        @Override
        public T next() {
         T t =   elements.get (index);
         index++;
            return t;
        }
    }
}
