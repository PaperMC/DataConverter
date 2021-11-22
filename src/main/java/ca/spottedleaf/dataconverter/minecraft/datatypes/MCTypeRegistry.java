package ca.spottedleaf.dataconverter.minecraft.datatypes;

import ca.spottedleaf.dataconverter.minecraft.versions.V100;
import ca.spottedleaf.dataconverter.minecraft.versions.V101;
import ca.spottedleaf.dataconverter.minecraft.versions.V102;
import ca.spottedleaf.dataconverter.minecraft.versions.V1022;
import ca.spottedleaf.dataconverter.minecraft.versions.V105;
import ca.spottedleaf.dataconverter.minecraft.versions.V106;
import ca.spottedleaf.dataconverter.minecraft.versions.V107;
import ca.spottedleaf.dataconverter.minecraft.versions.V108;
import ca.spottedleaf.dataconverter.minecraft.versions.V109;
import ca.spottedleaf.dataconverter.minecraft.versions.V110;
import ca.spottedleaf.dataconverter.minecraft.versions.V111;
import ca.spottedleaf.dataconverter.minecraft.versions.V1125;
import ca.spottedleaf.dataconverter.minecraft.versions.V113;
import ca.spottedleaf.dataconverter.minecraft.versions.V1344;
import ca.spottedleaf.dataconverter.minecraft.versions.V135;
import ca.spottedleaf.dataconverter.minecraft.versions.V143;
import ca.spottedleaf.dataconverter.minecraft.versions.V1446;
import ca.spottedleaf.dataconverter.minecraft.versions.V1450;
import ca.spottedleaf.dataconverter.minecraft.versions.V1451;
import ca.spottedleaf.dataconverter.minecraft.versions.V1456;
import ca.spottedleaf.dataconverter.minecraft.versions.V1458;
import ca.spottedleaf.dataconverter.minecraft.versions.V1460;
import ca.spottedleaf.dataconverter.minecraft.versions.V1466;
import ca.spottedleaf.dataconverter.minecraft.versions.V147;
import ca.spottedleaf.dataconverter.minecraft.versions.V1470;
import ca.spottedleaf.dataconverter.minecraft.versions.V1474;
import ca.spottedleaf.dataconverter.minecraft.versions.V1475;
import ca.spottedleaf.dataconverter.minecraft.versions.V1480;
import ca.spottedleaf.dataconverter.minecraft.versions.V1483;
import ca.spottedleaf.dataconverter.minecraft.versions.V1484;
import ca.spottedleaf.dataconverter.minecraft.versions.V1486;
import ca.spottedleaf.dataconverter.minecraft.versions.V1487;
import ca.spottedleaf.dataconverter.minecraft.versions.V1488;
import ca.spottedleaf.dataconverter.minecraft.versions.V1490;
import ca.spottedleaf.dataconverter.minecraft.versions.V1492;
import ca.spottedleaf.dataconverter.minecraft.versions.V1494;
import ca.spottedleaf.dataconverter.minecraft.versions.V1496;
import ca.spottedleaf.dataconverter.minecraft.versions.V1500;
import ca.spottedleaf.dataconverter.minecraft.versions.V1501;
import ca.spottedleaf.dataconverter.minecraft.versions.V1502;
import ca.spottedleaf.dataconverter.minecraft.versions.V1506;
import ca.spottedleaf.dataconverter.minecraft.versions.V1510;
import ca.spottedleaf.dataconverter.minecraft.versions.V1514;
import ca.spottedleaf.dataconverter.minecraft.versions.V1515;
import ca.spottedleaf.dataconverter.minecraft.versions.V1624;
import ca.spottedleaf.dataconverter.minecraft.versions.V165;
import ca.spottedleaf.dataconverter.minecraft.versions.V1800;
import ca.spottedleaf.dataconverter.minecraft.versions.V1801;
import ca.spottedleaf.dataconverter.minecraft.versions.V1802;
import ca.spottedleaf.dataconverter.minecraft.versions.V1803;
import ca.spottedleaf.dataconverter.minecraft.versions.V1904;
import ca.spottedleaf.dataconverter.minecraft.versions.V1905;
import ca.spottedleaf.dataconverter.minecraft.versions.V1906;
import ca.spottedleaf.dataconverter.minecraft.versions.V1911;
import ca.spottedleaf.dataconverter.minecraft.versions.V1917;
import ca.spottedleaf.dataconverter.minecraft.versions.V1918;
import ca.spottedleaf.dataconverter.minecraft.versions.V1920;
import ca.spottedleaf.dataconverter.minecraft.versions.V1925;
import ca.spottedleaf.dataconverter.minecraft.versions.V1928;
import ca.spottedleaf.dataconverter.minecraft.versions.V1929;
import ca.spottedleaf.dataconverter.minecraft.versions.V1931;
import ca.spottedleaf.dataconverter.minecraft.versions.V1936;
import ca.spottedleaf.dataconverter.minecraft.versions.V1946;
import ca.spottedleaf.dataconverter.minecraft.versions.V1948;
import ca.spottedleaf.dataconverter.minecraft.versions.V1953;
import ca.spottedleaf.dataconverter.minecraft.versions.V1955;
import ca.spottedleaf.dataconverter.minecraft.versions.V1961;
import ca.spottedleaf.dataconverter.minecraft.versions.V1963;
import ca.spottedleaf.dataconverter.minecraft.versions.V2100;
import ca.spottedleaf.dataconverter.minecraft.versions.V2202;
import ca.spottedleaf.dataconverter.minecraft.versions.V2209;
import ca.spottedleaf.dataconverter.minecraft.versions.V2211;
import ca.spottedleaf.dataconverter.minecraft.versions.V2218;
import ca.spottedleaf.dataconverter.minecraft.versions.V2501;
import ca.spottedleaf.dataconverter.minecraft.versions.V2502;
import ca.spottedleaf.dataconverter.minecraft.versions.V2503;
import ca.spottedleaf.dataconverter.minecraft.versions.V2505;
import ca.spottedleaf.dataconverter.minecraft.versions.V2508;
import ca.spottedleaf.dataconverter.minecraft.versions.V2509;
import ca.spottedleaf.dataconverter.minecraft.versions.V2511;
import ca.spottedleaf.dataconverter.minecraft.versions.V2514;
import ca.spottedleaf.dataconverter.minecraft.versions.V2516;
import ca.spottedleaf.dataconverter.minecraft.versions.V2518;
import ca.spottedleaf.dataconverter.minecraft.versions.V2519;
import ca.spottedleaf.dataconverter.minecraft.versions.V2522;
import ca.spottedleaf.dataconverter.minecraft.versions.V2523;
import ca.spottedleaf.dataconverter.minecraft.versions.V2527;
import ca.spottedleaf.dataconverter.minecraft.versions.V2528;
import ca.spottedleaf.dataconverter.minecraft.versions.V2529;
import ca.spottedleaf.dataconverter.minecraft.versions.V2531;
import ca.spottedleaf.dataconverter.minecraft.versions.V2533;
import ca.spottedleaf.dataconverter.minecraft.versions.V2535;
import ca.spottedleaf.dataconverter.minecraft.versions.V2550;
import ca.spottedleaf.dataconverter.minecraft.versions.V2551;
import ca.spottedleaf.dataconverter.minecraft.versions.V2552;
import ca.spottedleaf.dataconverter.minecraft.versions.V2553;
import ca.spottedleaf.dataconverter.minecraft.versions.V2558;
import ca.spottedleaf.dataconverter.minecraft.versions.V2568;
import ca.spottedleaf.dataconverter.minecraft.versions.V2671;
import ca.spottedleaf.dataconverter.minecraft.versions.V2679;
import ca.spottedleaf.dataconverter.minecraft.versions.V2680;
import ca.spottedleaf.dataconverter.minecraft.versions.V2686;
import ca.spottedleaf.dataconverter.minecraft.versions.V2688;
import ca.spottedleaf.dataconverter.minecraft.versions.V2690;
import ca.spottedleaf.dataconverter.minecraft.versions.V2691;
import ca.spottedleaf.dataconverter.minecraft.versions.V2693;
import ca.spottedleaf.dataconverter.minecraft.versions.V2696;
import ca.spottedleaf.dataconverter.minecraft.versions.V2700;
import ca.spottedleaf.dataconverter.minecraft.versions.V2701;
import ca.spottedleaf.dataconverter.minecraft.versions.V2702;
import ca.spottedleaf.dataconverter.minecraft.versions.V2707;
import ca.spottedleaf.dataconverter.minecraft.versions.V2710;
import ca.spottedleaf.dataconverter.minecraft.versions.V2717;
import ca.spottedleaf.dataconverter.minecraft.versions.V2825;
import ca.spottedleaf.dataconverter.minecraft.versions.V2831;
import ca.spottedleaf.dataconverter.minecraft.versions.V2832;
import ca.spottedleaf.dataconverter.minecraft.versions.V2833;
import ca.spottedleaf.dataconverter.minecraft.versions.V2838;
import ca.spottedleaf.dataconverter.minecraft.versions.V2841;
import ca.spottedleaf.dataconverter.minecraft.versions.V2842;
import ca.spottedleaf.dataconverter.minecraft.versions.V2843;
import ca.spottedleaf.dataconverter.minecraft.versions.V2846;
import ca.spottedleaf.dataconverter.minecraft.versions.V2852;
import ca.spottedleaf.dataconverter.minecraft.versions.V501;
import ca.spottedleaf.dataconverter.minecraft.versions.V502;
import ca.spottedleaf.dataconverter.minecraft.versions.V505;
import ca.spottedleaf.dataconverter.minecraft.versions.V700;
import ca.spottedleaf.dataconverter.minecraft.versions.V701;
import ca.spottedleaf.dataconverter.minecraft.versions.V702;
import ca.spottedleaf.dataconverter.minecraft.versions.V703;
import ca.spottedleaf.dataconverter.minecraft.versions.V704;
import ca.spottedleaf.dataconverter.minecraft.versions.V705;
import ca.spottedleaf.dataconverter.minecraft.versions.V804;
import ca.spottedleaf.dataconverter.minecraft.versions.V806;
import ca.spottedleaf.dataconverter.minecraft.versions.V808;
import ca.spottedleaf.dataconverter.minecraft.versions.V813;
import ca.spottedleaf.dataconverter.minecraft.versions.V816;
import ca.spottedleaf.dataconverter.minecraft.versions.V820;
import ca.spottedleaf.dataconverter.minecraft.versions.V99;

