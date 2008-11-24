import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class HelloWorld extends MIDlet 
       implements CommandListener {
  // The exit command
  private Command exitCommand; 
  // The display for this MIDlet
  private Display display;    
  // create a ticker
  private Ticker hi = new Ticker("J2ME is cool"); 

  public HelloWorld() {
    display = Display.getDisplay(this);
    exitCommand = new Command("Exit", Command.SCREEN, 2);
  }

  public void startApp() {
    TextBox t = new TextBox("Hello MIDlet", 
                "Wireless Internet", 256, 0);

    t.addCommand(exitCommand);
    t.setCommandListener(this);
    t.setTicker(hi); // set the ticker
    display.setCurrent(t);
  }
  public void pauseApp() { }
  public void destroyApp(boolean unconditional) { }
  public void commandAction(Command c, Displayable s) {
    if (c == exitCommand) {
      destroyApp(false);
      notifyDestroyed();
    }
  }
}