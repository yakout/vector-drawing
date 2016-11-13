package mvc.model;

import exceptions.CannotRedoException;
import exceptions.HistoryIsEmptyException;
import mvc.controller.PainterPanelController;
import mvc.view.MainGuiView;

/**
 * Created by ahmedyakout on 10/29/16.
 */
public class RedoCommand implements Command {
    public RedoCommand() {
    }

    @Override
    public void execute() {
        try {
            Model.getModel().getHistory().redo();
            PainterPanelController.getPainterPanel().repaint();
        } catch (HistoryIsEmptyException e1) {
            MainGuiView.getMainGuiView().showError(e1.toString());
        } catch (CannotRedoException e2) {
            MainGuiView.getMainGuiView().showError(e2.toString());
        }
    }
}