public final class MCTypeRegistry {

    public static final MCDataType LEVEL              = new MCDataType("Level");
    public static final MCDataType PLAYER             = new MCDataType("Player");
    public static final MCDataType CHUNK              = new MCDataType("Chunk");
    public static final MCDataType HOTBAR             = new MCDataType("CreativeHotbar");
    public static final MCDataType OPTIONS            = new MCDataType("Options");
    public static final MCDataType STRUCTURE          = new MCDataType("Structure");
    public static final MCDataType STATS              = new MCDataType("Stats");
    public static final MCDataType SAVED_DATA         = new MCDataType("SavedData");
    public static final MCDataType ADVANCEMENTS       = new MCDataType("Advancements");
    public static final MCDataType POI_CHUNK          = new MCDataType("PoiChunk");
    public static final MCDataType ENTITY_CHUNK       = new MCDataType("EntityChunk");
    public static final IDDataType TILE_ENTITY        = new IDDataType("TileEntity");
    public static final IDDataType ITEM_STACK         = new IDDataType("ItemStack");
    public static final MCDataType BLOCK_STATE        = new MCDataType("BlockState");
    public static final MCValueType ENTITY_NAME       = new MCValueType("EntityName");
    public static final IDDataType ENTITY             = new IDDataType("Entity");
    public static final MCValueType BLOCK_NAME        = new MCValueType("BlockName");
    public static final MCValueType ITEM_NAME         = new MCValueType("ItemName");
    public static final MCDataType UNTAGGED_SPAWNER   = new MCDataType("Spawner");
    public static final MCDataType STRUCTURE_FEATURE  = new MCDataType("StructureFeature");
    public static final MCDataType OBJECTIVE          = new MCDataType("Objective");
    public static final MCDataType TEAM               = new MCDataType("Team");
    public static final MCValueType RECIPE            = new MCValueType("RecipeName");
    public static final MCValueType BIOME             = new MCValueType("Biome");
    public static final MCDataType WORLD_GEN_SETTINGS = new MCDataType("WorldGenSettings");

