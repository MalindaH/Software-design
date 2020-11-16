package activity5.Interface;

public interface Command
{
    /**
     * method that execute the concrete command
     */
    public void execute();

    /**
     * method to undo the command
     */
    public void undo();
}
