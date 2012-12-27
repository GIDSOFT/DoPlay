/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.filechooser.FileNameExtensionFilter;
import javazoom.jlgui.basicplayer.BasicPlayer;

/**
 *
 * @author Brayhan
 */
public class Reproductor {

    //el player
    private Player mediaPlayer;
//animacion
    private Timer tiempo;
    private TimerTask task;
    private int speed = 1000;//velocidad
    private int frame = 0;
//control    
    boolean run = false;
    boolean todo_ok = false;
//archivo
    URL mediaURL = null;
    String file = "";
//otros
    String t = null;
//filtro
    private static FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo MP3", "mp3", "mp3");

    public void Continuar() throws Exception {
        mediaPlayer.start();
    }

    public void Stop() throws Exception {
        mediaPlayer.stop();
    }

    public String abrirCancion() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                //si se esta reproduciendo un mp3, se detiene
                if (todo_ok) {
                    this.STOP();
                }

                //se asigna a mediaURL el archivo de video seleccionado
                URL url = fileChooser.getSelectedFile().toURL();
                //se coloca el nombre de la cancion en la variable file
                this.file = fileChooser.getSelectedFile().getName();
                //se asigna el mp3 al reproductor
                mediaPlayer = Manager.createRealizedPlayer(url);
                //se coloca a true
                this.todo_ok = true;
            } catch (NoPlayerException ex) {
                Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CannotRealizeException ex) {
                Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.file;
    }

    private void STOP() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public String PLAY2(JSlider b) {
        t = "No existe ningun archivo...";
        if (todo_ok) {
            if (!run) {//si ya se esta reproduciendo
                mediaPlayer.start();
                this.run = true;
                t = "Reproduciendo";

            }
        }
        return t;
    }
}
