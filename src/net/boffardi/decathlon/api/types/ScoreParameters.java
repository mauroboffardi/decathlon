package net.boffardi.decathlon.api.types;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Define a static immutable map to save event score parameters.
 * a bunch of 30 magic numbers!
 * @author mauro.boffardi
 *
 */
public class ScoreParameters {
    public static final Map<String, Double> Sprint;
    public static final Map<String, Double> LongJump;
    public static final Map<String, Double> ShotPut;
    public static final Map<String, Double> HighJump;
    public static final Map<String, Double> FourHundreds;
    public static final Map<String, Double> Hurdles;
    public static final Map<String, Double> Discus;
    public static final Map<String, Double> PoleVault;
    public static final Map<String, Double> Javelin;
    public static final Map<String, Double> M1500Sprint;
    
    static {
        Map<String, Double> mMap1 = new HashMap<String,Double>();
        // sprint
        mMap1.put("A", 25.4347);
        mMap1.put("B", 18.0);
        mMap1.put("C", 1.81);
        Sprint = Collections.unmodifiableMap(mMap1);

        Map<String, Double> mMap2 = new HashMap<String,Double>();
        // longjump
        mMap2.put("A", 0.14354);
        mMap2.put("B", 220.0);
        mMap2.put("C", 1.4);
        LongJump = Collections.unmodifiableMap(mMap2);

        Map<String, Double> mMap3 = new HashMap<String,Double>();
        // shot put
        mMap3.put("A", 51.39);
        mMap3.put("B", 1.5);
        mMap3.put("C", 1.05);
        ShotPut = Collections.unmodifiableMap(mMap3);

        Map<String, Double> mMap4 = new HashMap<String,Double>();
        // high Jump
        mMap4.put("A", 0.8465);
        mMap4.put("B", 75.0);
        mMap4.put("C", 1.42);
        HighJump = Collections.unmodifiableMap(mMap4);

        Map<String, Double> mMap5 = new HashMap<String,Double>();
        // 400m
        mMap5.put("A", 1.53775);
        mMap5.put("B", 82.0);
        mMap5.put("C", 1.81);
        FourHundreds = Collections.unmodifiableMap(mMap5);

        Map<String, Double> mMap6 = new HashMap<String,Double>();
        // Hurdles
        mMap6.put("A", 5.74352);
        mMap6.put("B", 28.5);
        mMap6.put("C", 1.92);
        Hurdles = Collections.unmodifiableMap(mMap6);
        
        Map<String, Double> mMap7 = new HashMap<String,Double>();
        // Discus
        mMap7.put("A", 12.91);
        mMap7.put("B", 4.0);
        mMap7.put("C", 1.1);
        Discus = Collections.unmodifiableMap(mMap7);

        Map<String, Double> mMap8 = new HashMap<String,Double>();
        // Pole Vault
        mMap8.put("A", 0.2797);
        mMap8.put("B", 100.0);
        mMap8.put("C", 1.35);
        PoleVault = Collections.unmodifiableMap(mMap8);

        Map<String, Double> mMap9 = new HashMap<String,Double>();
        // Javelin
        mMap9.put("A", 10.14);
        mMap9.put("B", 7.0);
        mMap9.put("C", 1.08);
        Javelin = Collections.unmodifiableMap(mMap9);

        Map<String, Double> mMap10 = new HashMap<String,Double>();
        // 1500m
        mMap10.put("A", 0.03768);
        mMap10.put("B", 480.0);
        mMap10.put("C", 1.85);
        M1500Sprint = Collections.unmodifiableMap(mMap10);
    }

}
