package mvc.model;

/**
 * Created by ahmedyakout on 10/29/16.
 */
public class UpdateCommand implements Command {
    public UpdateCommand() {
    }

    @Override
    public void execute() {
        Model.getModel().getHistory().update();
    }
}
