package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitatHero {
    static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static final double CONCERT_A = 440.0;

    public static void main(String[] args) {
        GuitarString[] strings = new GuitarString[37];
        for (int i = 0; i < strings.length;i++){
            strings[i] = new GuitarString(CONCERT_A*Math.pow(2,(double) (i-24)/12));
        }

        while (true){

            double sample = 0;

            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) != -1) strings[keyboard.indexOf(key)].pluck();
            }

            for (int i = 0; i < strings.length;i++){
                sample +=strings[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < strings.length;i++){
                strings[i].tic();
            }

        }
    }
}
