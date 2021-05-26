package src.components;

public class Songs {
    public static final int NOTE_B0  =31;
    public static final int NOTE_C1  =33;
    public static final int NOTE_CS1 =35;
    public static final int NOTE_D1  =37;
    public static final int NOTE_DS1 =39;
    public static final int NOTE_E1  =41;
    public static final int NOTE_F1  =44;
    public static final int NOTE_FS1 =46;
    public static final int NOTE_G1  =49;
    public static final int NOTE_GS1 =52;
    public static final int NOTE_A1  =55;
    public static final int NOTE_AS1 =58;
    public static final int NOTE_B1  =62;
    public static final int NOTE_C2  =65;
    public static final int NOTE_CS2 =69;
    public static final int NOTE_D2  =73;
    public static final int NOTE_DS2 =78;
    public static final int NOTE_E2  =82;
    public static final int NOTE_F2  =87;
    public static final int NOTE_FS2 =93;
    public static final int NOTE_G2  =98;
    public static final int NOTE_GS2 =104;
    public static final int NOTE_A2  =110;
    public static final int NOTE_AS2 =117;
    public static final int NOTE_B2  =123;
    public static final int NOTE_C3  =131;
    public static final int NOTE_CS3 =139;
    public static final int NOTE_D3  =147;
    public static final int NOTE_DS3 =156;
    public static final int NOTE_E3  =165;
    public static final int NOTE_F3  =175;
    public static final int NOTE_FS3 =185;
    public static final int NOTE_G3  =196;
    public static final int NOTE_GS3 =208;
    public static final int NOTE_A3  =220;
    public static final int NOTE_AS3 =233;
    public static final int NOTE_B3  =247;
    public static final int NOTE_C4  =262;
    public static final int NOTE_CS4 =277;
    public static final int NOTE_D4  =294;
    public static final int NOTE_DS4 =311;
    public static final int NOTE_E4  =330;
    public static final int NOTE_F4  =349;
    public static final int NOTE_FS4 =370;
    public static final int NOTE_G4  =392;
    public static final int NOTE_GS4 =415;
    public static final int NOTE_A4  =440;
    public static final int NOTE_AS4 =466;
    public static final int NOTE_B4  =494;
    public static final int NOTE_C5  =523;
    public static final int NOTE_CS5 =554;
    public static final int NOTE_D5  =587;
    public static final int NOTE_DS5 =622;
    public static final int NOTE_E5  =659;
    public static final int NOTE_F5  =698;
    public static final int NOTE_FS5 =740;
    public static final int NOTE_G5  =784;
    public static final int NOTE_GS5 =831;
    public static final int NOTE_A5  =880;
    public static final int NOTE_AS5 =932;
    public static final int NOTE_B5  =988;
    public static final int NOTE_C6  =1047;
    public static final int NOTE_CS6 =1109;
    public static final int NOTE_D6  =1175;
    public static final int NOTE_DS6 =1245;
    public static final int NOTE_E6  =1319;
    public static final int NOTE_F6  =1397;
    public static final int NOTE_FS6 =1480;
    public static final int NOTE_G6  =1568;
    public static final int NOTE_GS6 =1661;
    public static final int NOTE_A6  =1760;
    public static final int NOTE_AS6 =1865;
    public static final int NOTE_B6  =1976;
    public static final int NOTE_C7  =2093;
    public static final int NOTE_CS7 =2217;
    public static final int NOTE_D7  =2349;
    public static final int NOTE_DS7 =2489;
    public static final int NOTE_E7  =2637;
    public static final int NOTE_F7  =2794;
    public static final int NOTE_FS7 =2960;
    public static final int NOTE_G7  =3136;
    public static final int NOTE_GS7 =3322;
    public static final int NOTE_A7  =3520;
    public static final int NOTE_AS7 =3729;
    public static final int NOTE_B7  =3951;
    public static final int NOTE_C8  =4186;
    public static final int NOTE_CS8 =4435;
    public static final int NOTE_D8  =4699;
    public static final int NOTE_DS8 =4978;
    public static final int REST =0;

