package ch.yukow0.underabyss;

import ch.yukow0.underabyss.Component.HasFilter;
import ch.yukow0.underabyss.Items.Filter.AdvancedFilter;
import ch.yukow0.underabyss.Items.Filter.BasicFilter;
import ch.yukow0.underabyss.Items.Filter.HeatResistantFilter;
import ch.yukow0.underabyss.Serializer.FilterSerializer;
import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Underabyss.MODID)
public class Underabyss {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "underabyss";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Underabyss(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Underabyss) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        BasicFilter.ITEMS.register(modEventBus);
        AdvancedFilter.ITEMS.register(modEventBus);
        HasFilter.COMPONENTS.register(modEventBus);
        HeatResistantFilter.ITEMS.register(modEventBus);

        modEventBus.addListener(HeatResistantFilter::AddInCreative);

        modEventBus.addListener(BasicFilter::AddInCreative);
        modEventBus.addListener(AdvancedFilter::AddInCreative);
        FilterSerializer.SERIALIZERS.register(modEventBus);

    }







    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");

        }
    }
}
