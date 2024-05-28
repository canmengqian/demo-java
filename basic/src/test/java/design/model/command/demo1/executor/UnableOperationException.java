package design.model.command.demo1.executor;

public class UnableOperationException extends Exception{
    public UnableOperationException(String error) {
        super(error);
    }
}