    private Integer[][] songs = {
            {
                    //Nummer 1
                    NOTE_E5, 4,  NOTE_B4,8,  NOTE_C5,8,  NOTE_D5,4,  NOTE_C5,8,  NOTE_B4,8,
                    NOTE_A4, 4,  NOTE_A4,8,  NOTE_C5,8,  NOTE_E5,4,  NOTE_D5,8,  NOTE_C5,8,
                    NOTE_B4, -4,  NOTE_C5,8,  NOTE_D5,4,  NOTE_E5,4,
                    NOTE_C5, 4,  NOTE_A4,4,  NOTE_A4,8,  NOTE_A4,4,  NOTE_B4,8,  NOTE_C5,8,

                    NOTE_D5, -4,  NOTE_F5,8,  NOTE_A5,4,  NOTE_G5,8,  NOTE_F5,8,
                    NOTE_E5, -4,  NOTE_C5,8,  NOTE_E5,4,  NOTE_D5,8,  NOTE_C5,8,
                    NOTE_B4, 4,  NOTE_B4,8,  NOTE_C5,8,  NOTE_D5,4,  NOTE_E5,4,
                    NOTE_C5, 4,  NOTE_A4,4,  NOTE_A4,4, REST, 4,

                    NOTE_E5, 4,  NOTE_B4,8,  NOTE_C5,8,  NOTE_D5,4,  NOTE_C5,8,  NOTE_B4,8,
                    NOTE_A4, 4,  NOTE_A4,8,  NOTE_C5,8,  NOTE_E5,4,  NOTE_D5,8,  NOTE_C5,8,
                    NOTE_B4, -4,  NOTE_C5,8,  NOTE_D5,4,  NOTE_E5,4,
                    NOTE_C5, 4,  NOTE_A4,4,  NOTE_A4,8,  NOTE_A4,4,  NOTE_B4,8,  NOTE_C5,8,

                    NOTE_D5, -4,  NOTE_F5,8,  NOTE_A5,4,  NOTE_G5,8,  NOTE_F5,8,
                    NOTE_E5, -4,  NOTE_C5,8,  NOTE_E5,4,  NOTE_D5,8,  NOTE_C5,8,
                    NOTE_B4, 4,  NOTE_B4,8,  NOTE_C5,8,  NOTE_D5,4,  NOTE_E5,4,
                    NOTE_C5, 4,  NOTE_A4,4,  NOTE_A4,4, REST, 4,


                    NOTE_E5,2,  NOTE_C5,2,
                    NOTE_D5,2,   NOTE_B4,2,
                    NOTE_C5,2,   NOTE_A4,2,
                    NOTE_GS4,2,  NOTE_B4,4,  REST,8,
                    NOTE_E5,2,   NOTE_C5,2,
                    NOTE_D5,2,   NOTE_B4,2,
                    NOTE_C5,4,   NOTE_E5,4,  NOTE_A5,2,
                    NOTE_GS5,2,
            },
            {
                    // Bloody Tears, from Castlevania II
                    // Arranged by Bobby Lee. THe flute part was used
                    // https://musescore.com/user/263171/scores/883296
                    //B-flat major Bb Eb
                    REST, 4, NOTE_G5, 4,
                    NOTE_A5, 4, NOTE_AS5, 4,
                    NOTE_A5, 4, NOTE_F5, 4,
                    NOTE_A5, 4, NOTE_G5, 4,
                    REST, 4, NOTE_G5, 4,
                    NOTE_A5, 4, NOTE_AS5, 4,
                    NOTE_C6, 4, NOTE_AS5, 4,

                    NOTE_A5, 4, NOTE_G5, 4, //8
                    REST, 4, NOTE_G5, 4,
                    NOTE_A5, 4, NOTE_AS5, 4,
                    NOTE_A5, 4, NOTE_F5, 4,
                    NOTE_A5, 4, NOTE_G5, 4,
                    NOTE_D6, 4, REST, 8, NOTE_C6, 8,
                    REST, 4, NOTE_AS5, 4,

                    NOTE_A5, 4, NOTE_AS5, 8, NOTE_C6, 8, //15
                    NOTE_F6, 8, REST, 8, REST, 4,
                    NOTE_G5, 16, NOTE_D5, 16, NOTE_D6, 16, NOTE_D5, 16, NOTE_C6, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16,
                    NOTE_A5, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16, NOTE_A5, 16, NOTE_D5, 16, NOTE_G5, 16, NOTE_D5, 16,
                    NOTE_A5, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16, NOTE_C6, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16,

                    NOTE_A5, 16, NOTE_D5, 16, NOTE_F5, 16, NOTE_D5, 16, NOTE_A5, 16, NOTE_D5, 16, NOTE_G5, 16, NOTE_D5, 16, //20
                    NOTE_G5, 16, NOTE_D5, 16, NOTE_D6, 16, NOTE_D5, 16, NOTE_C6, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16,
                    NOTE_A5, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16, NOTE_A5, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16,
                    NOTE_A5, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16, NOTE_C6, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16,
                    NOTE_A5, 16, NOTE_D5, 16, NOTE_F5, 16, NOTE_D5, 16, NOTE_A5, 16, NOTE_D5, 16, NOTE_G5, 16, NOTE_D5, 16,

                    NOTE_G5, 16, NOTE_D5, 16, NOTE_D6, 16, NOTE_D5, 16, NOTE_C6, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16, //25
                    NOTE_A5, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16, NOTE_A5, 16, NOTE_D5, 16, NOTE_G5, 16, NOTE_D5, 16,
                    NOTE_A5, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16, NOTE_C6, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16,
                    NOTE_A5, 16, NOTE_D5, 16, NOTE_F5, 16, NOTE_D5, 16, NOTE_A5, 16, NOTE_D5, 16, NOTE_G5, 16, NOTE_D5, 16,
                    NOTE_AS5, 16, NOTE_D5, 16, NOTE_D6, 16, NOTE_D5, 16, NOTE_C6, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16,

                    NOTE_A5, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16, NOTE_A5, 16, NOTE_D5, 16, NOTE_G5, 16, NOTE_D5, 16,
                    NOTE_A5, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16, NOTE_C6, 16, NOTE_D5, 16, NOTE_AS5, 16, NOTE_D5, 16,
                    NOTE_A5, 16, NOTE_D5, 16, NOTE_F5, 16, NOTE_D5, 16, NOTE_A5, 16, NOTE_D5, 16, NOTE_G5, 16, NOTE_D5, 16,
                    NOTE_C6, 16, NOTE_C6, 16, NOTE_F6, 16, NOTE_D6, 8, REST, 16, REST, 8,
                    REST, 4, NOTE_C6, 16, NOTE_AS5, 16,

                    NOTE_C6, -8,  NOTE_F6, -8, NOTE_D6, -4, //35
                    NOTE_C6, 8, NOTE_AS5, 8,
                    NOTE_C6, 8, NOTE_F6, 16, NOTE_D6, 8, REST, 16, REST, 8,
                    REST, 4, NOTE_C6, 8, NOTE_D6, 8,
                    NOTE_DS6, -8, NOTE_F6, -8,

                    NOTE_D6, -8, REST, 16, NOTE_DS6, 8, REST, 8, //40
                    NOTE_C6, 8, NOTE_F6, 16, NOTE_D6, 8, REST, 16, REST, 8,
                    REST, 4, NOTE_C6, 8, NOTE_AS5, 8,
                    NOTE_C6, -8,  NOTE_F6, -8, NOTE_D6, -4,
                    NOTE_C6, 8, NOTE_AS5, 8,

                    NOTE_C6, 8, NOTE_F6, 16, NOTE_D6, 8, REST, 16, REST, 8, //45
                    REST, 4, NOTE_C6, 8, NOTE_D6, 8,
                    NOTE_DS6, -8, NOTE_F6, -8,
                    NOTE_D5, 8, NOTE_FS5, 8, NOTE_F5, 8, NOTE_A5, 8,
                    NOTE_A5, -8, NOTE_G5, -4,

                    NOTE_A5, -8, NOTE_G5, -4, //50
                    NOTE_A5, -8, NOTE_G5, -4,
                    NOTE_AS5, 8, NOTE_A5, 8, NOTE_G5, 8, NOTE_F5, 8,
                    NOTE_A5, -8, NOTE_G5, -8, NOTE_D5, 8,
                    NOTE_A5, -8, NOTE_G5, -8, NOTE_D5, 8,
                    NOTE_A5, -8, NOTE_G5, -8, NOTE_D5, 8,

                    NOTE_AS5, 4, NOTE_C6, 4, NOTE_A5, 4, NOTE_AS5, 4,
                    NOTE_G5,16, NOTE_D5,16, NOTE_D6,16, NOTE_D5,16, NOTE_C6,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16,//56 //r
                    NOTE_A5,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16, NOTE_A5,16, NOTE_D5,16, NOTE_G5,16, NOTE_D5,16,
                    NOTE_A5,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16, NOTE_C6,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16,
                    NOTE_A5,16, NOTE_D5,16, NOTE_F5,16, NOTE_D5,16, NOTE_A5,16, NOTE_D5,16, NOTE_G5,16, NOTE_D5,16,

                    NOTE_G5,16, NOTE_D5,16, NOTE_D6,16, NOTE_D5,16, NOTE_C6,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16,//61
                    NOTE_A5,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16, NOTE_A5,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16,
                    NOTE_A5,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16, NOTE_C6,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16,
                    NOTE_A5,16, NOTE_D5,16, NOTE_F5,16, NOTE_D5,16, NOTE_A5,16, NOTE_D5,16, NOTE_G5,16, NOTE_D5,16,
                    NOTE_G5,16, NOTE_D5,16, NOTE_D6,16, NOTE_D5,16, NOTE_C6,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16,

                    NOTE_A5,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16, NOTE_A5,16, NOTE_D5,16, NOTE_G5,16, NOTE_D5,16,//66
                    NOTE_A5,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16, NOTE_C6,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16,
                    NOTE_A5,16, NOTE_D5,16, NOTE_F5,16, NOTE_D5,16, NOTE_A5,16, NOTE_D5,16, NOTE_G5,16, NOTE_D5,16,
                    NOTE_AS5,16, NOTE_D5,16, NOTE_D6,16, NOTE_D5,16, NOTE_C6,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16,
                    NOTE_A5,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16, NOTE_A5,16, NOTE_D5,16, NOTE_G5,16, NOTE_D5,16,

                    NOTE_A5,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16, NOTE_C6,16, NOTE_D5,16, NOTE_AS5,16, NOTE_D5,16,//71 //
                    NOTE_A5, 16, NOTE_D5, 16, NOTE_F5, 16, NOTE_D5, 16, NOTE_A5, 8, NOTE_G5, 32, NOTE_A5, 32, NOTE_AS5, 32, NOTE_C6, 32,
                    NOTE_D6, 16, NOTE_G5, 16, NOTE_AS5, 16, NOTE_G5, 16, NOTE_C6, 16, NOTE_G5, 16, NOTE_D6, 16, NOTE_G5, 16,
                    NOTE_C6, 16, NOTE_G5, 16, NOTE_A5, 16, NOTE_G5, 16, NOTE_F6, 16, NOTE_G5, 16, NOTE_D6, 16, NOTE_DS5, 16,
                    NOTE_D6, 4, REST, 4,

                    NOTE_C5, 8, REST, 8, NOTE_A4, -16, NOTE_AS4, -16, NOTE_C5, 16, //76
                    NOTE_D6, 16, NOTE_G4, 16, NOTE_AS4, 16, NOTE_G4, 16, NOTE_C5, 16, NOTE_G4, 16, NOTE_D6, 16, NOTE_G4, 16,
                    NOTE_C6, 16, NOTE_F4, 16, NOTE_A4, 16, NOTE_F4, 16, NOTE_F5, 16, NOTE_F4, 16, NOTE_D6, 16, NOTE_DS4, 16,
                    NOTE_D6, 16, REST, 8, NOTE_E4, 16, NOTE_F4, 16,

                    //change of key B Major A# C# D# F# G#
                    NOTE_GS4, 8, REST, 8, NOTE_AS4, 8, REST, 8,

                    NOTE_DS5, 16, NOTE_GS4, 16, NOTE_B4, 16, NOTE_GS4, 16, NOTE_CS5, 16, NOTE_GS4, 16, NOTE_DS5, 16, NOTE_GS4, 16, //81
                    NOTE_CS5, 16, NOTE_FS4, 16, NOTE_AS4, 16, NOTE_FS4, 16, NOTE_FS5, 16, NOTE_FS4, 16, NOTE_DS5, 16, NOTE_E5, 16,
                    NOTE_D5, 4, REST, 4,
                    NOTE_CS5, 8, REST, 8, NOTE_AS4, -16,  NOTE_B4, -16, NOTE_CS5, 16,
                    NOTE_DS5, 16, NOTE_GS4, 16, NOTE_B4, 16, NOTE_GS4, 16, NOTE_CS5, 16, NOTE_GS4, 16, NOTE_DS5, 16, NOTE_GS4, 16,

                    NOTE_CS5, 16, NOTE_FS4, 16, NOTE_AS4, 16, NOTE_FS4, 16, NOTE_FS5, 16, NOTE_FS4, 16, NOTE_DS5, 16, NOTE_E5, 16,
                    NOTE_DS5, 4, REST, 8, NOTE_DS5, 16,  NOTE_E5, 16,
                    NOTE_FS5, 16, NOTE_CS5, 16, NOTE_E5, 16, NOTE_CS4, 16, NOTE_DS5, 16, NOTE_E5, 16, NOTE_G5, 16, NOTE_AS5, 16,
                    NOTE_GS5, 16, NOTE_DS5, 16, NOTE_DS6, 16, NOTE_DS5, 16, NOTE_CS6, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16,

                    NOTE_AS5, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16, NOTE_AS5, 16, NOTE_DS5, 16, NOTE_GS5, 16, NOTE_DS5, 16, //90
                    NOTE_AS5, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16, NOTE_CS6, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16,
                    NOTE_AS5, 16, NOTE_DS5, 16, NOTE_FS5, 16, NOTE_DS5, 16, NOTE_AS5, 16, NOTE_DS5, 16, NOTE_GS5, 16, NOTE_DS5, 16,
                    NOTE_GS5, 16, NOTE_DS5, 16, NOTE_DS6, 16, NOTE_DS5, 16, NOTE_CS6, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16,

                    NOTE_AS5, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16, NOTE_AS5, 16, NOTE_DS5, 16, NOTE_GS5, 16, NOTE_DS5, 16,//94
                    NOTE_AS5, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16, NOTE_CS6, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16,
                    NOTE_AS5, 16, NOTE_DS5, 16, NOTE_FS5, 16, NOTE_DS5, 16, NOTE_AS5, 16, NOTE_DS5, 16, NOTE_GS5, 16, NOTE_DS5, 16,
                    NOTE_GS5, 16, NOTE_DS5, 16, NOTE_DS6, 16, NOTE_DS5, 16, NOTE_CS6, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16,

                    NOTE_AS5, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16, NOTE_AS5, 16, NOTE_DS5, 16, NOTE_GS5, 16, NOTE_DS5, 16,//98
                    NOTE_AS5, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16, NOTE_CS6, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16,
                    NOTE_AS5, 16, NOTE_DS5, 16, NOTE_FS5, 16, NOTE_DS5, 16, NOTE_AS5, 16, NOTE_DS5, 16, NOTE_GS5, 16, NOTE_DS5, 16,
                    NOTE_GS5, 16, NOTE_DS5, 16, NOTE_DS6, 16, NOTE_DS5, 16, NOTE_CS6, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16,

                    NOTE_AS5, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16, NOTE_AS5, 16, NOTE_DS5, 16, NOTE_GS5, 16, NOTE_DS5, 16,//102
                    NOTE_AS5, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16, NOTE_CS6, 16, NOTE_DS5, 16, NOTE_B5, 16, NOTE_DS5, 16,
                    NOTE_AS5, 16, NOTE_DS5, 16, NOTE_FS5, 16, NOTE_DS5, 16, NOTE_AS5, 16, NOTE_DS5, 16, NOTE_GS5, 16, NOTE_DS5, 16,

                    NOTE_CS6, 8, NOTE_FS6, 16, NOTE_DS6, 8, REST,16, REST,8, //107
                    REST,4, NOTE_CS6, 8, NOTE_B5, 8,
                    NOTE_CS6,-8, NOTE_FS6, -8, NOTE_DS6, -4,
                    NOTE_CS6, 8, NOTE_B5, 8,
                    NOTE_CS6, 8, NOTE_FS6, 16, NOTE_DS6, 8, REST,16, REST,8,
                    REST,4, NOTE_CS6, 8, NOTE_B5, 8,
                    NOTE_E6, -8, NOTE_F6, -8,

                    NOTE_DS6,-8, REST,16, NOTE_E6,8, REST,16, REST,16, //112
                    NOTE_CS6, 8, NOTE_FS6, 16, NOTE_DS6, 8, REST,16, REST,8,
                    REST,4, NOTE_CS6, 8, NOTE_B5, 8,
                    NOTE_CS6,-8, NOTE_FS6, -8, NOTE_DS6, -4,
                    NOTE_CS6, 8, NOTE_B5, 8,

                    NOTE_CS6, 8, NOTE_FS6, 16, NOTE_DS6, 8, REST,16, REST,8, //117
                    REST,4, NOTE_CS5, 8, NOTE_DS5, 8,
                    NOTE_E5, -8, NOTE_F5, -8,
                    NOTE_DS5, 8, NOTE_G5, 8, NOTE_GS5, 8, NOTE_AS5, 8,
                    NOTE_AS5, -8, NOTE_GS5, -8,

                    NOTE_AS5, -8, NOTE_GS5, -8,//122
                    NOTE_AS5, -8, NOTE_GS5, -8,
                    NOTE_B6, 8, NOTE_AS5, 8, NOTE_GS5, 8, NOTE_FS5, 8,
                    NOTE_AS5,-8, NOTE_GS6, -8, NOTE_DS5, 8,
                    NOTE_AS5,-8, NOTE_GS6, -8, NOTE_DS5, 8,
                    NOTE_AS5,-8, NOTE_GS6, -8, NOTE_DS5, 8,

                    NOTE_B5,8, NOTE_CS6, 8, NOTE_AS5, 8, NOTE_B5, 8,//128
                    NOTE_GS5,8, REST,8, REST, 16,
            }, {
            // Hedwig's theme fromn the Harry Potter Movies
            // Socre from https://musescore.com/user/3811306/scores/4906610

            REST, 2, NOTE_D4, 4,
            NOTE_G4, -4, NOTE_AS4, 8, NOTE_A4, 4,
            NOTE_G4, 2, NOTE_D5, 4,
            NOTE_C5, -2,
            NOTE_A4, -2,
            NOTE_G4, -4, NOTE_AS4, 8, NOTE_A4, 4,
            NOTE_F4, 2, NOTE_GS4, 4,
            NOTE_D4, -1,
            NOTE_D4, 4,

            NOTE_G4, -4, NOTE_AS4, 8, NOTE_A4, 4, //10
            NOTE_G4, 2, NOTE_D5, 4,
            NOTE_F5, 2, NOTE_E5, 4,
            NOTE_DS5, 2, NOTE_B4, 4,
            NOTE_DS5, -4, NOTE_D5, 8, NOTE_CS5, 4,
            NOTE_CS4, 2, NOTE_B4, 4,
            NOTE_G4, -1,
            NOTE_AS4, 4,

            NOTE_D5, 2, NOTE_AS4, 4,//18
            NOTE_D5, 2, NOTE_AS4, 4,
            NOTE_DS5, 2, NOTE_D5, 4,
            NOTE_CS5, 2, NOTE_A4, 4,
            NOTE_AS4, -4, NOTE_D5, 8, NOTE_CS5, 4,
            NOTE_CS4, 2, NOTE_D4, 4,
            NOTE_D5, -1,
            REST,4, NOTE_AS4,4,

            NOTE_D5, 2, NOTE_AS4, 4,//26
            NOTE_D5, 2, NOTE_AS4, 4,
            NOTE_F5, 2, NOTE_E5, 4,
            NOTE_DS5, 2, NOTE_B4, 4,
            NOTE_DS5, -4, NOTE_D5, 8, NOTE_CS5, 4,
            NOTE_CS4, 2, NOTE_AS4, 4,
            NOTE_G4, -1,

    }
    };

    public Integer[] getMelody(int songID) {
        return songs[songID];
    }
}
