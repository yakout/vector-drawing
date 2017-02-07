package mvc.model;

import exceptions.CannotUndoException;
import exceptions.HistoryIsEmptyException;
import mvc.controller.PainterPanelController;
import mvc.view.MainGuiView;
import shapes.Shape;

import java.util.List;

/**
 * Created by ahmedyakout on 10/29/16.
 */
public class UndoCommand implements Command {

    public UndoCommand() {
    }

    @Override
    public void execute() {
        try {
            Model.getModel().getHistory().undo();
            System.out.println(Model.getModel().getHistory().primaryHistoryStack.size());
            for(List<Shape> shapes : Model.getModel().getHistory().primaryHistoryStack) {
                System.out.println(shapes.size());
            }
            PainterPanelController.getPainterPanel().repaint();
        } catch (HistoryIsEmptyException e1) {
            MainGuiView.getMainGuiView().showError(e1.toString());
        } catch (CannotUndoException e2) {
            MainGuiView.getMainGuiView().showError(e2.toString());
        }
    }
}
