package ch.yukow0.underabyss.Items.Filter;

import ch.yukow0.underabyss.Underabyss;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemLore;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class BasicFilter {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Underabyss.MODID);

    public static final DeferredItem<Item> BASIC_FILTER = ITEMS.registerItem("basic_filter", props -> new Item(
            props.rarity(Rarity.COMMON)
                    .component(DataComponents.LORE, new ItemLore(List.of(
                            Component.translatable("tooltip.underabyss.basic_filter.description").withStyle(ChatFormatting.GRAY)
                    )))
    ));

    public static void AddInCreative(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(BasicFilter.BASIC_FILTER);
        }
    }



}
