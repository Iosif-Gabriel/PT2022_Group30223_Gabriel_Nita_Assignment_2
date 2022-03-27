import View.View;
import Controller.ControllerQueues;
public class MVC {
    public static void main(String[] args) {
        View inter=new View();
        ControllerQueues controller=new ControllerQueues(inter);
        inter.setVisible(true);
    }
}