    static {
        // General notes:
        // - Structure converters run before everything.
        // - ID specific converters run after structure converters.
        // - Structure walkers run after id specific converters.
        // - ID specific walkers run after structure walkers.

        V99.register(); // all legacy data before converters existed
        V100.register(); // first version with version id
        V101.register();
        V102.register();
        V105.register();
        V106.register();
        V107.register();
        V108.register();
        V109.register();
        V110.register();
        V111.register();
        V113.register();
        V135.register();
        V143.register();
        V147.register();
        V165.register();
        V501.register();
        V502.register();
        V505.register();
        V700.register();
        V701.register();
        V702.register();
        V703.register();
        V704.register();
        V705.register();
        V804.register();
        V806.register();
        V808.register();
        V813.register();
        V816.register();
        V820.register();
        V1022.register();
        V1125.register();
        // END OF LEGACY DATA CONVERTERS

        // V1.13
        V1344.register();
        V1446.register();
        // START THE FLATTENING
        V1450.register();
        V1451.register();
        // END THE FLATTENING

        V1456.register();
        V1458.register();
        V1460.register();
        V1466.register();
        V1470.register();
        V1474.register();
        V1475.register();
        V1480.register();
        // V1481 is adding simple block entity
        V1483.register();
        V1484.register();
        V1486.register();
        V1487.register();
        V1488.register();
        V1490.register();
        V1492.register();
        V1494.register();
        V1496.register();
        V1500.register();
        V1501.register();
        V1502.register();
        V1506.register();
        V1510.register();
        V1514.register();
        V1515.register();
        V1624.register();
        // V1.14
        V1800.register();
        V1801.register();
        V1802.register();
        V1803.register();
        V1904.register();
        V1905.register();
        V1906.register();
        // V1909 is just adding a simple block entity (jigsaw)
        V1911.register();
        V1917.register();
        V1918.register();
        V1920.register();
        V1925.register();
        V1928.register();
        V1929.register();
        V1931.register();
        V1936.register();
        V1946.register();
        V1948.register();
        V1953.register();
        V1955.register();
        V1961.register();
        V1963.register();
        // V1.15
        V2100.register();
        V2202.register();
        V2209.register();
        V2211.register();
        V2218.register();
        // V1.16
        V2501.register();
        V2502.register();
        V2503.register();
        V2505.register();
        V2508.register();
        V2509.register();
        V2511.register();
        V2514.register();
        V2516.register();
        V2518.register();
        V2519.register();
        V2522.register();
        V2523.register();
        V2527.register();
        V2528.register();
        V2529.register();
        V2531.register();
        V2533.register();
        V2535.register();
        V2550.register();
        V2551.register();
        V2552.register();
        V2553.register();
        V2558.register();
        V2568.register();
        // V1.17
        // WARN: Mojang registers V2671 under 2571, but that version predates 1.16.5? So it looks like a typo...
        // I changed it to 2671, just so that it's after 1.16.5, but even then this looks misplaced... Thankfully this is
        // the first datafixer, and all it does is add a walker, so I think even if the version here is just wrong it will
        // work.
        V2671.register();
        V2679.register();
        V2680.register();
        // V2684 is registering a simple tile entity (skulk sensor)
        V2686.register();
        V2688.register();
        V2690.register();
        V2691.register();
        V2693.register();
        V2696.register();
        V2700.register();
        V2701.register();
        V2702.register();
        // In reference to V2671, why the fuck is goat being registered again? For this obvious reason, V2704 is absent.
        V2707.register();
        V2710.register();
        V2717.register();
        // V1.18
        V2825.register();
        V2831.register();
        V2832.register();
        V2833.register();
        V2838.register();
        V2841.register();
        V2842.register();
        V2843.register();
        V2846.register();
        V2852.register();
    }

    private MCTypeRegistry() {}
}
