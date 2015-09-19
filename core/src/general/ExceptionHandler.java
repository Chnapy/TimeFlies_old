/*
 * 
 * 
 * 
 */
package general;

import com.badlogic.gdx.Gdx;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.Y_AXIS;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * ExceptionHandler.java
 *
 */
public class ExceptionHandler extends JOptionPane implements Thread.UncaughtExceptionHandler {

	private static final int SIZE = 500;

	private final Runnable run;
	private final JTextArea area;

	public ExceptionHandler() {
		Dimension display = Toolkit.getDefaultToolkit().getScreenSize();

		JFrame frame = new JFrame("TimeFlies a planté :'(");
		frame.setLocation((display.width - SIZE) / 2 + SIZE / 2, (display.height - SIZE) / 2 + SIZE / 2);
		frame.setAlwaysOnTop(true);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, Y_AXIS));

		JLabel titre = new JLabel("<html><body>Un bug important est survenu. Veuillez en informer les développeurs en suivant cette démarche :<br/>"
				+ "1/ Copiez le message d'erreur qui suit.<br/>"
				+ "2/ Allez sur le site du jeu <a href='http://timeflies.fr'>http://timeflies.fr</a> et collez le message d'erreur et les informations demandées.</body></html>");
		titre.setAlignmentX(Component.CENTER_ALIGNMENT);
		titre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		titre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://timeflies.fr"));
				} catch (URISyntaxException | IOException ex) {
					// ...
				}
			}
		});
		panel.add(titre);

		area = new JTextArea();
		area.setEditable(false);
		area.setForeground(Color.red);
		panel.add(area);

		run = () -> {
			Gdx.app.exit();
			frame.setVisible(true);
			JOptionPane.showOptionDialog(frame, panel, "Un bug sauvage apparait !", JOptionPane.CLOSED_OPTION,
					JOptionPane.ERROR_MESSAGE, null, null, null);
			System.exit(0);
		};
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		String trace = e.toString() + "\n";
		for (StackTraceElement s : e.getStackTrace()) {
			trace += "\t" + s + "\n";
		}
		area.setText(trace);
		handle(e);
	}

	public void handle(Throwable throwable) {
		try {
			SwingUtilities.invokeLater(run);
		} catch (Throwable t) {
			// don't let the exception get thrown out, will cause infinite looping !
		}
	}

	public static void registerExceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
		System.setProperty("sun.awt.exception.handler", ExceptionHandler.class.getName());
	}
}
