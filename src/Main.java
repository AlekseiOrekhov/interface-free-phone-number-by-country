import threads.InitCacheThread;
import swing.windows.MainWindow;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        // инициация cache по странам и телефонам
        InitCacheThread initCacheThread = new InitCacheThread();
        initCacheThread.start();

        // заглушка чтобы подгрузить данные по странам
            Thread.sleep(2500);

        // инициализация формы
        JFrame.setDefaultLookAndFeelDecorated(true);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainWindow g = new MainWindow();
                try {
                    g.createGUI();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
