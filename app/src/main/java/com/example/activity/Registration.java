package com.example.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Registration extends AppCompatActivity {
    private static final int picCode = 123;
    ImageView profileLabel;
    boolean dateset = false;
    GradientDrawable border = new GradientDrawable();
    Spinner q1, q2, q3;
    int error_ctr = 0;
    //CONTEXT PARA SA INTENT
    Context con = this;

    //UPDATED SINGLETON IMPLEMENTATION METHOD
    //CHECK DBHANDLER.JAVA

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_registration);

        String[] municipality = new String[]{
                "ADAMS", "BACARRA", "BADOC", "BANGUI", "CITY OF BATAC", "BURGOS", "CARASI", "CURRIMAO", "DINGRAS", "DUMALNEG", "BANNA (ESPIRITU)", "LAOAG CITY (Capital)", "MARCOS", "NUEVA ERA", "PAGUDPUD", "PAOAY", "PASUQUIN", "PIDDIG", "PINILI", "SAN NICOLAS", "SARRAT", "SOLSONA", "VINTAR", "ALILEM", "BANAYOYO", "BANTAY", "BURGOS", "CABUGAO", "CITY OF CANDON", "CAOAYAN", "CERVANTES", "GALIMUYOD", "GREGORIO DEL PILAR (CONCEPCION)", "LIDLIDDA", "MAGSINGAL", "NAGBUKEL", "NARVACAN", "QUIRINO (ANGKAKI)", "SALCEDO (BAUGEN)", "SAN EMILIO", "SAN ESTEBAN", "SAN ILDEFONSO", "SAN JUAN (LAPOG)", "SAN VICENTE", "SANTA", "SANTA CATALINA", "SANTA CRUZ", "SANTA LUCIA", "SANTA MARIA", "SANTIAGO", "SANTO DOMINGO", "SIGAY", "SINAIT", "SUGPON", "SUYO", "TAGUDIN", "CITY OF VIGAN (Capital)", "AGOO", "ARINGAY", "BACNOTAN", "BAGULIN", "BALAOAN", "BANGAR", "BAUANG", "BURGOS", "CABA", "LUNA", "NAGUILIAN", "PUGO", "ROSARIO", "CITY OF SAN FERNANDO (Capital)", "SAN GABRIEL", "SAN JUAN", "SANTO TOMAS", "SANTOL", "SUDIPEN", "TUBAO", "AGNO", "AGUILAR", "CITY OF ALAMINOS", "ALCALA", "ANDA", "ASINGAN", "BALUNGAO", "BANI", "BASISTA", "BAUTISTA", "BAYAMBANG", "BINALONAN", "BINMALEY", "BOLINAO", "BUGALLON", "BURGOS", "CALASIAO", "DAGUPAN CITY", "DASOL", "INFANTA", "LABRADOR", "LINGAYEN (Capital)", "MABINI", "MALASIQUI", "MANAOAG", "MANGALDAN", "MANGATAREM", "MAPANDAN", "NATIVIDAD", "POZORRUBIO", "ROSALES", "SAN CARLOS CITY", "SAN FABIAN", "SAN JACINTO", "SAN MANUEL", "SAN NICOLAS", "SAN QUINTIN", "SANTA BARBARA", "SANTA MARIA", "SANTO TOMAS", "SISON", "SUAL", "TAYUG", "UMINGAN", "URBIZTONDO", "CITY OF URDANETA", "VILLASIS", "LAOAC", "BASCO (Capital)", "ITBAYAT", "IVANA", "MAHATAO", "SABTANG", "UYUGAN", "ABULUG", "ALCALA", "ALLACAPAN", "AMULUNG", "APARRI", "BAGGAO", "BALLESTEROS", "BUGUEY", "CALAYAN", "CAMALANIUGAN", "CLAVERIA", "ENRILE", "GATTARAN", "GONZAGA", "IGUIG", "LAL-LO", "LASAM", "PAMPLONA", "PEÃ‘ABLANCA", "PIAT", "RIZAL", "SANCHEZ-MIRA", "SANTA ANA", "SANTA PRAXEDES", "SANTA TERESITA", "SANTO NIÃ‘O (FAIRE)", "SOLANA", "TUAO", "TUGUEGARAO CITY (Capital)", "ALICIA", "ANGADANAN", "AURORA", "BENITO SOLIVEN", "BURGOS", "CABAGAN", "CABATUAN", "CITY OF CAUAYAN", "CORDON", "DINAPIGUE", "DIVILACAN", "ECHAGUE", "GAMU", "ILAGAN CITY (Capital)", "JONES", "LUNA", "MACONACON", "DELFIN ALBANO (MAGSAYSAY)", "MALLIG", "NAGUILIAN", "PALANAN", "QUEZON", "QUIRINO", "RAMON", "REINA MERCEDES", "ROXAS", "SAN AGUSTIN", "SAN GUILLERMO", "SAN ISIDRO", "SAN MANUEL", "SAN MARIANO", "SAN MATEO", "SAN PABLO", "SANTA MARIA", "CITY OF SANTIAGO", "SANTO TOMAS", "TUMAUINI", "AMBAGUIO", "ARITAO", "BAGABAG", "BAMBANG", "BAYOMBONG (Capital)", "DIADI", "DUPAX DEL NORTE", "DUPAX DEL SUR", "KASIBU", "KAYAPA", "QUEZON", "SANTA FE", "SOLANO", "VILLAVERDE", "ALFONSO CASTANEDA", "AGLIPAY", "CABARROGUIS (Capital)", "DIFFUN", "MADDELA", "SAGUDAY", "NAGTIPUNAN", "ABUCAY", "BAGAC", "CITY OF BALANGA (Capital)", "DINALUPIHAN", "HERMOSA", "LIMAY", "MARIVELES", "MORONG", "ORANI", "ORION", "PILAR", "SAMAL", "ANGAT", "BALAGTAS (BIGAA)", "BALIUAG", "BOCAUE", "BULACAN", "BUSTOS", "CALUMPIT", "GUIGUINTO", "HAGONOY", "CITY OF MALOLOS (Capital)", "MARILAO", "CITY OF MEYCAUAYAN", "NORZAGARAY", "OBANDO", "PANDI", "PAOMBONG", "PLARIDEL", "PULILAN", "SAN ILDEFONSO", "CITY OF SAN JOSE DEL MONTE", "SAN MIGUEL", "SAN RAFAEL", "SANTA MARIA", "DOÃ‘A REMEDIOS TRINIDAD", "ALIAGA", "BONGABON", "CABANATUAN CITY", "CABIAO", "CARRANGLAN", "CUYAPO", "GABALDON (BITULOK & SABANI)", "CITY OF GAPAN", "GENERAL MAMERTO NATIVIDAD", "GENERAL TINIO (PAPAYA)", "GUIMBA", "JAEN", "LAUR", "LICAB", "LLANERA", "LUPAO", "SCIENCE CITY OF MUÃ‘OZ", "NAMPICUAN", "PALAYAN CITY (Capital)", "PANTABANGAN", "PEÃ‘ARANDA", "QUEZON", "RIZAL", "SAN ANTONIO", "SAN ISIDRO", "SAN JOSE CITY", "SAN LEONARDO", "SANTA ROSA", "SANTO DOMINGO", "TALAVERA", "TALUGTUG", "ZARAGOZA", "ANGELES CITY", "APALIT", "ARAYAT", "BACOLOR", "CANDABA", "FLORIDABLANCA", "GUAGUA", "LUBAO", "MABALACAT CITY", "MACABEBE", "MAGALANG", "MASANTOL", "MEXICO", "MINALIN", "PORAC", "CITY OF SAN FERNANDO (Capital)", "SAN LUIS", "SAN SIMON", "SANTA ANA", "SANTA RITA", "SANTO TOMAS", "SASMUAN (Sexmoan)", "ANAO", "BAMBAN", "CAMILING", "CAPAS", "CONCEPCION", "GERONA", "LA PAZ", "MAYANTOC", "MONCADA", "PANIQUI", "PURA", "RAMOS", "SAN CLEMENTE", "SAN MANUEL", "SANTA IGNACIA", "CITY OF TARLAC (Capital)", "VICTORIA", "SAN JOSE", "BOTOLAN", "CABANGAN", "CANDELARIA", "CASTILLEJOS", "IBA (Capital)", "MASINLOC", "OLONGAPO CITY", "PALAUIG", "SAN ANTONIO", "SAN FELIPE", "SAN MARCELINO", "SAN NARCISO", "SANTA CRUZ", "SUBIC", "BALER (Capital)", "CASIGURAN", "DILASAG", "DINALUNGAN", "DINGALAN", "DIPACULAO", "MARIA AURORA", "SAN LUIS", "AGONCILLO", "ALITAGTAG", "BALAYAN", "BALETE", "BATANGAS CITY (Capital)", "BAUAN", "CALACA", "CALATAGAN", "CUENCA", "IBAAN", "LAUREL", "LEMERY", "LIAN", "LIPA CITY", "LOBO", "MABINI", "MALVAR", "MATAASNAKAHOY", "NASUGBU", "PADRE GARCIA", "ROSARIO", "SAN JOSE", "SAN JUAN", "SAN LUIS", "SAN NICOLAS", "SAN PASCUAL", "SANTA TERESITA", "SANTO TOMAS", "TAAL", "TALISAY", "CITY OF TANAUAN", "TAYSAN", "TINGLOY", "TUY", "ALFONSO", "AMADEO", "BACOOR CITY", "CARMONA", "CAVITE CITY", "CITY OF DASMARIÃ‘AS", "GENERAL EMILIO AGUINALDO", "GENERAL TRIAS", "IMUS CITY", "INDANG", "KAWIT", "MAGALLANES", "MARAGONDON", "MENDEZ (MENDEZ-NUÃ‘EZ)", "NAIC", "NOVELETA", "ROSARIO", "SILANG", "TAGAYTAY CITY", "TANZA", "TERNATE", "TRECE MARTIRES CITY (Capital)", "GEN. MARIANO ALVAREZ", "ALAMINOS", "BAY", "CITY OF BIÃ‘AN", "CABUYAO CITY", "CITY OF CALAMBA", "CALAUAN", "CAVINTI", "FAMY", "KALAYAAN", "LILIW", "LOS BAÃ‘OS", "LUISIANA", "LUMBAN", "MABITAC", "MAGDALENA", "MAJAYJAY", "NAGCARLAN", "PAETE", "PAGSANJAN", "PAKIL", "PANGIL", "PILA", "RIZAL", "SAN PABLO CITY", "CITY OF SAN PEDRO", "SANTA CRUZ (Capital)", "SANTA MARIA", "CITY OF SANTA ROSA", "SINILOAN", "VICTORIA", "AGDANGAN", "ALABAT", "ATIMONAN", "BUENAVISTA", "BURDEOS", "CALAUAG", "CANDELARIA", "CATANAUAN", "DOLORES", "GENERAL LUNA", "GENERAL NAKAR", "GUINAYANGAN", "GUMACA", "INFANTA", "JOMALIG", "LOPEZ", "LUCBAN", "LUCENA CITY (Capital)", "MACALELON", "MAUBAN", "MULANAY", "PADRE BURGOS", "PAGBILAO", "PANUKULAN", "PATNANUNGAN", "PEREZ", "PITOGO", "PLARIDEL", "POLILLO", "QUEZON", "REAL", "SAMPALOC", "SAN ANDRES", "SAN ANTONIO", "SAN FRANCISCO (AURORA)", "SAN NARCISO", "SARIAYA", "TAGKAWAYAN", "CITY OF TAYABAS", "TIAONG", "UNISAN", "ANGONO", "CITY OF ANTIPOLO", "BARAS", "BINANGONAN", "CAINTA", "CARDONA", "JALA-JALA", "RODRIGUEZ (MONTALBAN)", "MORONG", "PILILLA", "SAN MATEO", "TANAY", "TAYTAY", "TERESA", "BOAC (Capital)", "BUENAVISTA", "GASAN", "MOGPOG", "SANTA CRUZ", "TORRIJOS", "ABRA DE ILOG", "CALINTAAN", "LOOC", "LUBANG", "MAGSAYSAY", "MAMBURAO (Capital)", "PALUAN", "RIZAL", "SABLAYAN", "SAN JOSE", "SANTA CRUZ", "BACO", "BANSUD", "BONGABONG", "BULALACAO (SAN PEDRO)", "CITY OF CALAPAN (Capital)", "GLORIA", "MANSALAY", "NAUJAN", "PINAMALAYAN", "POLA", "PUERTO GALERA", "ROXAS", "SAN TEODORO", "SOCORRO", "VICTORIA", "ABORLAN", "AGUTAYA", "ARACELI", "BALABAC", "BATARAZA", "BROOKE'S POINT", "BUSUANGA", "CAGAYANCILLO", "CORON", "CUYO", "DUMARAN", "EL NIDO (BACUIT)", "LINAPACAN", "MAGSAYSAY", "NARRA", "PUERTO PRINCESA CITY (Capital)", "QUEZON", "ROXAS", "SAN VICENTE", "TAYTAY", "KALAYAAN", "CULION", "RIZAL (MARCOS)", "SOFRONIO ESPAÃ‘OLA", "ALCANTARA", "BANTON", "CAJIDIOCAN", "CALATRAVA", "CONCEPCION", "CORCUERA", "LOOC", "MAGDIWANG", "ODIONGAN", "ROMBLON (Capital)", "SAN AGUSTIN", "SAN ANDRES", "SAN FERNANDO", "SAN JOSE", "SANTA FE", "FERROL", "SANTA MARIA (IMELDA)", "BACACAY", "CAMALIG", "DARAGA (LOCSIN)", "GUINOBATAN", "JOVELLAR", "LEGAZPI CITY (Capital)", "LIBON", "CITY OF LIGAO", "MALILIPOT", "MALINAO", "MANITO", "OAS", "PIO DURAN", "POLANGUI", "RAPU-RAPU", "SANTO DOMINGO (LIBOG)", "CITY OF TABACO", "TIWI", "BASUD", "CAPALONGA", "DAET (Capital)", "SAN LORENZO RUIZ (IMELDA)", "JOSE PANGANIBAN", "LABO", "MERCEDES", "PARACALE", "SAN VICENTE", "SANTA ELENA", "TALISAY", "VINZONS", "BAAO", "BALATAN", "BATO", "BOMBON", "BUHI", "BULA", "CABUSAO", "CALABANGA", "CAMALIGAN", "CANAMAN", "CARAMOAN", "DEL GALLEGO", "GAINZA", "GARCHITORENA", "GOA", "IRIGA CITY", "LAGONOY", "LIBMANAN", "LUPI", "MAGARAO", "MILAOR", "MINALABAC", "NABUA", "NAGA CITY", "OCAMPO", "PAMPLONA", "PASACAO", "PILI (Capital)", "PRESENTACION (PARUBCAN)", "RAGAY", "SAGÃ‘AY", "SAN FERNANDO", "SAN JOSE", "SIPOCOT", "SIRUMA", "TIGAON", "TINAMBAC", "BAGAMANOC", "BARAS", "BATO", "CARAMORAN", "GIGMOTO", "PANDAN", "PANGANIBAN (PAYO)", "SAN ANDRES (CALOLBON)", "SAN MIGUEL", "VIGA", "VIRAC (Capital)", "AROROY", "BALENO", "BALUD", "BATUAN", "CATAINGAN", "CAWAYAN", "CLAVERIA", "DIMASALANG", "ESPERANZA", "MANDAON", "CITY OF MASBATE (Capital)", "MILAGROS", "MOBO", "MONREAL", "PALANAS", "PIO V. CORPUZ (LIMBUHAN)", "PLACER", "SAN FERNANDO", "SAN JACINTO", "SAN PASCUAL", "USON", "BARCELONA", "BULAN", "BULUSAN", "CASIGURAN", "CASTILLA", "DONSOL", "GUBAT", "IROSIN", "JUBAN", "MAGALLANES", "MATNOG", "PILAR", "PRIETO DIAZ", "SANTA MAGDALENA", "CITY OF SORSOGON (Capital)", "ALTAVAS", "BALETE", "BANGA", "BATAN", "BURUANGA", "IBAJAY", "KALIBO (Capital)", "LEZO", "LIBACAO", "MADALAG", "MAKATO", "MALAY", "MALINAO", "NABAS", "NEW WASHINGTON", "NUMANCIA", "TANGALAN", "ANINI-Y", "BARBAZA", "BELISON", "BUGASONG", "CALUYA", "CULASI", "TOBIAS FORNIER (DAO)", "HAMTIC", "LAUA-AN", "LIBERTAD", "PANDAN", "PATNONGON", "SAN JOSE (Capital)", "SAN REMIGIO", "SEBASTE", "SIBALOM", "TIBIAO", "VALDERRAMA", "CUARTERO", "DAO", "DUMALAG", "DUMARAO", "IVISAN", "JAMINDAN", "MA-AYON", "MAMBUSAO", "PANAY", "PANITAN", "PILAR", "PONTEVEDRA", "PRESIDENT ROXAS", "ROXAS CITY (Capital)", "SAPI-AN", "SIGMA", "TAPAZ", "AJUY", "ALIMODIAN", "ANILAO", "BADIANGAN", "BALASAN", "BANATE", "BAROTAC NUEVO", "BAROTAC VIEJO", "BATAD", "BINGAWAN", "CABATUAN", "CALINOG", "CARLES", "CONCEPCION", "DINGLE", "DUEÃ‘AS", "DUMANGAS", "ESTANCIA", "GUIMBAL", "IGBARAS", "ILOILO CITY (Capital)", "JANIUAY", "LAMBUNAO", "LEGANES", "LEMERY", "LEON", "MAASIN", "MIAGAO", "MINA", "NEW LUCENA", "OTON", "CITY OF PASSI", "PAVIA", "POTOTAN", "SAN DIONISIO", "SAN ENRIQUE", "SAN JOAQUIN", "SAN MIGUEL", "SAN RAFAEL", "SANTA BARBARA", "SARA", "TIGBAUAN", "TUBUNGAN", "ZARRAGA", "BACOLOD CITY (Capital)", "BAGO CITY", "BINALBAGAN", "CADIZ CITY", "CALATRAVA", "CANDONI", "CAUAYAN", "ENRIQUE B. MAGALONA (SARAVIA)", "CITY OF ESCALANTE", "CITY OF HIMAMAYLAN", "HINIGARAN", "HINOBA-AN (ASIA)", "ILOG", "ISABELA", "CITY OF KABANKALAN", "LA CARLOTA CITY", "LA CASTELLANA", "MANAPLA", "MOISES PADILLA (MAGALLON)", "MURCIA", "PONTEVEDRA", "PULUPANDAN", "SAGAY CITY", "SAN CARLOS CITY", "SAN ENRIQUE", "SILAY CITY", "CITY OF SIPALAY", "CITY OF TALISAY", "TOBOSO", "VALLADOLID", "CITY OF VICTORIAS", "SALVADOR BENEDICTO", "BUENAVISTA", "JORDAN (Capital)", "NUEVA VALENCIA", "SAN LORENZO", "SIBUNAG", "ALBURQUERQUE", "ALICIA", "ANDA", "ANTEQUERA", "BACLAYON", "BALILIHAN", "BATUAN", "BILAR", "BUENAVISTA", "CALAPE", "CANDIJAY", "CARMEN", "CATIGBIAN", "CLARIN", "CORELLA", "CORTES", "DAGOHOY", "DANAO", "DAUIS", "DIMIAO", "DUERO", "GARCIA HERNANDEZ", "GUINDULMAN", "INABANGA", "JAGNA", "JETAFE", "LILA", "LOAY", "LOBOC", "LOON", "MABINI", "MARIBOJOC", "PANGLAO", "PILAR", "PRES. CARLOS P. GARCIA (PITOGO)", "SAGBAYAN (BORJA)", "SAN ISIDRO", "SAN MIGUEL", "SEVILLA", "SIERRA BULLONES", "SIKATUNA", "TAGBILARAN CITY (Capital)", "TALIBON", "TRINIDAD", "TUBIGON", "UBAY", "VALENCIA", "BIEN UNIDO", "ALCANTARA", "ALCOY", "ALEGRIA", "ALOGUINSAN", "ARGAO", "ASTURIAS", "BADIAN", "BALAMBAN", "BANTAYAN", "BARILI", "CITY OF BOGO", "BOLJOON", "BORBON", "CITY OF CARCAR", "CARMEN", "CATMON", "CEBU CITY (Capital)", "COMPOSTELA", "CONSOLACION", "CORDOVA", "DAANBANTAYAN", "DALAGUETE", "DANAO CITY", "DUMANJUG", "GINATILAN", "LAPU-LAPU CITY (OPON)", "LILOAN", "MADRIDEJOS", "MALABUYOC", "MANDAUE CITY", "MEDELLIN", "MINGLANILLA", "MOALBOAL", "CITY OF NAGA", "OSLOB", "PILAR", "PINAMUNGAHAN", "PORO", "RONDA", "SAMBOAN", "SAN FERNANDO", "SAN FRANCISCO", "SAN REMIGIO", "SANTA FE", "SANTANDER", "SIBONGA", "SOGOD", "TABOGON", "TABUELAN", "CITY OF TALISAY", "TOLEDO CITY", "TUBURAN", "TUDELA", "AMLAN (AYUQUITAN)", "AYUNGON", "BACONG", "BAIS CITY", "BASAY", "CITY OF BAYAWAN (TULONG)", "BINDOY (PAYABON)", "CANLAON CITY", "DAUIN", "DUMAGUETE CITY (Capital)", "CITY OF GUIHULNGAN", "JIMALALUD", "LA LIBERTAD", "MABINAY", "MANJUYOD", "PAMPLONA", "SAN JOSE", "SANTA CATALINA", "SIATON", "SIBULAN", "CITY OF TANJAY", "TAYASAN", "VALENCIA (LUZURRIAGA)", "VALLEHERMOSO", "ZAMBOANGUITA", "ENRIQUE VILLANUEVA", "LARENA", "LAZI", "MARIA", "SAN JUAN", "SIQUIJOR (Capital)", "ARTECHE", "BALANGIGA", "BALANGKAYAN", "CITY OF BORONGAN (Capital)", "CAN-AVID", "DOLORES", "GENERAL MACARTHUR", "GIPORLOS", "GUIUAN", "HERNANI", "JIPAPAD", "LAWAAN", "LLORENTE", "MASLOG", "MAYDOLONG", "MERCEDES", "ORAS", "QUINAPONDAN", "SALCEDO", "SAN JULIAN", "SAN POLICARPO", "SULAT", "TAFT", "ABUYOG", "ALANGALANG", "ALBUERA", "BABATNGON", "BARUGO", "BATO", "CITY OF BAYBAY", "BURAUEN", "CALUBIAN", "CAPOOCAN", "CARIGARA", "DAGAMI", "DULAG", "HILONGOS", "HINDANG", "INOPACAN", "ISABEL", "JARO", "JAVIER (BUGHO)", "JULITA", "KANANGA", "LA PAZ", "LEYTE", "MACARTHUR", "MAHAPLAG", "MATAG-OB", "MATALOM", "MAYORGA", "MERIDA", "ORMOC CITY", "PALO", "PALOMPON", "PASTRANA", "SAN ISIDRO", "SAN MIGUEL", "SANTA FE", "TABANGO", "TABONTABON", "TACLOBAN CITY (Capital)", "TANAUAN", "TOLOSA", "TUNGA", "VILLABA", "ALLEN", "BIRI", "BOBON", "CAPUL", "CATARMAN (Capital)", "CATUBIG", "GAMAY", "LAOANG", "LAPINIG", "LAS NAVAS", "LAVEZARES", "MAPANAS", "MONDRAGON", "PALAPAG", "PAMBUJAN", "ROSARIO", "SAN ANTONIO", "SAN ISIDRO", "SAN JOSE", "SAN ROQUE", "SAN VICENTE", "SILVINO LOBOS", "VICTORIA", "LOPE DE VEGA", "ALMAGRO", "BASEY", "CALBAYOG CITY", "CALBIGA", "CITY OF CATBALOGAN (Capital)", "DARAM", "GANDARA", "HINABANGAN", "JIABONG", "MARABUT", "MATUGUINAO", "MOTIONG", "PINABACDAO", "SAN JOSE DE BUAN", "SAN SEBASTIAN", "SANTA MARGARITA", "SANTA RITA", "SANTO NIÃ‘O", "TALALORA", "TARANGNAN", "VILLAREAL", "PARANAS (WRIGHT)", "ZUMARRAGA", "TAGAPUL-AN", "SAN JORGE", "PAGSANGHAN", "ANAHAWAN", "BONTOC", "HINUNANGAN", "HINUNDAYAN", "LIBAGON", "LILOAN", "CITY OF MAASIN (Capital)", "MACROHON", "MALITBOG", "PADRE BURGOS", "PINTUYAN", "SAINT BERNARD", "SAN FRANCISCO", "SAN JUAN (CABALIAN)", "SAN RICARDO", "SILAGO", "SOGOD", "TOMAS OPPUS", "LIMASAWA", "ALMERIA", "BILIRAN", "CABUCGAYAN", "CAIBIRAN", "CULABA", "KAWAYAN", "MARIPIPI", "NAVAL (Capital)", "DAPITAN CITY", "DIPOLOG CITY (Capital)", "KATIPUNAN", "LA LIBERTAD", "LABASON", "LILOY", "MANUKAN", "MUTIA", "PIÃ‘AN (NEW PIÃ‘AN)", "POLANCO", "PRES. MANUEL A. ROXAS", "RIZAL", "SALUG", "SERGIO OSMEÃ‘A SR.", "SIAYAN", "SIBUCO", "SIBUTAD", "SINDANGAN", "SIOCON", "SIRAWAI", "TAMPILISAN", "JOSE DALMAN (PONOT)", "GUTALAC", "BALIGUIAN", "GODOD", "BACUNGAN (Leon T. Postigo)", "KALAWIT", "AURORA", "BAYOG", "DIMATALING", "DINAS", "DUMALINAO", "DUMINGAG", "KUMALARANG", "LABANGAN", "LAPUYAN", "MAHAYAG", "MARGOSATUBIG", "MIDSALIP", "MOLAVE", "PAGADIAN CITY (Capital)", "RAMON MAGSAYSAY (LIARGO)", "SAN MIGUEL", "SAN PABLO", "TABINA", "TAMBULIG", "TUKURAN", "ZAMBOANGA CITY", "LAKEWOOD", "JOSEFINA", "PITOGO", "SOMINOT (DON MARIANO MARCOS)", "VINCENZO A. SAGUN", "GUIPOS", "TIGBAO", "ALICIA", "BUUG", "DIPLAHAN", "IMELDA", "IPIL (Capital)", "KABASALAN", "MABUHAY", "MALANGAS", "NAGA", "OLUTANGA", "PAYAO", "ROSELLER LIM", "SIAY", "TALUSAN", "TITAY", "TUNGAWAN", "CITY OF ISABELA", "BAUNGON", "DAMULOG", "DANGCAGAN", "DON CARLOS", "IMPASUG-ONG", "KADINGILAN", "KALILANGAN", "KIBAWE", "KITAOTAO", "LANTAPAN", "LIBONA", "CITY OF MALAYBALAY (Capital)", "MALITBOG", "MANOLO FORTICH", "MARAMAG", "PANGANTUCAN", "QUEZON", "SAN FERNANDO", "SUMILAO", "TALAKAG", "CITY OF VALENCIA", "CABANGLASAN", "CATARMAN", "GUINSILIBAN", "MAHINOG", "MAMBAJAO (Capital)", "SAGAY", "BACOLOD", "BALOI", "BAROY", "ILIGAN CITY", "KAPATAGAN", "SULTAN NAGA DIMAPORO (KAROMATAN)", "KAUSWAGAN", "KOLAMBUGAN", "LALA", "LINAMON", "MAGSAYSAY", "MAIGO", "MATUNGAO", "MUNAI", "NUNUNGAN", "PANTAO RAGAT", "POONA PIAGAPO", "SALVADOR", "SAPAD", "TAGOLOAN", "TANGCAL", "TUBOD (Capital)", "PANTAR", "ALORAN", "BALIANGAO", "BONIFACIO", "CALAMBA", "CLARIN", "CONCEPCION", "JIMENEZ", "LOPEZ JAENA", "OROQUIETA CITY (Capital)", "OZAMIS CITY", "PANAON", "PLARIDEL", "SAPANG DALAGA", "SINACABAN", "TANGUB CITY", "TUDELA", "DON VICTORIANO CHIONGBIAN  (DON MARIANO MARCOS)", "ALUBIJID", "BALINGASAG", "BALINGOAN", "BINUANGAN", "CAGAYAN DE ORO CITY (Capital)", "CLAVERIA", "CITY OF EL SALVADOR", "GINGOOG CITY", "GITAGUM", "INITAO", "JASAAN", "KINOGUITAN", "LAGONGLONG", "LAGUINDINGAN", "LIBERTAD", "LUGAIT", "MAGSAYSAY (LINUGOS)", "MANTICAO", "MEDINA", "NAAWAN", "OPOL", "SALAY", "SUGBONGCOGON", "TAGOLOAN", "TALISAYAN", "VILLANUEVA", "ASUNCION (SAUG)", "CARMEN", "KAPALONG", "NEW CORELLA", "CITY OF PANABO", "ISLAND GARDEN CITY OF SAMAL", "SANTO TOMAS", "CITY OF TAGUM (Capital)", "TALAINGOD", "BRAULIO E. DUJALI", "SAN ISIDRO", "BANSALAN", "DAVAO CITY", "CITY OF DIGOS (Capital)", "HAGONOY", "KIBLAWAN", "MAGSAYSAY", "MALALAG", "MATANAO", "PADADA", "SANTA CRUZ", "SULOP", "BAGANGA", "BANAYBANAY", "BOSTON", "CARAGA", "CATEEL", "GOVERNOR GENEROSO", "LUPON", "MANAY", "CITY OF MATI (Capital)", "SAN ISIDRO", "TARRAGONA", "COMPOSTELA", "LAAK (SAN VICENTE)", "MABINI (DOÃ‘A ALICIA)", "MACO", "MARAGUSAN (SAN MARIANO)", "MAWAB", "MONKAYO", "MONTEVISTA", "NABUNTURAN (Capital)", "NEW BATAAN", "PANTUKAN", "DON MARCELINO", "JOSE ABAD SANTOS (TRINIDAD)", "MALITA", "SANTA MARIA", "SARANGANI", "ALAMADA", "CARMEN", "KABACAN", "CITY OF KIDAPAWAN (Capital)", "LIBUNGAN", "MAGPET", "MAKILALA", "MATALAM", "MIDSAYAP", "M'LANG", "PIGKAWAYAN", "PIKIT", "PRESIDENT ROXAS", "TULUNAN", "ANTIPAS", "BANISILAN", "ALEOSAN", "ARAKAN", "BANGA", "GENERAL SANTOS CITY (DADIANGAS)", "CITY OF KORONADAL (Capital)", "NORALA", "POLOMOLOK", "SURALLAH", "TAMPAKAN", "TANTANGAN", "T'BOLI", "TUPI", "SANTO NIÃ‘O", "LAKE SEBU", "BAGUMBAYAN", "COLUMBIO", "ESPERANZA", "ISULAN (Capital)", "KALAMANSIG", "LEBAK", "LUTAYAN", "LAMBAYONG (MARIANO MARCOS)", "PALIMBANG", "PRESIDENT QUIRINO", "CITY OF TACURONG", "SEN. NINOY AQUINO", "ALABEL (Capital)", "GLAN", "KIAMBA", "MAASIM", "MAITUM", "MALAPATAN", "MALUNGON", "COTABATO CITY", "TONDO I / II", "BINONDO", "QUIAPO", "SAN NICOLAS", "SANTA CRUZ", "SAMPALOC", "SAN MIGUEL", "ERMITA", "INTRAMUROS", "MALATE", "PACO", "PANDACAN", "PORT AREA", "SANTA ANA", "CITY OF MANDALUYONG", "CITY OF MARIKINA", "CITY OF PASIG", "QUEZON CITY", "CITY OF SAN JUAN", "CALOOCAN CITY", "CITY OF MALABON", "CITY OF NAVOTAS", "CITY OF VALENZUELA", "CITY OF LAS PIÃ‘AS", "CITY OF MAKATI", "CITY OF MUNTINLUPA", "CITY OF PARAÃ‘AQUE", "PASAY CITY", "PATEROS", "TAGUIG CITY", "BANGUED (Capital)", "BOLINEY", "BUCAY", "BUCLOC", "DAGUIOMAN", "DANGLAS", "DOLORES", "LA PAZ", "LACUB", "LAGANGILANG", "LAGAYAN", "LANGIDEN", "LICUAN-BAAY (LICUAN)", "LUBA", "MALIBCONG", "MANABO", "PEÃ‘ARRUBIA", "PIDIGAN", "PILAR", "SALLAPADAN", "SAN ISIDRO", "SAN JUAN", "SAN QUINTIN", "TAYUM", "TINEG", "TUBO", "VILLAVICIOSA", "ATOK", "BAGUIO CITY", "BAKUN", "BOKOD", "BUGUIAS", "ITOGON", "KABAYAN", "KAPANGAN", "KIBUNGAN", "LA TRINIDAD (Capital)", "MANKAYAN", "SABLAN", "TUBA", "TUBLAY", "BANAUE", "HUNGDUAN", "KIANGAN", "LAGAWE (Capital)", "LAMUT", "MAYOYAO", "ALFONSO LISTA (POTIA)", "AGUINALDO", "HINGYON", "TINOC", "ASIPULO", "BALBALAN", "LUBUAGAN", "PASIL", "PINUKPUK", "RIZAL (LIWAN)", "CITY OF TABUK (Capital)", "TANUDAN", "TINGLAYAN", "BARLIG", "BAUKO", "BESAO", "BONTOC (Capital)", "NATONIN", "PARACELIS", "SABANGAN", "SADANGA", "SAGADA", "TADIAN", "CALANASAN (BAYAG)", "CONNER", "FLORA", "KABUGAO (Capital)", "LUNA", "PUDTOL", "SANTA MARCELA", "CITY OF LAMITAN", "LANTAWAN", "MALUSO", "SUMISIP", "TIPO-TIPO", "TUBURAN", "AKBAR", "AL-BARKA", "HADJI MOHAMMAD AJUL", "UNGKAYA PUKAN", "HADJI MUHTAMAD", "TABUAN-LASA", "BACOLOD-KALAWI (BACOLOD GRANDE)", "BALABAGAN", "BALINDONG (WATU)", "BAYANG", "BINIDAYAN", "BUBONG", "BUTIG", "GANASSI", "KAPAI", "LUMBA-BAYABAO (MAGUING)", "LUMBATAN", "MADALUM", "MADAMBA", "MALABANG", "MARANTAO", "MARAWI CITY (Capital)", "MASIU", "MULONDO", "PAGAYAWAN (TATARIKAN)", "PIAGAPO", "POONA BAYABAO (GATA)", "PUALAS", "DITSAAN-RAMAIN", "SAGUIARAN", "TAMPARAN", "TARAKA", "TUBARAN", "TUGAYA", "WAO", "MAROGONG", "CALANOGAS", "BUADIPOSO-BUNTONG", "MAGUING", "PICONG (SULTAN GUMANDER)", "LUMBAYANAGUE", "BUMBARAN", "TAGOLOAN II", "KAPATAGAN", "SULTAN DUMALONDONG", "LUMBACA-UNAYAN", "AMPATUAN", "BULDON", "BULUAN", "DATU PAGLAS", "DATU PIANG", "DATU ODIN SINSUAT (DINAIG)", "SHARIFF AGUAK (MAGANOY) (Capital)", "MATANOG", "PAGALUNGAN", "PARANG", "SULTAN KUDARAT (NULING)", "SULTAN SA BARONGIS (LAMBAYONG)", "KABUNTALAN (TUMBAO)", "UPI", "TALAYAN", "SOUTH UPI", "BARIRA", "GEN. S. K. PENDATUN", "MAMASAPANO", "TALITAY", "PAGAGAWAN", "PAGLAT", "SULTAN MASTURA", "GUINDULUNGAN", "DATU SAUDI-AMPATUAN", "DATU UNSAY", "DATU ABDULLAH SANGKI", "RAJAH BUAYAN", "DATU BLAH T. SINSUAT", "DATU ANGGAL MIDTIMBANG", "MANGUDADATU", "PANDAG", "NORTHERN KABUNTALAN", "DATU HOFFER AMPATUAN", "DATU SALIBO", "SHARIFF SAYDONA MUSTAPHA", "INDANAN", "JOLO (Capital)", "KALINGALAN CALUANG", "LUUK", "MAIMBUNG", "HADJI PANGLIMA TAHIL (MARUNGGAS)", "OLD PANAMAO", "PANGUTARAN", "PARANG", "PATA", "PATIKUL", "SIASI", "TALIPAO", "TAPUL", "TONGKIL", "PANGLIMA ESTINO (NEW PANAMAO)", "LUGUS", "PANDAMI", "OMAR", "PANGLIMA SUGALA (BALIMBING)", "BONGAO (Capital)", "MAPUN (CAGAYAN DE TAWI-TAWI)", "SIMUNUL", "SITANGKAI", "SOUTH UBIAN", "TANDUBAS", "TURTLE ISLANDS", "LANGUYAN", "SAPA-SAPA", "SIBUTU", "BUENAVISTA", "BUTUAN CITY (Capital)", "CITY OF CABADBARAN", "CARMEN", "JABONGA", "KITCHARAO", "LAS NIEVES", "MAGALLANES", "NASIPIT", "SANTIAGO", "TUBAY", "REMEDIOS T. ROMUALDEZ", "CITY OF BAYUGAN", "BUNAWAN", "ESPERANZA", "LA PAZ", "LORETO", "PROSPERIDAD (Capital)", "ROSARIO", "SAN FRANCISCO", "SAN LUIS", "SANTA JOSEFA", "TALACOGON", "TRENTO", "VERUELA", "SIBAGAT", "ALEGRIA", "BACUAG", "BURGOS", "CLAVER", "DAPA", "DEL CARMEN", "GENERAL LUNA", "GIGAQUIT", "MAINIT", "MALIMONO", "PILAR", "PLACER", "SAN BENITO", "SAN FRANCISCO (ANAO-AON)", "SAN ISIDRO", "SANTA MONICA (SAPAO)", "SISON", "SOCORRO", "SURIGAO CITY (Capital)", "TAGANA-AN", "TUBOD", "BAROBO", "BAYABAS", "CITY OF BISLIG", "CAGWAIT", "CANTILAN", "CARMEN", "CARRASCAL", "CORTES", "HINATUAN", "LANUZA", "LIANGA", "LINGIG", "MADRID", "MARIHATAG", "SAN AGUSTIN", "SAN MIGUEL", "TAGBINA", "TAGO", "CITY OF TANDAG (Capital)", "BASILISA (RIZAL)", "CAGDIANAO", "DINAGAT", "LIBJO (ALBOR)", "LORETO", "SAN JOSE (Capital)", "TUBAJON",
        };

        String[] barangay = new String[]{
                "IBA O' ESTE", "CANIOGAN", "BULIHAN", "DAKILA", "STA. ISABEL", "GUINHAWA", "STA. LUCIA", "LONGOS", "PUNGO", "IBA ESTE", "BALUNGAO",
        };
        String[] province = new String[]{
                "ILOCOS NORTE", "ILOCOS SUR", "LA UNION", "PANGASINAN", "BATANES", "CAGAYAN", "ISABELA", "NUEVA VIZCAYA", "QUIRINO", "BATAAN", "BULACAN", "NUEVA ECIJA", "PAMPANGA", "TARLAC", "ZAMBALES", "AURORA", "BATANGAS", "CAVITE", "LAGUNA", "QUEZON", "RIZAL", "MARINDUQUE", "OCCIDENTAL MINDORO", "ORIENTAL MINDORO", "PALAWAN", "ROMBLON", "ALBAY", "CAMARINES NORTE", "CAMARINES SUR", "CATANDUANES", "MASBATE", "SORSOGON", "AKLAN", "ANTIQUE", "CAPIZ", "ILOILO", "NEGROS OCCIDENTAL", "GUIMARAS", "BOHOL", "CEBU", "NEGROS ORIENTAL", "SIQUIJOR", "EASTERN SAMAR", "LEYTE", "NORTHERN SAMAR", "SAMAR (WESTERN SAMAR)", "SOUTHERN LEYTE", "BILIRAN", "ZAMBOANGA DEL NORTE", "ZAMBOANGA DEL SUR", "ZAMBOANGA SIBUGAY", "CITY OF ISABELA", "BUKIDNON", "CAMIGUIN", "LANAO DEL NORTE", "MISAMIS OCCIDENTAL", "MISAMIS ORIENTAL", "DAVAO DEL NORTE", "DAVAO DEL SUR", "DAVAO ORIENTAL", "COMPOSTELA VALLEY", "DAVAO OCCIDENTAL", "COTABATO (NORTH COTABATO)", "SOUTH COTABATO", "SULTAN KUDARAT", "SARANGANI", "COTABATO CITY", "NCR, CITY OF MANILA, FIRST DISTRICT", "CITY OF MANILA", "NCR, SECOND DISTRICT", "NCR, THIRD DISTRICT", "NCR, FOURTH DISTRICT", "ABRA", "BENGUET", "IFUGAO", "KALINGA", "MOUNTAIN PROVINCE", "APAYAO", "BASILAN", "LANAO DEL SUR", "MAGUINDANAO", "SULU", "TAWI-TAWI", "AGUSAN DEL NORTE", "AGUSAN DEL SUR", "SURIGAO DEL NORTE", "SURIGAO DEL SUR", "DINAGAT ISLANDS",
        };


        profileLabel = (ImageView) findViewById(R.id.profilelabel);
        Button profileBtn = (Button) findViewById(R.id.profileBtn);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, picCode);
            }
        });

        ImageView backBtn = (ImageView) findViewById(R.id.registerBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainAct = new Intent(con, MainActivity.class);
                startActivity(toMainAct);
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, municipality);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, province);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, barangay);

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.municipality);
        AutoCompleteTextView autoCompleteTextView2 = (AutoCompleteTextView) findViewById(R.id.province);
        AutoCompleteTextView autoCompleteTextView3 = (AutoCompleteTextView) findViewById(R.id.barangay);

        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView2.setAdapter(adapter2);
        autoCompleteTextView3.setAdapter(adapter3);

        LinearLayout background, background_accent, login_ctr;
        background = findViewById(R.id.background);
        TextView welcome = findViewById(R.id.welcome);
        ImageView logo = findViewById(R.id.logo);

        Animation slide_left, slide_top, slide_bottom, fade, random;
        slide_left = AnimationUtils.loadAnimation(this, R.anim.anim_slide_left);
        fade = AnimationUtils.loadAnimation(this, R.anim.fade);
        slide_bottom = AnimationUtils.loadAnimation(this, R.anim.anim_slide_bottom);
        random = AnimationUtils.loadAnimation(this, R.anim.random);

        //SET ANIMATIONS
        welcome.setAnimation(fade);
        logo.setAnimation(slide_bottom);
        background.setAnimation(random);

        border.setColor(0xFFFFFFFF); //white background
        border.setStroke(2, 0xFFFF0000);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        EditText confirmpassword = findViewById(R.id.confirmpassword);

        confirmpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    if (!password.getText().toString().equals(confirmpassword.getText().toString())) {
                        confirmpassword.setBackgroundDrawable(border);
                    } else {
                        confirmpassword.setBackgroundResource(R.drawable.edittext_stroke);
                        confirmpassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.person, 0, R.drawable.check, 0);
                    }
                }
            }
        });

        q1 = findViewById(R.id.q1);
        q2 = findViewById(R.id.q2);
        q3 = findViewById(R.id.q3);

        q1.setSelection(0,false);
        q2.setSelection(1,false);
        q3.setSelection(2,false);

        int q1_sel = q1.getSelectedItemPosition();
        int q2_sel = q2.getSelectedItemPosition();
        int q3_sel = q3.getSelectedItemPosition();

        int finalError_ctr = error_ctr;


        q1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int position, long id) {
                int q1_sel = q1.getSelectedItemPosition();
                int q2_sel = q2.getSelectedItemPosition();
                int q3_sel = q3.getSelectedItemPosition();
                validate_security(q1_sel, q2_sel, q3_sel, finalError_ctr);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
        q2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int position, long id) {
                int q1_sel = q1.getSelectedItemPosition();
                int q2_sel = q2.getSelectedItemPosition();
                int q3_sel = q3.getSelectedItemPosition();
                validate_security(q1_sel, q2_sel, q3_sel, finalError_ctr);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
        q3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int position, long id) {
                int q1_sel = q1.getSelectedItemPosition();
                int q2_sel = q2.getSelectedItemPosition();
                int q3_sel = q3.getSelectedItemPosition();
                validate_security(q1_sel, q2_sel, q3_sel, finalError_ctr);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void dateDialog(View view) {
        int day = 1, month = 0, year = 1990;
        Button datebtn = findViewById(R.id.button);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        datebtn.setText(dayOfMonth + "/" + month + "/" + year);
                        dateset = true;
                    }
                }, year, month, day);

        datePickerDialog.getWindow().setBackgroundDrawableResource(R.drawable.rounded_corners_plain);
        datePickerDialog.show();

    }

    //YUNG SA CONFIRM PASSWORD VALIDATION, DINAGDAGAN KO NA RIN PO SIYA NG REAL-TIME VALIDATION SIR. GUSTO KO LANG PONG ITRY KUNG KAYA PO DITO HEHE. SALAMAT PO.
    public void validate(View view) {
        EditText username, password, confirmpassword, firstname, middlename, lastname, street = null, houseno, phoneno, q1a, q2a, q3a;
        AutoCompleteTextView municipality, province, barangay;
        Button datebtn;
        RelativeLayout hobbiescont;
        RadioGroup gender;
        error_ctr = 0;

        CheckBox ch1 = findViewById(R.id.ch1), ch2 = findViewById(R.id.ch2), ch3 = findViewById(R.id.ch3), ch4 = findViewById(R.id.ch4), ch5 = findViewById(R.id.ch5),
                ch6 = findViewById(R.id.ch6), ch7 = findViewById(R.id.ch7), ch8 = findViewById(R.id.ch8), ch9 = findViewById(R.id.ch9), ch10 = findViewById(R.id.ch10);

        q1a = findViewById(R.id.q1a);
        q2a = findViewById(R.id.q2a);
        q3a = findViewById(R.id.q3a);
        datebtn = findViewById(R.id.button);

        hobbiescont = findViewById(R.id.hobbiescont);
        municipality = findViewById(R.id.municipality);
        province = findViewById(R.id.province);
        barangay = findViewById(R.id.barangay);
        street = findViewById(R.id.street);
        houseno = findViewById(R.id.housenumber);

        gender = findViewById(R.id.gender);
        phoneno = findViewById(R.id.number);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        firstname = findViewById(R.id.firstname);
        middlename = findViewById(R.id.middlename);
        lastname = findViewById(R.id.lastname);

        String error_list = "";
        //BLANK USERNAME
        if (username.getText().toString().isEmpty()) {
            error_ctr++;
            error_list += "- Blank username\n";
            username.setBackgroundDrawable(border);
        } else
            username.setBackgroundResource(R.drawable.edittext_stroke);

        //BLANK PASSWORD
        if (password.getText().toString().isEmpty()) {
            error_ctr++;
            password.setBackgroundDrawable(border);
            error_list += "- Blank password\n";
        } else
            password.setBackgroundResource(R.drawable.edittext_stroke);

        //BLANK CONFIRM PASSWORD
        if (confirmpassword.getText().toString().isEmpty()) {
            error_ctr++;
            error_list += "- Blank confirm password\n";
            confirmpassword.setBackgroundDrawable(border);
        } else
            confirmpassword.setBackgroundResource(R.drawable.edittext_stroke);

        //PASSWORD AND CONFIRMPASSWORD MISMATCH
        if (!password.getText().toString().isEmpty() && !confirmpassword.getText().toString().isEmpty()) {
            if (!password.getText().toString().equals(confirmpassword.getText().toString())) {
                error_ctr++;
                error_list += "- Password and confirm password not matching\n";
                confirmpassword.setBackgroundDrawable(border);
            } else
                confirmpassword.setBackgroundResource(R.drawable.edittext_stroke);
        }

        //BLANK FIRSTNAME
        if (firstname.getText().toString().isEmpty()) {
            error_ctr++;
            error_list += "- Blank first name\n";
            firstname.setBackgroundDrawable(border);
        } else
            firstname.setBackgroundResource(R.drawable.edittext_stroke);

        //BLANK LASTNAME
        if (lastname.getText().toString().isEmpty()) {
            error_ctr++;
            error_list += "- Blank last name\n";
            lastname.setBackgroundDrawable(border);
        } else
            lastname.setBackgroundResource(R.drawable.edittext_stroke);

        //NO RADIOBUTTON SELECTED IN THE GROUP
        if (gender.getCheckedRadioButtonId() == -1) {
            error_list += "- Gender not selected\n";
            error_ctr++;
            gender.setBackgroundDrawable(border);
        } else
            gender.setBackgroundResource(R.drawable.edittext_stroke);

        //DATE OF BIRTH NOT SET
        if (!dateset) {
            error_ctr++;
            error_list += "- Date of birth not set\n";
            datebtn.setBackgroundDrawable(border);
        } else
            datebtn.setBackgroundResource(R.drawable.edittext_stroke);

        if (street.getText().toString().isEmpty()) {
            error_ctr++;
            error_list += "- Blank street address\n";
            street.setBackgroundDrawable(border);
        } else
            street.setBackgroundResource(R.drawable.edittext_stroke);

        if (houseno.getText().toString().isEmpty()) {
            error_list += "- Blank house number\n";
            error_ctr++;
            houseno.setBackgroundDrawable(border);
        } else
            houseno.setBackgroundResource(R.drawable.edittext_stroke);

        if (municipality.getText().toString().isEmpty()) {
            error_list += "- Blank municipality\n";
            error_ctr++;
            municipality.setBackgroundDrawable(border);
        } else
            municipality.setBackgroundResource(R.drawable.edittext_stroke);

        if (province.getText().toString().isEmpty()) {
            error_list += "- Blank province\n";
            error_ctr++;
            province.setBackgroundDrawable(border);
        } else
            province.setBackgroundResource(R.drawable.edittext_stroke);

        if (barangay.getText().toString().isEmpty()) {
            error_list += "- Blank barangay\n";
            error_ctr++;
            barangay.setBackgroundDrawable(border);
        } else
            barangay.setBackgroundResource(R.drawable.edittext_stroke);

        if (phoneno.getText().toString().isEmpty()) {
            error_list += "- Blank phone number\n";
            error_ctr++;
            phoneno.setBackgroundDrawable(border);
        } else
            phoneno.setBackgroundResource(R.drawable.edittext_stroke);

        if (ch1.isChecked() || ch2.isChecked() || ch3.isChecked() || ch4.isChecked() || ch5.isChecked() || ch6.isChecked() || ch7.isChecked() || ch8.isChecked() || ch9.isChecked() || ch10.isChecked()) {
            hobbiescont.setBackgroundResource(0);
        } else {
            error_ctr++;
            error_list += "- No selected hobbies\n";
            hobbiescont.setBackgroundDrawable(border);
        }

        if (q1a.getText().toString().isEmpty()) {
            error_list += "- Blank security question 1 answer\n";
            error_ctr++;
            q1a.setBackgroundDrawable(border);
        } else
            q1a.setBackgroundResource(R.drawable.edittext_stroke);


        if (q2a.getText().toString().isEmpty()) {
            error_list += "- Blank security question 2 answer\n";
            error_ctr++;
            q2a.setBackgroundDrawable(border);
        } else
            q2a.setBackgroundResource(R.drawable.edittext_stroke);


        if (q3a.getText().toString().isEmpty()) {
            error_list += "- Blank security question 3 answer\n";
            error_ctr++;
            q3a.setBackgroundDrawable(border);
        } else
            q3a.setBackgroundResource(R.drawable.edittext_stroke);


        int q1_sel = q1.getSelectedItemPosition();
        int q2_sel = q2.getSelectedItemPosition();
        int q3_sel = q3.getSelectedItemPosition();

        if (q1_sel == q2_sel || q2_sel == q3_sel || q1_sel == q3_sel) {
            error_ctr++;
            String dial = validate_security(q1_sel, q2_sel, q3_sel, error_ctr);
            error_list += "- " + dial + "\n";

        }

        if (error_ctr > 0) {


        } else {

            int selectedId = gender.getCheckedRadioButtonId();
            RadioButton  wao = (RadioButton) findViewById(selectedId);

            String hobbies_selected="";

            if(ch1.isChecked())
                hobbies_selected+=ch1.getText().toString() + ", ";
            if(ch2.isChecked())
                hobbies_selected+=ch2.getText().toString() + ", ";
            if(ch3.isChecked())
                hobbies_selected+=ch3.getText().toString() + ", ";
            if(ch4.isChecked())
                hobbies_selected+=ch4.getText().toString() + ", ";
            if(ch5.isChecked())
                hobbies_selected+=ch5.getText().toString() + ", ";
            if(ch6.isChecked())
                hobbies_selected+=ch6.getText().toString() + ", ";
            if(ch7.isChecked())
                hobbies_selected+=ch7.getText().toString() + ", ";
            if(ch8.isChecked())
                hobbies_selected+=ch8.getText().toString() + ", ";
            if(ch9.isChecked())
                hobbies_selected+=ch9.getText().toString() + ", ";
            if(ch10.isChecked())
                hobbies_selected+=ch10.getText().toString() + ", ";

            //REMOVE LAST COMMA
            hobbies_selected = hobbies_selected.substring(0, hobbies_selected.length()-2);

            String info =
                    "\nUsername: " + username.getText().toString()+"\n\n"+
                    "Password: " + password.getText().toString()+"\n\n"+
                    "Name: " + firstname.getText().toString()+" "+middlename.getText().toString()+" "+lastname.getText().toString()+"\n\n"+
                    "Gender: " + wao.getText().toString()+"\n\n"+
                    "Date of birth: " + datebtn.getText()+"\n\n"+

                    "Address: " + houseno.getText().toString()+" "+
                    street.getText().toString()+" "+
                    barangay.getText().toString()+", "+
                    municipality.getText().toString()+", "+
                    province.getText().toString() +"\n\n"+

                    "Phone Number: " + phoneno.getText().toString()+"\n\n"+
                    "Hobbies: " + hobbies_selected+"\n\n"+
                    "SQ1: " + q1.getSelectedItem().toString()+"\n"+
                    "A: " + q1a.getText().toString()+"\n\n"+
                    "SQ2: " + q2.getSelectedItem().toString()+"\n"+
                    "A: " + q2a.getText().toString()+"\n\n"+
                    "SQ3: " + q3.getSelectedItem().toString()+"\n"+
                    "A: " + q3a.getText().toString()+"\n\n";

            //NEW COMMA DELIMItED ARRAYLIST INSERT DEMo
            ArrayList<String> db_insert = new ArrayList<>();

            db_insert.add(username.getText().toString());
            db_insert.add(password.getText().toString());
            db_insert.add(firstname.getText().toString());
            db_insert.add(middlename.getText().toString());
            db_insert.add(lastname.getText().toString());
            db_insert.add(wao.getText().toString());
            db_insert.add(datebtn.getText().toString());
            db_insert.add(houseno.getText().toString());
            db_insert.add(street.getText().toString());
            db_insert.add(barangay.getText().toString());
            db_insert.add(municipality.getText().toString());
            db_insert.add(province.getText().toString());
            db_insert.add(phoneno.getText().toString());
            db_insert.add(q1.getSelectedItem().toString());
            db_insert.add(q1a.getText().toString());
            db_insert.add(q2.getSelectedItem().toString());
            db_insert.add(q2a.getText().toString());
            db_insert.add(q3.getSelectedItem().toString());
            db_insert.add(q3a.getText().toString());

            MainActivity.db.add(db_insert);

            AlertDialog.Builder alert = new AlertDialog.Builder(this)
                    .setTitle("What We Received")
                    .setMessage(info)
                    .setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Registration.this,"Successful Registration",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(con, MainActivity.class);
                            startActivity(i);
                        }
                    });
            alert.show();
        }
    }

    public String validate_security(int q1_sel, int q2_sel, int q3_sel, int err_cnt) {
        String dial = "";
        if ((q1_sel == q2_sel || q2_sel == q3_sel || q1_sel == q3_sel) && err_cnt < 18) {

            if (q1_sel == q2_sel) dial = "Question 1 is the same as Question 2. Select another question.";
            else if (q2_sel == q3_sel) dial = "Question 2 is the same as Question 3.Select another question.";
            else dial = "Question 1 is the same as Question 3. Select another question.";

            AlertDialog.Builder alert = new AlertDialog.Builder(this)
                    .setTitle("Security Question")
                    .setMessage(dial)
                    .setPositiveButton("Okay", null);
            alert.show();
        }

        return dial;
    }

    public void login_transfer(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this)
                .setTitle("Login")
                .setMessage("Redirecting you to the login screen.")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(con, MainActivity.class);
                        startActivity(i);
                    }
                })
                .setIcon(R.drawable.person);
        alert.show();
    }
    protected void onActivityResult(int reqCode, int resCode, Intent data){
        if(reqCode==picCode){
            Bitmap pic = (Bitmap)data.getExtras().get("data");
            profileLabel.setImageBitmap(pic);
        }
    }
}