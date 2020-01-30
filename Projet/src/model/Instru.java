package model;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Instru.
 */
public class Instru {

    /**
     * The Volume.
     */
    public int volume = 100;

    private Synthesizer synthetiseur;
    private MidiChannel canal;

    /**
     * Instantiates a new Instru.
     */
    public Instru() {

        try {
            synthetiseur = MidiSystem.getSynthesizer();
            synthetiseur.open();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(Instru.class.getName()).log(Level.SEVERE, null, ex);
        }
        canal = synthetiseur.getChannels()[0];

        canal.programChange(0);
    }

    /**
     * Note on.
     *
     * @param note the note
     */
    public void note_on(int note) {
        canal.noteOn(note, volume);
    }

    /**
     * Note off.
     *
     * @param note the note
     */
    public void note_off(int note) {
        canal.noteOff(note);
    }

    /**
     * Sets instrument.
     *
     * @param instru the instru
     */
    public void set_instrument(int instru) {
        canal.programChange(instru);
    }
}
