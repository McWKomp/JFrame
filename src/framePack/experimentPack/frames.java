package framePack.experimentPack;

import java.awt.event.ActionEvent;

public class frames {

    public static void main(String[] args) {
        frameTask frame = new frameTask();
        frame.actionPerformed(new ActionEvent(frame, 0, ""));
    }
}
