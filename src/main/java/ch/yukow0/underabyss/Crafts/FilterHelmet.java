package ch.yukow0.underabyss.Crafts;

import ch.yukow0.underabyss.Component.HasFilter;
import ch.yukow0.underabyss.Enum.FILTER_TYPE;
import ch.yukow0.underabyss.Items.Filter.AdvancedFilter;
import ch.yukow0.underabyss.Items.Filter.BasicFilter;
import ch.yukow0.underabyss.Items.Filter.HeatResistantFilter;
import ch.yukow0.underabyss.Record.FilterRecord;
import ch.yukow0.underabyss.Serializer.FilterSerializer;
import ch.yukow0.underabyss.Tags.AirFilters;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static net.minecraft.world.item.ItemStack.EMPTY;

public class FilterHelmet extends CustomRecipe {
    public FilterHelmet(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, @NotNull Level level) {
        ItemStack helmet = EMPTY;
        boolean foundFilter = false;
        int totalItems = 0;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            totalItems++;

            if (stack.is(AirFilters.AIR_FILTERS)) {
                foundFilter = true;
            } else if (stack.is(ItemTags.HEAD_ARMOR)) {
                if (getArmorDefense(stack) >= 2) {
                    helmet = stack;
                }
            }
        }

        if (!helmet.isEmpty() && helmet.has(HasFilter.HAS_FILTER.get())) {
            return false;
        }

        return !helmet.isEmpty() && foundFilter && totalItems == 2;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider provider) {
        ItemStack helmet = EMPTY;
        ItemStack filter = EMPTY;


        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.is(ItemTags.HEAD_ARMOR)) {
                helmet = stack;
            } else if (stack.is(AirFilters.AIR_FILTERS)) {
                filter = stack;
            }
        }

        if (helmet.isEmpty() || filter.isEmpty()) return EMPTY;

        ItemStack result = helmet.copy();
        result.setCount(1);


        Map<Item, FILTER_TYPE> map = Map.of(
                BasicFilter.BASIC_FILTER.get(), FILTER_TYPE.BASIC,
                AdvancedFilter.ADVANCED_FILTER.get(), FILTER_TYPE.ADVANCED,
                HeatResistantFilter.HEAT_RESISTANT_FILTER.get(), FILTER_TYPE.HEATRESISTANT
        );

        FILTER_TYPE type = map.getOrDefault(filter.getItem(), FILTER_TYPE.BASIC);


        switch (type) {
            case BASIC -> {
                result.set(HasFilter.HAS_FILTER.get(), new FilterRecord(FILTER_TYPE.BASIC, 1000));
                result.set(DataComponents.LORE, new ItemLore(List.of(
                        Component.translatable("tooltip.underabyss.helmet_filter.description").withStyle(ChatFormatting.BLUE),
                        Component.literal("Durability: 1000").withStyle(ChatFormatting.AQUA)
                )));
            }
            case ADVANCED -> {
                result.set(HasFilter.HAS_FILTER.get(), new FilterRecord(FILTER_TYPE.ADVANCED, 1500));
                result.set(DataComponents.LORE, new ItemLore(List.of(
                        Component.translatable("tooltip.underabyss.advancedfilter.advanced_description").withStyle(ChatFormatting.BLUE),
                        Component.literal("Durability: 1500").withStyle(ChatFormatting.AQUA)
                )));
            }
            case HEATRESISTANT -> {
                result.set(HasFilter.HAS_FILTER.get(), new FilterRecord(FILTER_TYPE.HEATRESISTANT, 1700));
                result.set(DataComponents.LORE, new ItemLore(List.of(
                        Component.translatable("tooltip.underabyss.heatresistant_filter_helmet.description").withStyle(ChatFormatting.DARK_RED),
                        Component.literal("Durability: 1000").withStyle(ChatFormatting.AQUA)
                )));
            }
        }

        return result;
    }


    private double getArmorDefense(ItemStack stack) {
        ItemAttributeModifiers modifiers = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
        if (modifiers == null) {
            modifiers = stack.getItem().components().get(DataComponents.ATTRIBUTE_MODIFIERS);
        }

        if (modifiers != null) {
            double defense = 0;
            for (ItemAttributeModifiers.Entry entry : modifiers.modifiers()) {
                if (entry.attribute().is(Attributes.ARMOR) &&
                        (entry.slot().equals(EquipmentSlotGroup.HEAD) || entry.slot().equals(EquipmentSlotGroup.ANY))) {
                    defense += entry.modifier().amount();
                }
            }
            if (defense > 0) return defense;
        }

        Item item = stack.getItem();
        if (item == net.minecraft.world.item.Items.IRON_HELMET) return 2;
        if (item == net.minecraft.world.item.Items.GOLDEN_HELMET) return 2;
        if (item == net.minecraft.world.item.Items.DIAMOND_HELMET) return 3;
        if (item == net.minecraft.world.item.Items.NETHERITE_HELMET) return 3;
        if (item == net.minecraft.world.item.Items.TURTLE_HELMET) return 2;
        if (item == net.minecraft.world.item.Items.CHAINMAIL_HELMET) return 2;

        return 0;
    }


    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return FilterSerializer.FILTER_HELMET_SERIALIZER.get();
    }
}